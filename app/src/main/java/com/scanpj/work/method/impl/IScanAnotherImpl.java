package com.scanpj.work.method.impl;

import android.util.Log;

import com.scanpj.work.constant.ConstLog;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.method.IScanAnother;
import com.scanpj.work.universal.http.CallServer;
import com.scanpj.work.universal.http.HttpSingleResponseListener;
import com.scanpj.work.universal.http.RequestPacking;
import com.scanpj.work.universal.http.requestparam.ScanAnotherParam;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class IScanAnotherImpl implements IScanAnother {


    private ScanAnotherParam scanAnotherParam;

    public IScanAnotherImpl() {
        this.scanAnotherParam = new ScanAnotherParam();
    }

    @Override
    public void doGetAnotherScanRecords(String url, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);


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

    @Override
    public void doCommitScanResult(String url, List<AnotherScanInfo> list, final OnDataBackListener onDataBackListener) {

        String param = scanAnotherParam.getparam(list);

        Log.i(ConstLog.REQUEST_PARAM,param);
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.POST,
                param);


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
