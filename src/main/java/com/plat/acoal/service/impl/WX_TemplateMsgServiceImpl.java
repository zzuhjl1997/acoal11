package com.plat.acoal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.plat.acoal.dao.AccessTokenMapper;
import com.plat.acoal.model.AccessToken;
import com.plat.acoal.model.TemplateData;
import com.plat.acoal.service.WX_TemplateMsgService;
import com.plat.acoal.utils.WX_HttpsUtil;
import com.plat.acoal.utils.WX_TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class WX_TemplateMsgServiceImpl implements WX_TemplateMsgService {
    private static Logger log = LoggerFactory.getLogger(WX_TemplateMsgServiceImpl.class);


    @Autowired
    public AccessTokenMapper accessTokenMapper;

    @Override
    public JSONObject packJsonmsg(Map<String, TemplateData> param) {
        return null;
    }

    @Override
    public String getWXTemplateMsgId(String templateSerialNumber) {
        return null;
    }

    @Override
    public String sendWechatMsgToUser(String touser, String templatId, String clickurl, String topcolor, JSONObject data) {
        /**
         * 做个查询，access token是否过期
         *
         */
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
        System.out.println("发送微信消息返回信息：" + resultJson.get("errcode")+" "+errmsg);

        // 如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
        if (!"ok".equals(errmsg)) {

            return "error";

        }

        return "success";
    }
}
