package com.scanpj.work.universal.http.requestparam;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.entity.ChickenInfoScanAbout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alie on 2018/6/12.
 * 类描述  扫描的参数
 * 版本
 */

public class ScanAnotherParam extends BaseParam{



    /**
     * 生成验证码的参数
     * @return
     */
    public String getparam(List<AnotherScanInfo> list) {




        List<String> listCodes = new ArrayList<>();
        List<String> listRingIds = new ArrayList<>();
        for (AnotherScanInfo anotherScanInfo:list) {

            if(!TextUtils.isEmpty(anotherScanInfo.getRingid()) && !TextUtils.isEmpty(anotherScanInfo.getCode())){
                listCodes.add(anotherScanInfo.getCode());
                listRingIds.add(anotherScanInfo.getRingid());
            }
        }

        JsonObject jsonObject = getJsonObject();

        Type type =new TypeToken<List<String>>() {}.getType();

        JsonArray jsonArryCode = new Gson().toJsonTree(listCodes, type).getAsJsonArray();
        JsonArray jsonArryRing = new Gson().toJsonTree(listRingIds, type).getAsJsonArray();


        jsonObject.add("rings",jsonArryRing);
        jsonObject.add("codes",jsonArryCode);


        return jsonObject.toString();
    }


}
