package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xingguo on 2017-09-07 23:34.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/import")
    public @ResponseBody TaotaoResult importIndex(){
        try {
            TaotaoResult taotaoResult = searchItemService.importAllItemsToIndex();
            return taotaoResult;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, "导入数据失败");
        }
    }

}
