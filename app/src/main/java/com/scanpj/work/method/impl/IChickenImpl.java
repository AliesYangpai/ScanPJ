package com.scanpj.work.method.impl;

import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.method.IChicken;
import com.scanpj.work.universal.http.CallServer;
import com.scanpj.work.universal.http.HttpSingleResponseListener;
import com.scanpj.work.universal.http.RequestPacking;
import com.scanpj.work.universal.http.requestparam.ChickenParam;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public class IChickenImpl implements IChicken {

    private ChickenParam chickenParam;


    public IChickenImpl() {

        chickenParam = new ChickenParam();
    }

    @Override
    public void doSetScanResault(String url, List<ChickenInfoScanAbout> list, final OnDataBackListener onDataBackListener) {

        String param = chickenParam.getChickens(list);

        Request<String> request =
                RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, param);


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
