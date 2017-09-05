package com.taotao.controller;

/**
 * Created by xingguo on 2017-09-05 11:33.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class ItemCatController {
    @AutoWired
    private com.taotao.service.ItemCatService itemCatService;

    @ResultMapping("/item/cat/list")
    public @ReponseBody List<com.taotao.common.pojo.EasyUITreeData> getItemCatList(@RequestParam(defaultValue="0")Long parentId){
            return itemCatService.getItemCatList(parentId);

    }
}
