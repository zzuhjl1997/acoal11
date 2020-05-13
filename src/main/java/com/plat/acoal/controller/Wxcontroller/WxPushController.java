package com.plat.acoal.controller.Wxcontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.acoal.dao.WxUserMapper;
import com.plat.acoal.entity.WxUser;
import com.plat.acoal.model.TemplateData;
import com.plat.acoal.service.impl.WX_TemplateMsgServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.RestController;


/**
 * 问候语 报警类型 报警设备 报警时间 报警内容
 */
@RestController
@RequestMapping(value = "/wxacpush")
public class WxPushController {
    @Autowired
    public WX_TemplateMsgServiceImpl wx_templateMsgServiceImpl;

    @RequestMapping(value = "/dopush", produces = "application/json;charset=utf-8")
    // ,method = {RequestMethod.POST}method = RequestMethod.POST,
    private String dopush(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model) {
        int deviceid = 0;
        //cname 客户名
        String cname = "", eventtype = "", devicename = "", content = "", remark = "";

        if (request.getParameter("cname") != null && !"".equals(request.getParameter("cname"))) {
            cname = request.getParameter("cname");
        }

        if (request.getParameter("deviceid") != null && !"".equals(request.getParameter("deviceid"))) {
            deviceid = Integer.parseInt(request.getParameter("deviceid"));
        }
        if (request.getParameter("eventtype") != null && !"".equals(request.getParameter("eventtype"))) {
            eventtype = request.getParameter("eventtype").toString();
        }
        if (request.getParameter("devicename") != null && !"".equals(request.getParameter("devicename"))) {
            devicename = request.getParameter("devicename").toString();
        }

        if (request.getParameter("content") != null && !"".equals(request.getParameter("content"))) {
            content = request.getParameter("content").toString();
        }
        if (request.getParameter("remark") != null && !"".equals(request.getParameter("remark"))) {
            remark = request.getParameter("remark").toString();
        }

        Map<String, TemplateData> param = new HashMap();
        // 问候语
        param.put("first", new TemplateData(cname, "#EE0000"));

        // 报警类型
        param.put("keyword1", new TemplateData(eventtype, "#EE0000"));
        // 报警设备
        param.put("keyword2", new TemplateData(devicename, "#EE0000"));
        // 报警时间:精确到时分秒
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        param.put("keyword3", new TemplateData((df.format(date)).toString(), "#EE0000"));
        // 报警内容
        param.put("keyword4", new TemplateData(content, "#EE0000"));
        // 备注信息
        param.put("remark", new TemplateData(remark, "#EE0000"));

        JSON.toJSONString(param);

        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(param));

        // 调用发送微信消息给用户的接口 ********这里写自己在微信公众平台拿到的模板ID

//        List<WxUser> list = wxUserMapper.selectOpenidList(deviceid);
//        for (WxUser wxUser : list) {
//            System.out.println(wxUser.getOpenidGzh());
      /*  WX_TemplateMsgUtil.sendWechatMsgToUser(wxUser.getOpenidGzh(), "此处填写你的模板id",
                null,

                "#000000", jsonObject);*/
//        }
        wx_templateMsgServiceImpl.sendWechatMsgToUser("oXpoI1gNyoGnb9C5EXlpglw-trhY", "vuqd2ZS1Fbqto9rXcqJ8CleHr1V4XXOT0OKLw3QXgB4",
                null,

                "#000000", jsonObject);
        System.out.println("进入了这个方法");
        System.out.println(123);
        return null;
    }

}
