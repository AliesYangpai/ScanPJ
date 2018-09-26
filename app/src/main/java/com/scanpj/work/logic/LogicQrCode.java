package com.scanpj.work.logic;

import com.scanpj.work.constant.ConstSign;

import java.util.List;
import java.util.Map;

/**
 * Created by Alie on 2018/6/12.
 * 类描述  二维码相关逻辑类
 * 版本
 */

public class LogicQrCode {


    public boolean isCode(String str) {

        return str.contains(ConstSign.COLON) && str.contains(ConstSign.INCLINE_2);

    }


    /**
     * 判断是否含有ringId
     *
     * @param list
     * @return
     */
    public boolean isTempContainsRingId(List<String> list, String ringId) {

        return list.contains(ringId);


    }


    /**
     * 判断是否含有code
     *
     * @param list
     * @return
     */
    public boolean isTempContainsCode(List<String> list,String code) {
        return list.contains(code);

    }


}
