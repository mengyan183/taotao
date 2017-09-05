package com.taotao.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public EasyUIDataGridResult getAllItemList(int page,int rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> items = tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(items);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(tbItemPageInfo.getTotal());
        easyUIDataGridResult.setRows(items);
        return easyUIDataGridResult;
    }
}
