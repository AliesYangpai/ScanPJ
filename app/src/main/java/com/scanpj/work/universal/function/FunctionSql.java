package com.scanpj.work.universal.function;

import com.scanpj.work.constant.ConstSign;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.Map;

/**
 * Created by Alie on 2018/6/13.
 * 类描述
 * 版本
 */

public class FunctionSql {





    public String getStringFromMap(Map map) {


        String targe = "";
        for (Object v : map.values()) {

            Integer value = (Integer) v;
            targe =targe+ String.valueOf(value)+ ConstSign.DOU_COMMA;
        }


        if(!TextUtil.isEmpty(targe)) {

            targe =  ConstSign.LEFT_BRACE + targe.substring(0,targe.length() - 1) +ConstSign.RIGHT_BRACE;
        }

        return targe;
    }




}
