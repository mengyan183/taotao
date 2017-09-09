package com.taotao.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * Created by xingguo on 2017-09-04 14:28.
 *
 * @author mengy
 * @project taotao
 * <p>
 * 实现itemService接口,并注入itemMapper接口
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    /**
     * TODO:这不是错误,由于Mapper接口是底层动态代理直接实现的,idea默认安全级别显示无法完成注入
     */
    @Autowired
    private TbItemMapper tbItemMapper;


    /**
     * 执行根据id得到tbItem
     *
     * @param id
     * @return
     */
    @Override
    public TbItem getItemById(Long id) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
        return tbItem;
    }

    /**
     * 查询所有商品
     */
    @Override
    public EasyUIDataGridResult getAllItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> items = tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(items);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(tbItemPageInfo.getTotal());
        easyUIDataGridResult.setRows(items);
        return easyUIDataGridResult;
    }


    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "itemAddTopicDestination")
    private Topic topic;


    /**
     * 添加商品
     *
     * @param tbItem
     * @param itemDesc
     */
    @Override
    public TaotaoResult addItem(TbItem tbItem, String itemDesc) {
        Date date = new Date();
       final long itemid=IDUtils.genItemId();
        //生成商品id
        tbItem.setId(itemid);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //1-正常 2-下架 3-删除
        tbItem.setStatus((byte) 1);
        //插入商品表
        int id = tbItemMapper.insert(tbItem);
        //商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setCreated(date);
        tbItemDesc.setItemDesc(itemDesc);
        tbItemDesc.setUpdated(date);
        //插入商品描述表
        tbItemDescMapper.insert(tbItemDesc);
        //商品添加完成后发送activemq
        jmsTemplate.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemid + "");
                return textMessage;
            }
        });

        return TaotaoResult.ok();
    }


    /**
     * 我们采用通配符对商品进行操作
     *
     * @param ids
     * @param operate
     * @return
     */
    @Override
    public void updateItemByOperate(String ids, String operate) {
        Byte status = null;
        if ("delete".equals(operate)) {
            status = (byte) 3;
        } else if ("instock".equals(operate)) {
            status = (byte) 2;
        } else {
            status = (byte) 1;
        }

        String[] split = ids.split(",");
        for (String id : split) {
            TbItem tbItem = tbItemMapper.selectByPrimaryKey(Long.parseLong(id));
            tbItem.setStatus(status);
            tbItem.setUpdated(new Date());
            tbItemMapper.updateByPrimaryKey(tbItem);
        }
       return;
    }

    /**
     * 查询商品介绍
     *
     * @param id
     */
    @Override
    public TbItemDesc itemDescByItemId(String id) {
        return tbItemDescMapper.selectByPrimaryKey(Long.parseLong(id));
    }

    /**
     * 更新商品
     *
     * @param tbItem
     * @param desc
     */
    @Override
    public void updateItem(TbItem tbItem, String desc) {
        TbItem tbItem1 = tbItemMapper.selectByPrimaryKey(tbItem.getId());
        tbItem.setStatus(tbItem1.getStatus());
        tbItem.setCreated(tbItem1.getCreated());
        tbItem.setUpdated(new Date());
        tbItemMapper.updateByPrimaryKey(tbItem);
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
    }
}
