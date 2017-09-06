package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingguo on 2017-09-06 09:56.
 *
 * @author mengy
 * @project taotao
 */
@Controller
public class PictureController {

    @Value("${image_server_url}")
    private String IMAGE_URL;

    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public @ResponseBody String uploadPic(MultipartFile uploadFile) {
        //接收文件
        try {
            byte[] bytes = uploadFile.getBytes();
            //取得文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //把文件内容上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:FastDfs.conf");
            String s = fastDFSClient.uploadFile(bytes, ext);
            //从配置文件中取图片服务器的url
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("error", 0);
            result.put("url", IMAGE_URL + s);
            //解决浏览器兼容的问题,我们返回json字符串
            return JsonUtils.objectToJson(result);
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("error", 1);
            result.put("msg", "图片上传失败");
            e.printStackTrace();
            return JsonUtils.objectToJson(result);
        }


    }
}
