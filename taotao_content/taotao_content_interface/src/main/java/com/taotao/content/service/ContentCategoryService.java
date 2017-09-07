package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeData;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

import java.util.List;

/**
 * Created by xingguo on 2017-09-06 19:09.
 *  内容展示
 * @author mengy
 * @project taotao
 */
public interface ContentCategoryService {
    /**
     * 查询所有的列表信息分类
     * @param parentId
     * @return
     */
    List<EasyUITreeData> getContentCatList(Long parentId);
    /**
     * 添加列表分类数据
     */

    TaotaoResult insertContentCategory(Long parentId,String name);

    /***
     * 更新列表数据
     * @param id
     * @param name
     */
    void updateContentCatgory(Long id, String name);

    /**
     * 删除列表数据
     *
     * @param id
     */
    void deleteContentCatGory(Long id);
}
