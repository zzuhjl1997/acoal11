package com.plat.acoal.controller.Wxcontroller;


import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.plat.acoal.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@RequestMapping("/wechat")

@Controller

public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);
    //这里的token与服务器配置中的token一致

    private static String WECHAT_TOKEN = "stupwxid";

    @RequestMapping(value = "/wx.do")

    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.error("WechatController   ----   WechatController");

        System.out.println("========WechatController========= ");

        logger.info("请求进来了...");



        Enumeration pNames = request.getParameterNames();

        while (pNames.hasMoreElements()) {

            String name = (String) pNames.nextElement();

            String value = request.getParameter(name);


            String log = "name =" + name + "     value =" + value;

            logger.error(log);

        }
        /// 微信加密签名
        String signature = request.getParameter("signature");
        /// 时间戳
        String timestamp = request.getParameter("timestamp");
        /// 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {

            out.print(echostr);

        }

        out.close();

        out = null;

        response.sendRedirect("");

    }

}