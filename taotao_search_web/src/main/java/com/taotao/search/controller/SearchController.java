package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xingguo on 2017-09-08 08:42.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class SearchController {

    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;


    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(String q, @RequestParam(defaultValue = "1")String page, Model model) throws Exception {
        String ask = new String(q.getBytes("iso-8859-1"), "utf-8");
        SearchResult searchResult = searchService.searchItemByAdvice(ask, Integer.parseInt(page), ITEM_ROWS);
        //传递给页面
        model.addAttribute("query", ask);
      /*  model.addAttribute("totalPages", searchResult.getRecordCount()/ITEM_ROWS);*/
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);

        return "search";

    }
}
