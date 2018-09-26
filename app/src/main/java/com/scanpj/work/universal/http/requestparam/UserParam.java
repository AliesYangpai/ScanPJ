package com.scanpj.work.universal.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Alie on 2018/1/13.
 * 类描述
 * 版本
 */

public class UserParam extends BaseParam{




    /**
     * 用户注册的参数
     * @param phone
     * @param user_name
     * @param password
     * @param pass_code
     * @param user_points
     * @return
     */
    public String getRegisterParam(
            String phone,
            String user_name,
            String password,
            String pass_code,
            String user_points) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("phone",phone);
        jsonObject.addProperty("user_name",user_name);
        jsonObject.addProperty("password",password);
        jsonObject.addProperty("pass_code",pass_code);
        jsonObject.addProperty("user_points",user_points);
        return jsonObject.toString();
    }




    /**
     * 验证收集验证码
     * @param user_name
     * @param password
     * @return
     */
    public String getLogonParam(String user_name, String password) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("user_name",user_name);
        jsonObject.addProperty("password",password);
        return jsonObject.toString();
    }







    /**
     * 编辑用户信息
     * @param user_name
     * @param address
     * @return
     */
    public String getEditUserInfoParam(String user_name, String address) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("UserName",user_name);
        jsonObject.addProperty("Address",address);
        return jsonObject.toString();
    }

}
