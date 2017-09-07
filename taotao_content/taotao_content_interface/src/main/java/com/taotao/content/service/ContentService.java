package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by xingguo on 2017-09-07 09:28.
 *  商品内容服务接口
 * @author mengy
 * @project taotao
 */
public interface ContentService {
    /**
     * 根据categoryid对数据进行分页查询
     */
    EasyUIDataGridResult findContentByCidAndPage(String categoryId, String page, String rows);

    /**
     * 添加content
     * @param tbContent
     */
    void addContent(TbContent tbContent);

    /**
     * 更新content
     * @param tbContent
     */
    void updateContent(TbContent tbContent);
    /**
     * 删除content
     */
    void deleteContent(String ids);

    /**
     * 根据列表id获得所有的content
     * @param cateGoryId
     * @return
     */
    List<TbContent> getContentById(String cateGoryId);
}
