package com.scanpj.work.logic;

import android.text.TextUtils;

import com.scanpj.work.entity.AnotherScanInfo;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Alie on 2018/6/14.
 * 类描述
 * 版本
 */

public class LogicScanAnother {


    public boolean isNoScaned(List<AnotherScanInfo> list) {

        AnotherScanInfo anotherScanInfo = list.get(0);
        return TextUtil.isEmpty(anotherScanInfo.getCode()) && TextUtil.isEmpty(anotherScanInfo.getRingid());
    }


    public boolean isFullLine(List<AnotherScanInfo> list) {

        boolean result = false;

        if (list.size() > 1) {
            AnotherScanInfo anotherScanInfo = list.get(list.size() - 1);
            result = TextUtil.isEmpty(anotherScanInfo.getCode()) && TextUtil.isEmpty(anotherScanInfo.getRingid());
        }


        return result;
    }


    public boolean isHasSingleLine(List<AnotherScanInfo> list) {
        boolean result = false;

        if (list.size() > 1) {
            AnotherScanInfo anotherScanInfo = list.get(list.size() - 1);
            result = TextUtil.isEmpty(anotherScanInfo.getCode()) && TextUtil.isEmpty(anotherScanInfo.getRingid());
        }


        for (int i = 0; i < list.size(); i++) {

            AnotherScanInfo anotherScanInfo = list.get(i);
            String code = anotherScanInfo.getCode();
            String ringId = anotherScanInfo.getRingid();

            if (TextUtils.isEmpty(code) || TextUtils.isEmpty(ringId)) {
                result = true;
                break;
            }
        }

        return result;

    }


}
