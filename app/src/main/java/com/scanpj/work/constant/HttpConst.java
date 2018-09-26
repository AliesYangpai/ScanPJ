package com.scanpj.work.constant;

import com.welink.utils.CommonUtils;

/**
 * Created by Administrator on 2016/9/17 0017.
 * 类描述   网络交互的所有常量
 * 版本
 */
public class HttpConst {


    public static final String HTTP_PREFIX = "http://";

    public static final String LOG_REQUEST = "log_request";

    public static final String SERVER_BACK = "server_back";

    public static final String CONTENT_TYPE = "Content-Type";


    public static final String CONTENT_TYPE_APPLICATION = "application/json; charset=UTF-8";

//    public static final String BASE_PRIMARY_URL = "http://agriculture.iot.gosuncnwelink.com/";  //正式环境
    public static final String BASE_PRIMARY_URL = "http://36.41.184.66:8888/JDChicken/";   //测试环境

    public static final String API_VERSION = "JDChicken/faughter/";


    public static final int CODE_200 = 200;//success回调中使用的返回码


    public static final int CODE_201 = 201;//success回调中使用的返回码


    public static final int CODE_204 = 204;//success回调中使用的返回码


    public static final int CODE_0 = 0;//网络链接异常

    public static final int CODE_401 = 401;//未授权


    public static final int CODE_502 = 502;//服务器异常

    public static final int NO_RESOURCE = 204;//未知资源，比如更新数据时，没有找到该数据


    public static final String UPLOAD_DATA_KEY = "data";

    public static final String UPLOAD_DATA_KEY_FILE_NAME = "filename";


    public static final String UPLOAD_DATA_VALUE_NAME = "img.jpg";

    public static final int HTTP_WHAT = 0;//当前的请求标记


    public static final int NO_HTTP_CONNECT_ERROR = 0;

    public static final int SERVER_ERROR = 502;//服务器异常（比如服务器没网了）

    public static final int UNAUTHORIZED = 401;// 未授权、token过期等等;


    public static final String AUTHORIZATION = "Authorization";//授权

    public static final String BASIC = "Basic ";//获取token时候，需要加入的basci

    public static final String BEARER = "Bearer ";
    public static final String SEPARATOR = "/";//分隔符（用于get请求中有参数在URL中即可）



    public static final int CODE_SUCCESS = 0;//code

    public class URL {


        /**
         * 888888888888888888888原wx请求接口8888888888888888888888888888888
         */

        /**
         * 获取扫描结果数据
         * get方法获取code
         */
        public static final String GET_SCAN_RESULT = BASE_PRIMARY_URL + "chiken/data_fingRingOfCode.action";


        /**
         * 添加扫描数据
         * get方法获取code
         */
        public static final String GET_SCAN_ADD = BASE_PRIMARY_URL + "chiken/data_QrAdd.action";


        /**
         * *****************************项目接口************************************
         */

        /**
         * 获取鸡场列表
         * get方法获取code
         */
        public static final String GET_ALL_CHICK_HOUSE = BASE_PRIMARY_URL + "faughter/fau_getAllHouses.action";


        /**
         * get方法
         * 获取根据鸡场id获取批次信息
         */
        public static final String GET_BATCH_INFO_LIST = BASE_PRIMARY_URL + "faughter/fau_getBatchByHouseId.action";


        /**
         * get方法
         * 获取根据鸡场id获取批次信息
         */
        public static final String SET_RING_INFO = BASE_PRIMARY_URL + "faughter/fau_setRingInfo.action";

    }





    /**
     * 加密相关
     * userName
     * password
     * sign
     */


    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_PASS_WORD = "password";
    public static final String KEY_SIGN = "sign";

    public static final String VALUE_USER_NAME = "duchao";
    public static final String VALUE_PASS_WORD = "123456";


    public static final String VALUE_PASS_WORD_ENCRIYTION = CommonUtils.encryptPwd(HttpConst.VALUE_PASS_WORD, HttpConst.VALUE_USER_NAME);
    public static final String VALUE_SIGN_ENCRIYTION = CommonUtils.encrySign(HttpConst.VALUE_USER_NAME);


    /**
     * 组装的加密
     */
    public static final String ENCRIYTION = ConstSign.QUESTION_MARK+HttpConst.KEY_USER_NAME+ConstSign.EQUAL_SIGN+HttpConst.VALUE_USER_NAME+ConstSign.AND+
            KEY_PASS_WORD+ConstSign.EQUAL_SIGN+VALUE_PASS_WORD_ENCRIYTION+ConstSign.AND+
            KEY_SIGN+ConstSign.EQUAL_SIGN+VALUE_SIGN_ENCRIYTION;



    public static final String  REQUEST_KET_HOUSEID = "houseId";
}
