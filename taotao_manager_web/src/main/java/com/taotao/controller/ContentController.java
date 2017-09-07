package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by xingguo on 2017-09-07 09:30.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 对根据cid content进行分页数据查询
     *
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/content/query/list")
    public @ResponseBody
    EasyUIDataGridResult list(String categoryId, String page, String rows) {
        return contentService.findContentByCidAndPage(categoryId, page, rows);
    }

    /**
     * 添加Content
     * @param tbContent
     * @return
     */
    @RequestMapping("/content/save")
    public @ResponseBody
    TaotaoResult addContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        contentService.addContent(tbContent);
        return TaotaoResult.ok();
    }

    /**
     * 更新content
     * @param tbContent
     * @return
     */
    @RequestMapping("/rest/content/edit")
    public @ResponseBody
    TaotaoResult updateContent(TbContent tbContent){
        contentService.updateContent(tbContent);
        return TaotaoResult.ok();

    }

    @RequestMapping("/content/delete")
    public @ResponseBody TaotaoResult deleteByIds(String ids){
        contentService.deleteContent(ids);
        return TaotaoResult.ok();
    }
}
