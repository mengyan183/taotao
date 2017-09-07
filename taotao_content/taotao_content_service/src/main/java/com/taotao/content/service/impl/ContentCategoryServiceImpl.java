package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeData;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xingguo on 2017-09-06 19:12.
 * 内容分类管理服务
 *
 * @author mengy
 * @project taotao
 */
@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    /**
     * 根据父节点查询所有内容
     *
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITreeData> getContentCatList(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        List<EasyUITreeData> easyUITreeDataList = new ArrayList<EasyUITreeData>();
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            EasyUITreeData easyUITreeData = new EasyUITreeData();
            easyUITreeData.setId(tbContentCategory.getId());
            easyUITreeData.setText(tbContentCategory.getName());
            easyUITreeData.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            easyUITreeDataList.add(easyUITreeData);
        }
        return easyUITreeDataList;
    }

    /**
     * 添加列表分类数据
     *
     * @param parentId
     * @param name
     */
    @Override
    public TaotaoResult insertContentCategory(Long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        //新添加的节点都是叶子节点
        tbContentCategory.setIsParent(false);
        //1:正常 2:异常
        tbContentCategory.setStatus(1);
        //默认排序为1
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        //获得自增后的id
        int insert = tbContentCategoryMapper.insert(tbContentCategory);
        //判断父节点
        TbContentCategory paranetNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!paranetNode.getIsParent()) {
            paranetNode.setIsParent(true);
            //如果不是父节点,要对其进行更新操作
            tbContentCategoryMapper.updateByPrimaryKey(paranetNode);
        }
        return TaotaoResult.ok(tbContentCategory);
    }

    /***
     * 更新列表数据

     * @param id
     * @param name
     */
    @Override
    public void updateContentCatgory(Long id, String name) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        tbContentCategory.setName(name);
        tbContentCategory.setUpdated(new Date());
        tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);

    }

    /**
     * 删除列表数据
     *
     * @param id
     */
    @Override
    public void deleteContentCatGory(Long id) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        if (tbContentCategory.getIsParent()) {
            //如果是父节点
            TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
            criteria.andParentIdEqualTo(id);
            List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
            if (tbContentCategories.size() > 0) {
                for (TbContentCategory tbContentCategory1 : tbContentCategories) {
                    this.deleteContentCatGory(tbContentCategory1.getId());
                }
            } else {
                tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
            }
        } else {
            //如果不是父节点
            tbContentCategoryMapper.deleteByPrimaryKey(id);
        }

    }
}
