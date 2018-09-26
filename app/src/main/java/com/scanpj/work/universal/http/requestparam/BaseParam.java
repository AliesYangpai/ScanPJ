package com.scanpj.work.universal.http.requestparam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * Created by Alie on 2017/11/14.
 * 类描述
 * 版本
 */

public class BaseParam {




    public JsonObject getJsonObject() {
        return new JsonObject();
    }


    /**
     * 获取jsonElement
     *  用于将object转化json实体类对象
     * @param object
     * @return
     */
    public JsonElement getJsonElement(Object object) {



        return new Gson().toJsonTree(object);
    }



}
