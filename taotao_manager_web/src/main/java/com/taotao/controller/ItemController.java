package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
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


    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 遍历商品
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    public @ResponseBody
    EasyUIDataGridResult tbItemList(Integer page, Integer rows) {
        EasyUIDataGridResult allItemList = itemService.getAllItemList(page, rows);
        return allItemList;
    }

    /**
     * 保存商品
     *
     * @param tbItem
     * @param desc
     * @return
     */
    @RequestMapping("/item/save")
    public @ResponseBody
    TaotaoResult addItem(TbItem tbItem, String desc) {
        TaotaoResult taotaoResult = itemService.addItem(tbItem, desc);
        return taotaoResult;
    }

    /**
     * 对商品进行删除,上架下架功能
     *
     * @param ids
     * @return
     */
    @RequestMapping("/item/updateBy/{operate}")
    public @ResponseBody
    String updateItemByOperate(String ids, @PathVariable String operate) {
        itemService.updateItemByOperate(ids, operate);
        TaotaoResult taotaoResult = new TaotaoResult();
        taotaoResult.setStatus(200);
        String s = JsonUtils.objectToJson(taotaoResult);
        return s;
    }

    @RequestMapping("/item/desc/{id}")
    public @ResponseBody
    String selectItemDescByItemId(@PathVariable String id) {

        TbItemDesc tbItemDesc = itemService.itemDescByItemId(id);
        TaotaoResult taotaoResult = new TaotaoResult();
        taotaoResult.setStatus(200);
        taotaoResult.setData(tbItemDesc);
        String s = JsonUtils.objectToJson(taotaoResult);

        return s;

    }
    @RequestMapping("/item/edit/{id}")
    public @ResponseBody
    String selectItemByItemId(@PathVariable String id) {
        TbItem itemById = itemService.getItemById(Long.parseLong(id));
        TaotaoResult taotaoResult = new TaotaoResult();
        taotaoResult.setStatus(200);
        taotaoResult.setData(itemById);
        String s = JsonUtils.objectToJson(taotaoResult);

        return s;

    }

    @RequestMapping("/rest/page/item-edit")
    public String itemEdit(){
        return "item-edit";
    }

    @RequestMapping("/rest/item/update")
    public @ResponseBody String itemUpdate(TbItem tbItem, String desc){
        itemService.updateItem(tbItem,desc);
        return JsonUtils.objectToJson(TaotaoResult.ok());
    }


}
