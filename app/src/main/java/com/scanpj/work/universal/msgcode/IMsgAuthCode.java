package com.scanpj.work.universal.msgcode;


import com.scanpj.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/1/13.
 * 类描述 短信验证码相关的方法
 * 版本
 */

public interface IMsgAuthCode {


    /**
     * 获取短信验证码
     *
     * @param url
     * @param phone
     * @param options            ：Anyway
     * @param onDataBackListener
     */

    void doGeneratePhonePassCode(String url,
                                 String phone,
                                 String options, OnDataBackListener onDataBackListener);


    /**
     * 验证验证码
     * @param url
     * @param phone
     * @param onDataBackListener
     */
    void doValidatePhonePassCode(String url,
                                 String phone,
                                 String pass_code,
                                 OnDataBackListener onDataBackListener);
}
