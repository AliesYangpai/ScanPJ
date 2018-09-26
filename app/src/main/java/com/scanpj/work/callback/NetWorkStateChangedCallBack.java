package com.scanpj.work.callback;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述  网络状态的判断回调
 * 版本
 */

public interface NetWorkStateChangedCallBack {


    void onNetChangeToWifi();


    void onNetChangeToMobel();


    void onNetChangeNoConnect();
}
