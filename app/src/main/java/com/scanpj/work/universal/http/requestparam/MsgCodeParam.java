package com.scanpj.work.universal.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Alie on 2018/1/13.
 * 类描述
 * 版本
 */

public class MsgCodeParam extends BaseParam{


    /**
     * 生成验证码的参数
     * @param phone
     * @param options Anyway
     * @return
     */
    public String getGeneratePhoneCodeParam(String phone, String options) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("phone",phone);
        jsonObject.addProperty("options",options);
        return jsonObject.toString();
    }


    /**
     * 验证收集验证码
     * @param phone
     * @param pass_code
     * @return
     */
    public String getValidatePhoneCodeParam(String phone, String pass_code) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("phone",phone);
        jsonObject.addProperty("pass_code",pass_code);
        return jsonObject.toString();
    }




}
