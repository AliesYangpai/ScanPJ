package com.scanpj.work.method.impl;

import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.method.IChickenBatch;
import com.scanpj.work.universal.http.CallServer;
import com.scanpj.work.universal.http.HttpSingleResponseListener;
import com.scanpj.work.universal.http.RequestPacking;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Alie on 2018/6/13.
 * 类描述
 * 版本
 */

public class IChickenBatchImpl implements IChickenBatch {


    @Override
    public void doGetChickenBatchRecords(String url, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.GET, null);


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
