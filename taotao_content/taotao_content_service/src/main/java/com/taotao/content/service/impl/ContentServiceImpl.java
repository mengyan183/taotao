package com.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
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
     *  @param categoryId
     * @param page
     * @param rows
     */
    @Override
    public EasyUIDataGridResult findContentByCidAndPage(String categoryId, String page, String rows) {

       PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(rows));
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
        for (String id : ides){
            tbContentMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
    }

    /**
     * 根据列表id获得所有的content
     *
     * @param cateGoryId
     * @return
     */
    @Override
    public List<TbContent> getContentById(String cateGoryId) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(Long.parseLong(cateGoryId));
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        return tbContents;
    }
}
