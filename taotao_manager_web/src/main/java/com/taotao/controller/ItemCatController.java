package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeData;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xingguo on 2017-09-05 11:33.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class ItemCatController {
    /**
     * 我们采用dubbo发布服务接口来实现注入
     */
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    public @ResponseBody
    List<EasyUITreeData> getItemCatList(@RequestParam(value = "id",defaultValue="0")Long parentId){
            return itemCatService.getItemCatList(parentId);

    }
}
