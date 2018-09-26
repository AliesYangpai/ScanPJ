package com.scanpj.work.universal.http.requestparam;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.scanpj.work.entity.ChickenInfoScanAbout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alie on 2018/1/13.
 * 类描述
 * 版本
 */

public class ChickenParam extends BaseParam {


    /**
     * 获取鸡场详情
     *
     * @param
     * @return
     */
    public String getChickens(List<ChickenInfoScanAbout> list) {

        JsonObject jsonObject = getJsonObject();
        Type type =new TypeToken<List<ChickenInfoScanAbout>>() {}.getType();
        JsonArray jsonArray = new Gson().toJsonTree(list, type).getAsJsonArray();

        jsonObject.add("array",jsonArray);

;

        return jsonObject.toString();
    }


}
