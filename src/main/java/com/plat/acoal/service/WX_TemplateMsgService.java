package com.plat.acoal.service;

import com.alibaba.fastjson.JSONObject;
import com.plat.acoal.model.TemplateData;

import java.util.Map;

public interface WX_TemplateMsgService {

    /**
     * 封装模板详细信息
     *
     * @return
     */
    JSONObject packJsonmsg(Map<String, TemplateData> param);

    /**
     * 根据模板的编号 新增并获取模板ID
     *
     * @param templateSerialNumber 模板库中模板的 "编号"
     * @return 模板ID
     */

    String getWXTemplateMsgId(String templateSerialNumber);

    String sendWechatMsgToUser(String touser, String templatId, String clickurl, String topcolor,
                               JSONObject data);

}
