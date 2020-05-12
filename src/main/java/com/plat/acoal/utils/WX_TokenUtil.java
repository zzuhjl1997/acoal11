package com.plat.acoal.utils;


import com.plat.acoal.model.AccessToken;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONException;

import com.alibaba.fastjson.JSONObject;


public class WX_TokenUtil {

    private static Logger log = LoggerFactory.getLogger(WX_TokenUtil.class);

    /**
     *
     * 获得微信 AccessToken
     *
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     *
     * 开发者需要access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取
     *
     * 的access_token失效。
     *
     * （此处我是把token存在java缓存里面了）此代码token没有加入缓存,后面附了缓存的工具类,根据需求自己添加
     *
     */

    public static AccessToken getWXToken() {

        String appId = "wxd09cb80b21a89012";

        String appSecret = "c83d4fc7ee2cb39da80780f8c55e2fa0";

        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId
                + "&secret=" + appSecret;

        /**
         * 判断access token是否过期 若未过期，接着用 若已经过期 重新获取 从数据库中找
         */

        JSONObject jsonObject = WX_HttpsUtil.httpsRequest(tokenUrl, "GET", null);

        System.out.println("jsonObject:" + jsonObject);

        AccessToken access_token = new AccessToken();

        if (null != jsonObject) {

            try {

                access_token.setAccesstoken(jsonObject.getString("access_token"));

                //	access_token.setExpiresin(jsonObject.getInteger("expires_in"));

            } catch (JSONException e) {

                access_token = null;

                // 获取token失败

                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"),
                        jsonObject.getString("errmsg"));

            }

        }

        return access_token;

    }

}