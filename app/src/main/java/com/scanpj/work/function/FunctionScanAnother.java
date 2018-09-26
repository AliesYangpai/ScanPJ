package com.scanpj.work.function;

import com.scanpj.work.entity.AnotherScanInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alie on 2018/6/22.
 * 类描述
 * 版本
 */

public class FunctionScanAnother {


    public List<AnotherScanInfo> getSelectAnotherScanInfoListFromSelectMap(List<Integer> integers, List<AnotherScanInfo> list) {

        List<AnotherScanInfo> targetList = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            AnotherScanInfo anotherScanInfo = list.get(integers.get(i));
            targetList.add(anotherScanInfo);
        }

        return targetList;

    }
}
