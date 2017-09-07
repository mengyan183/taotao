package com.taotao.protal.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.protal.pojo.Ad1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingguo on 2017-09-06 18:22.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class IndexController {

    @Value("${AD1_CONTENT_CID}")
    private String AD1_CONTENT_CID;
    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;


    @Autowired
    private ContentService contentService;

    /**
     * 展示商城首页
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model){
        //获取内容id,从属性文件中获取
        //根据内容id查询所有内容
        List<TbContent> tbContents = contentService.getContentById(AD1_CONTENT_CID);
        List<Ad1Node> ad1NodeList =new ArrayList<>();
        for (TbContent tbContent : tbContents){
            Ad1Node ad1Node = new Ad1Node();
            ad1Node.setAlt(tbContent.getTitle());
            ad1Node.setHref(tbContent.getUrl());
            ad1Node.setSrc(tbContent.getPic());
            ad1Node.setSrcB(tbContent.getPic2());
            ad1Node.setHeight(AD1_HEIGHT);
            ad1Node.setHeightB(AD1_HEIGHT_B);
            ad1Node.setWidth(AD1_WIDTH);
            ad1Node.setWidthB(AD1_WIDTH_B);
            ad1NodeList.add(ad1Node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(ad1NodeList));
        return "index";
    }
}
