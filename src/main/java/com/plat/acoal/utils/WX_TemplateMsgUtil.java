/*
package com.plat.acoal.utils;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.plat.acoal.dao.AccessTokenMapper;
import com.plat.acoal.dao.AlarmMapper;
import com.plat.acoal.model.AccessToken;
import com.plat.acoal.model.TemplateData;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class WX_TemplateMsgUtil {
    @Autowired
    public AccessTokenMapper accessTokenMapper;

    private static Logger log = LoggerFactory.getLogger(WX_TemplateMsgUtil.class);


    */
/**
     * 封装模板详细信息
     *
     * @return
     *//*


    public static JSONObject packJsonmsg(Map<String, TemplateData> param) {

        JSONObject json = new JSONObject();

        for (Map.Entry<String, TemplateData> entry : param.entrySet()) {

            JSONObject keyJson = new JSONObject();

            TemplateData dta = entry.getValue();

            keyJson.put("value", dta.getValue());

            keyJson.put("color", dta.getColor());

            json.put(entry.getKey(), keyJson);

        }

        return json;

    }

    */
/**
     * 根据模板的编号 新增并获取模板ID
     *
     * @param templateSerialNumber
     *            模板库中模板的 "编号"
     * @return 模板ID
     *//*


    public static String getWXTemplateMsgId(String templateSerialNumber) {

        String tmpurl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token="
                + WX_TokenUtil.getWXToken().getAccesstoken();

        JSONObject json = new JSONObject();
        // template_id_short是模板编号
        json.put("template_id_short", templateSerialNumber);
        // 路径，请求方式，传的数据
        JSONObject result = WX_HttpsUtil.httpsRequest(tmpurl, "POST", json.toString());

        JSONObject resultJson = new JSONObject(result);

        String errmsg = (String) resultJson.get("errmsg");

        log.info("获取模板编号返回信息：" + errmsg);

        if (!"ok".equals(errmsg)) {

            return "error";

        }

        String templateId = (String) resultJson.get("template_id");

        return templateId;

    }


    public static String sendWechatMsgToUser(String touser, String templatId, String clickurl, String topcolor,
                                             JSONObject data) {

        */
/**
         * 做个查询，access token是否过期
         *
         *//*

            AccessToken access_token=null;
            access_token = accessTokenMapper.selectByPrimaryKey(1);
            String accesstoken = "";
            if (access_token != null) {
                Date updatetime = access_token.getUpdatetime();
                Date now = new Date();
                // 这样得到的差值是微秒级别
                long diff = now.getTime() - updatetime.getTime();
                long seconds = diff / (1000);

                if (seconds >= 5400) {
                    AccessToken accessToken = new AccessToken();
                    accesstoken = WX_TokenUtil.getWXToken().getAccesstoken();

                    accessToken.setAccesstoken(accesstoken);
                    accessToken.setUpdatetime(now);
                    accessToken.setId(1);
                    int flag = accessTokenMapper.updateByPrimaryKeySelective(accessToken);

                } else {

                    accesstoken = access_token.getAccesstoken();

                }
            } else {
                Date now = new Date();
                accesstoken = WX_TokenUtil.getWXToken().getAccesstoken();
                AccessToken accessToken = new AccessToken();
                accessToken.setId(1);
                accessToken.setAccesstoken(accesstoken);
                accessToken.setUpdatetime(now);
                int flag = accessTokenMapper.insert(accessToken);
            }
            String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accesstoken;

            JSONObject json = new JSONObject();

            json.put("touser", touser);

            json.put("template_id", templatId);

            json.put("url", clickurl);

            json.put("topcolor", topcolor);

            json.put("data", data);

            JSONObject result = WX_HttpsUtil.httpsRequest(tmpurl, "POST", json.toString());

            JSONObject resultJson = new JSONObject(result);

            log.info("发送微信消息返回信息：" + resultJson.get("errcode"));

            String errmsg = (String) resultJson.get("errmsg");

            // 如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
            if (!"ok".equals(errmsg)) {

                return "error";

            }


        return "success";

    }
}*/
