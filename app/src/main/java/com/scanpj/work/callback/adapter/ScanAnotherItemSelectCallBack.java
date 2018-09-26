package com.scanpj.work.callback.adapter;

import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.entity.BatchInfo;

import java.util.Map;

/**
 * Created by Alie on 2018/6/15.
 * 类描述  批次列表中,adapter的回调
 * 版本
 */

public interface ScanAnotherItemSelectCallBack {

    void onItemDoSelect(Map<Integer, Boolean> map,int position);

    void onItemDoUnSelect(Map<Integer, Boolean> map,int position);
}
