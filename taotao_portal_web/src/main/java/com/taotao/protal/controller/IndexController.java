package com.taotao.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xingguo on 2017-09-06 18:22.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class IndexController {
    /**
     * 展示商城首页
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }
}
