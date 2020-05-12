package com.plat.acoal.utils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.expression.ParseException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetGZHOpenId {

    /**
     * 获取公众号openidUrl
     *
     *
     * @author Administrator
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     *
     */

    public static String getopendidUrl(String url) throws UnsupportedEncodingException {

        String appid = "wxd09cb80b21a89012";
        // http://localhost:8080
        // https://wx.cnsbdz.com/wxbind/dobind
        String redirect_uri = URLEncoder.encode("https://wx.cnsbdz.com/wxbind/dobind", "UTF-8");
        String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize"

                + "?appid=APPID"

                + "&redirect_uri=REDIRECT_URI"

                + "&response_type=code"

                + "&scope=snsapi_base"

                + "&state=STATE"

                + "#wechat_redirect";

        url1 = url1.replace("APPID", appid)

                .replace("REDIRECT_URI", redirect_uri);

        return url1;

    }

    /**
     * 获取公众号openid
     *
     */

    public static String getopendid(String code)
            throws ParseException, IOException,  URISyntaxException {

        String appid = "wxd09cb80b21a89012";

        String secret = "c83d4fc7ee2cb39da80780f8c55e2fa0";

        // 获取授权access token and openid
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token"

                + "?appid=AppId"

                + "&secret=AppSecret"

                + "&code=CODE"

                + "&grant_type=authorization_code";

        url = url.replace("AppId", appid)

                .replace("AppSecret", secret)

                .replace("CODE", code);

        JSONObject jsonTexts = WX_HttpsUtil.httpsRequest(url, "GET", null);

        String openid = "";

        if (jsonTexts.get("openid") != null) {

            openid = jsonTexts.get("openid").toString();

        }


        return openid;

    }

}