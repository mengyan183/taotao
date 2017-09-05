package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xingguo on 2017-09-04 15:04.
 * 商品管理controller
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class ItemController {
    /**
     * TODO:由于我们使用dubbo发布服务接口,idea默认找不到对应的实现类,所以会报错
     */
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemid}")
    public @ResponseBody
    TbItem getItemById(@PathVariable Long itemid) {
        //根据商品id查询商品信息
        TbItem item = itemService.getItemById(itemid);
        return item;
    }
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    @RequestMapping("/item/list")
    public @ResponseBody EasyUIDataGridResult tbItemList(Integer page,Integer rows) {
        EasyUIDataGridResult allItemList = itemService.getAllItemList(page, rows);
        return allItemList;
    }



}
