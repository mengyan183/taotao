package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeData;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xingguo on 2017-09-05 09:03.
 *
 * @author mengy
 * @project taotao
 * <p>
 * 实现itemcat 查询所有的接口
 */
@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {

    /**
     * itemMapper 由于没有具体的实现类,而是由底层动态代理的形式生成代理类
     */

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * 根据父节点查询所有的子节点
     */
    @Override
    public List<EasyUITreeData> getItemCatList(Long parentId) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        java.util.List<EasyUITreeData> easyUITreeDataArrayList = new java.util.ArrayList<EasyUITreeData>();
        for (com.taotao.pojo.TbItemCat tbItemCat : tbItemCats) {
            EasyUITreeData easyUITreeData = new EasyUITreeData();
            easyUITreeData.setId(tbItemCat.getId());
            easyUITreeData.setText(tbItemCat.getName());
            easyUITreeData.setState(tbItemCat.getIsParent() ? "closed" : "open");
            easyUITreeDataArrayList.add(easyUITreeData);
        }
        return easyUITreeDataArrayList;
    }
}
