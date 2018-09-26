package com.scanpj.work.function;

import android.text.TextUtils;
import android.widget.TextView;

import com.scanpj.work.entity.AnotherScanInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alie on 2018/6/22.
 * 类描述
 * 版本
 */

public class FunctionScanInfoList {


    public List<AnotherScanInfo> getTagetScanInfoInStartCheck(List<AnotherScanInfo> list) {

        for (int i = list.size() - 1; i >= 0; i--) {


            AnotherScanInfo anotherScanInfo = list.get(i);
            if (TextUtils.isEmpty(anotherScanInfo.getCode())
                    && TextUtils.isEmpty(anotherScanInfo.getRingid())) {
                list.remove(i);
            }
        }
        return list;
    }


    public List<AnotherScanInfo> getTagetScanInfoInCloseCheck(List<AnotherScanInfo> list) {


        boolean result = false;
        for (int i = 0; i < list.size(); i++) {


            AnotherScanInfo anotherScanInfo = list.get(i);

            String code = anotherScanInfo.getCode();
            String ringId = anotherScanInfo.getRingid();
            if (TextUtils.isEmpty(code) || TextUtils.isEmpty(ringId)) {
                result = true;
            }


        }
        if (!result) {
            list.add(new AnotherScanInfo());
        }


        return list;
    }


    public List<AnotherScanInfo> getSelectAnotherScanInfoListFromSelectMap(List<Integer> integers, List<AnotherScanInfo> list) {

        List<AnotherScanInfo> targetList = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            AnotherScanInfo anotherScanInfo = list.get(integers.get(i));
            targetList.add(anotherScanInfo);
        }

        return targetList;

    }


    public List<AnotherScanInfo> getUnSelectAnotherScanInfoListFromSelectMap(List<Integer> integers, List<AnotherScanInfo> list) {

        List<AnotherScanInfo> targetList = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            AnotherScanInfo anotherScanInfo = list.get(integers.get(i));
            targetList.add(anotherScanInfo);
        }

        return targetList;
    }


    public void doDealTempScanList(List<AnotherScanInfo> list, List<String> tempDataList) {

        for (int i = 0; i < list.size(); i++) {

            AnotherScanInfo anotherScanInfo = list.get(i);

            String ringId = anotherScanInfo.getRingid();
            String code = anotherScanInfo.getCode();


           if(!TextUtils.isEmpty(ringId)) {

               if(tempDataList.contains(ringId)) {
                   tempDataList.remove(ringId);
               }

           }

           if(!TextUtils.isEmpty(code)) {
               if(tempDataList.contains(code)) {
                   tempDataList.remove(code);
               }
           }

        }

    }
}
