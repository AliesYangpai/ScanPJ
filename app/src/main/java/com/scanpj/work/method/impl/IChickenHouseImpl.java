package com.scanpj.work.method.impl;

import android.util.Log;

import com.scanpj.work.constant.ConstLog;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.method.IChickenHouse;
import com.scanpj.work.universal.http.CallServer;
import com.scanpj.work.universal.http.HttpSingleResponseListener;
import com.scanpj.work.universal.http.RequestPacking;
import com.scanpj.work.universal.http.requestparam.ChickenHouseParam;
import com.welink.utils.CommonUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public class IChickenHouseImpl implements IChickenHouse {
    private ChickenHouseParam chickenHouseParam;


    public IChickenHouseImpl() {
        this.chickenHouseParam = new ChickenHouseParam();
    }


    @Override
    public void doGetChickenHouseRecords(String url, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.GET, null);

//        String password = CommonUtils.encryptPwd("123456", "duchao");
//        String sign = CommonUtils.encrySign("duchao");
//        request.add("userName","duchao");
//        request.add("password",password);
//        request.add("sign",sign);
//        request.add("houseId",3);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                onDataBackListener.onStart();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {

                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {
                onDataBackListener.onFail(response.responseCode(), response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });
    }
}
