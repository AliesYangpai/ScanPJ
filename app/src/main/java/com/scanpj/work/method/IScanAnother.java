package com.scanpj.work.method;

import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.listener.OnDataBackListener;

import java.util.List;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public interface IScanAnother {

    void doGetAnotherScanRecords(String url, OnDataBackListener onDataBackListener);


    void doCommitScanResult(String url, List<AnotherScanInfo> list, OnDataBackListener onDataBackListener);
}
