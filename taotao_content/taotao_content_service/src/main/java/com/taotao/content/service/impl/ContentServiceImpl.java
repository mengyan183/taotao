package com.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.content.service.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by xingguo on 2017-09-07 09:34.
 *
 * @author mengy
 * @project taotao
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    /**
     * 根据categoryid对数据进行分页查询
     *
     * @param categoryId
     * @param page
     * @param rows
     */
    @Override
    public EasyUIDataGridResult findContentByCidAndPage(String categoryId, String page, String rows) {

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(rows));
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(Long.parseLong(categoryId));
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        PageInfo<TbContent> tbContentPageInfo = new PageInfo<>(tbContents);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setRows(tbContentPageInfo.getList());
        easyUIDataGridResult.setTotal(tbContentPageInfo.getTotal());
        return easyUIDataGridResult;
    }

    /**
     * 添加content
     *
     * @param tbContent
     */
    @Override
    public void addContent(TbContent tbContent) {
        tbContentMapper.insert(tbContent);
        //同步缓存数据
        jedisClient.hdel(CONTENT_KEY,tbContent.getCategoryId().toString(),JsonUtils.objectToJson(tbContent));
    }

    /**
     * 更新content
     *
     * @param tbContent
     */
    @Override
    public void updateContent(TbContent tbContent) {
        TbContent tbContent1 = tbContentMapper.selectByPrimaryKey(tbContent.getId());
        tbContent.setCreated(tbContent1.getCreated());
        tbContent.setUpdated(new Date());
        tbContentMapper.updateByPrimaryKey(tbContent);
    }

    /**
     * 删除content
     *
     * @param ids
     */
    @Override
    public void deleteContent(String ids) {
        String[] ides = ids.split(",");
        for (String id : ides) {
            tbContentMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
    }


    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    /**
     * 根据列表id获得所有的content
     *
     * @param cateGoryId
     * @return
     */
    @Override
    public List<TbContent> getContentById(String cateGoryId) {
        //首先查询缓存,如果缓存存在直接返回结果
        try {
            String hget = jedisClient.hget(CONTENT_KEY, cateGoryId);
            if(StringUtils.isNotBlank(hget)){
                //如果不为空,我们需要把json转换为对象
                List<TbContent> tbContents = JsonUtils.jsonToList(hget, TbContent.class);
                return tbContents;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //否者,从数据库中查询结果返回并添加缓存

        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(Long.parseLong(cateGoryId));
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        //往数据库中保存数据
        try {
            jedisClient.hset(CONTENT_KEY,cateGoryId, JsonUtils.objectToJson(tbContents));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}
