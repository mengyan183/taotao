package com.taotao.common.exception;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xingguo on 2017-09-09 10:00.
 * 全局异常处理器
 *
 * @author mengy
 * @project taotao
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //写日志文件
        logger.error("运行时异常", e);
        //发短信/邮件
        //跳转到错误页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error/exception");
        modelAndView.addObject("message", "你的网络异常,请稍后重试");
        return modelAndView;
    }
}
