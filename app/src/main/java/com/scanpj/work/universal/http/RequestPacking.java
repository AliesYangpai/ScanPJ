package com.scanpj.work.universal.http;

import android.util.Base64;
import android.util.Log;


import com.scanpj.work.constant.ConstSp;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.universal.cache.sp.SpUtil;
import com.yanzhenjie.nohttp.Binary;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;


/**
 * Created by Administrator on 2017/4/27.
 * 类描述  封装的请求方法
 * 版本
 */

public class RequestPacking {


    /**
     * GET
     * POST
     * DELETE
     * PUT
     */


    private Request<String> request;

    private static volatile RequestPacking mInstance;

    public static RequestPacking getInstance() {
        if(null == mInstance) {
            synchronized (RequestPacking.class) {
                if(null == mInstance) {
                    mInstance = new RequestPacking();
                }
            }
        }
        return mInstance;

    }





    /**
     * 1.获取token，这个方法是适用于登陆时候获取token
     *
     * @param url
     * @param requestMethod
     * @param userName
     * @param pass
     * @return
     */
    public Request<String> getTokenInBascAuthorization(String url, RequestMethod requestMethod, String userName, String pass) {

        request = NoHttp.createStringRequest(url, requestMethod);

        request.addHeader(HttpConst.CONTENT_TYPE, HttpConst.CONTENT_TYPE_APPLICATION);

        String string = new String(Base64.encode((userName + ":" + pass).getBytes(), Base64.DEFAULT));

        String author = HttpConst.BASIC + string.trim();

        request.addHeader(HttpConst.AUTHORIZATION, author);

        return request;

    }


    /**
     * 用于测试
     * @param url
     * @param requestMethod
     * @param param
     * @return
     */
    public Request<String> getRequestParamForJsonNoToken(String url, RequestMethod requestMethod, String param) {

        request = NoHttp.createStringRequest(url, requestMethod);
        request.addHeader(HttpConst.CONTENT_TYPE, HttpConst.CONTENT_TYPE_APPLICATION);

        if (param != null) {

            request.setDefineRequestBodyForJson(param);

        }
        Log.i(HttpConst.LOG_REQUEST, "URL：" + url + " 请求方法：" + requestMethod + " 请求参数：" + param);

        return request;

    }



    /**
     * 一般请求数据
     *
     * @param url
     * @param requestMethod
     * @param param
     * @return
     */
    public Request<String> getRequestParamForJson(String url, RequestMethod requestMethod, String param) {

        request = NoHttp.createStringRequest(url, requestMethod);

        request.addHeader(HttpConst.CONTENT_TYPE, HttpConst.CONTENT_TYPE_APPLICATION);

        request.addHeader(HttpConst.AUTHORIZATION, HttpConst.BEARER + SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_TOKEN, ""));

        if (param != null) {

            request.setDefineRequestBodyForJson(param);

        }
        Log.i(HttpConst.LOG_REQUEST, "URL：" + url + " 请求方法：" + requestMethod + " 请求参数：" + param);

        return request;

    }






    /**
     * set传递token的请求
     *
     * @param url
     * @param requestMethod
     * @param param
     * @return
     */
    public Request<String> getRequestParamSetTokenForJson(String url, RequestMethod requestMethod, String token, String param) {

        request = NoHttp.createStringRequest(url, requestMethod);

        request.addHeader(HttpConst.CONTENT_TYPE, HttpConst.CONTENT_TYPE_APPLICATION);

        if(!TextUtil.isEmpty(token)) {

            request.addHeader(HttpConst.AUTHORIZATION, HttpConst.BEARER + token);
        }


        if (param != null) {

            request.setDefineRequestBodyForJson(param);

        }
        Log.i(HttpConst.LOG_REQUEST, "URL：" + url + " 请求方法：" + requestMethod + " 请求参数：" + param);

        return request;

    }




    /**
     * 单张图片上传接口
     *
     * @param url
     * @param requestMethod
     * @return
     */
    public Request<String> getRequestParamForUpLoad(String url, FileBinary fileBinary, RequestMethod requestMethod) {

        request = NoHttp.createStringRequest(url, requestMethod);

//        request.addHeader(HttpConst.CONTENT_TYPE, HttpConst.CONTENT_TYPE_APPLICATION);

        request.addHeader(HttpConst.AUTHORIZATION, HttpConst.BEARER + SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_TOKEN, ""));

        request.add(HttpConst.UPLOAD_DATA_KEY, fileBinary);

        request.add(HttpConst.UPLOAD_DATA_KEY_FILE_NAME, HttpConst.UPLOAD_DATA_VALUE_NAME);

        Log.i(HttpConst.LOG_REQUEST, "URL：" + url + " 请求方法：" + requestMethod );

        return request;

    }





    /**
     * 多文件上传接口
     *
     * @param url
     * @param requestMethod
     * @return
     */
    public Request<String> getRequestParamForUpLoadMulti(String url, List<Binary> binaries, RequestMethod requestMethod) {

        request = NoHttp.createStringRequest(url, requestMethod);

//        request.addHeader(HttpConst.CONTENT_TYPE, HttpConst.CONTENT_TYPE_APPLICATION);

        request.addHeader(HttpConst.AUTHORIZATION, HttpConst.BEARER + SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_TOKEN, ""));

        request.add(HttpConst.UPLOAD_DATA_KEY, binaries);

        request.add(HttpConst.UPLOAD_DATA_KEY_FILE_NAME, HttpConst.UPLOAD_DATA_VALUE_NAME);

        Log.i(HttpConst.LOG_REQUEST, "URL：" + url + " 请求方法：" + requestMethod );

        return request;

    }



}
