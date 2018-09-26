package com.scanpj.work.universal.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Alie on 2018/1/13.
 * 类描述
 * 版本
 */

public class ChickenHouseParam extends BaseParam{











    /**
     * 获取鸡场详情
     * @param chickenHouseId
     * @return
     */
    public String getChickenDetailParam(String chickenHouseId) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("chickenHouseId",chickenHouseId);
        return jsonObject.toString();
    }









}
