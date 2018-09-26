package com.scanpj.work.universal.msgcode.impl;


import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.universal.http.CallServer;
import com.scanpj.work.universal.http.HttpSingleResponseListener;
import com.scanpj.work.universal.http.RequestPacking;
import com.scanpj.work.universal.http.requestparam.MsgCodeParam;
import com.scanpj.work.universal.msgcode.IMsgAuthCode;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by admin on 2018/5/8.
 * 类描述    手机验证码 相关功能
 * 版本
 */
public class IMsgAuthCodeImpl implements IMsgAuthCode {




    private MsgCodeParam msgCodeParam;


    public IMsgAuthCodeImpl() {
        this.msgCodeParam = new MsgCodeParam();
    }

    @Override
    public void doGeneratePhonePassCode(String url, String phone, String options, final OnDataBackListener onDataBackListener) {
        String param = msgCodeParam.getGeneratePhoneCodeParam(phone, options);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, param);

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
                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });
    }

    @Override
    public void doValidatePhonePassCode(String url, String phone, String pass_code, final OnDataBackListener onDataBackListener) {


        String param = msgCodeParam.getValidatePhoneCodeParam(phone, pass_code);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, param);

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
                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });


    }
}