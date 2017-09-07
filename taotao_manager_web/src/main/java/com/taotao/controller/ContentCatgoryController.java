package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeData;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xingguo on 2017-09-06 19:27.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class ContentCatgoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(value = "/content/category/list" )
    public @ResponseBody
    List<EasyUITreeData> contenCategoryList(@RequestParam(defaultValue = "0") Long id){
        List<EasyUITreeData> contentCatList = contentCategoryService.getContentCatList(id);
        return contentCatList;
    }

    @RequestMapping("/content/category/create")
    public @ResponseBody TaotaoResult createContentCategory(Long parentId,String name){
        TaotaoResult taotaoResult = contentCategoryService.insertContentCategory(parentId, name);
        return taotaoResult;


    }
    @RequestMapping("/content/category/update")
    public @ResponseBody void updateContentCatgory(Long id ,String name){
        contentCategoryService.updateContentCatgory(id,name);
        return ;
    }

   @RequestMapping("/content/category/delete")
   public @ResponseBody void deleteContentCatGory(Long id){
        contentCategoryService.deleteContentCatGory(id);
   }



}
