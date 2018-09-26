package com.scanpj.work.ui;

/**
 * Created by Alie on 2017/11/3.
 * 类描述  基础iview类，用来接收P层数据 并将数据显示到界面上 这是最基础的类
 * 版本
 */

public interface IBaseView {


    void showLoadingDialog();

    void dismissLoadingDialog();

    void onDataBackFail(int code, String errorMsg);


}
