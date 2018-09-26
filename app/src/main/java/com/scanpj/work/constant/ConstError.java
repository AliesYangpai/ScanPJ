package com.scanpj.work.constant;

/**
 * Created by Alie on 2017/11/14.
 * 类描述
 * 版本
 */

public class ConstError {



    public static final int DEFUAL_ERROR_CODE = 1000;
    public static final String PARSE_ERROR_MSG = "请求失败";

    public static final int DEFUAL_ERROR_LOCAL_SEL_CODE = 1001;
    public static final String PARSE_ERROR_LOCAL_SEL_MSG = "本地查询异常";




    public static final int DEFUAL_ERROR_CODE_NO_TOKEN = 1002;
    public static final String PARSE_ERROR_NO_THIRD_TOKEN = "没有获取到第三方Token";



    public static final int ERROR_ALI_CONFIREM_CODE = 1003;
    public static final String ERROR_ALI_CONFIREM_MSG = "支付验签未通过";


    public static final int ERROR_WX_CONFIREM_CODE = 1004;
    public static final String ERROR_WX_CONFIREM_MSG = "微信验签未通过";




    public static final int ERROR_WX_BIND_CODE = 1005;
    public static final String ERROR_WX_BIND_MSG = "微信已经绑定，但无token信息";


    public static final int ERROR_WX_BIND = 10051;
    public static final String ERROR_WX_BIND_ALREADY_MSG = "该微信微信已经绑定账号，请更换微信";




    public static final int ERROR_ALI_BIND_CODE = 1006;
    public static final String ERROR_ALI_BIND_MSG = "支付宝已经绑定，但无token信息";




    public static final String ERROR_DEVICE_SCAN_INIT_MSG = "设备初始化失败";
    public static final String ERROR_DEVICE_SCAN_CANCEL_MSG = "扫描取消";
    public static final String ERROR_DEVICE_SCAN_TIME_OUT_MSG = "扫描超时";
    public static final String ERROR_DEVICE_SCAN_UNKNOWN_MSG = "未知扫描异常";
}
