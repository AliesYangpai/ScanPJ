package com.scanpj.work.constant;

/**
 * Created by Administrator on 2016/9/19 0019.
 * 类描述  Sp的常量
 * 版本
 */
public class ConstSp {

    public static final String ZTE_SCAN_SP_NAME = "zte_scan_sp";

    public static final int SP_OPEN = 0;

    /**
     * 首次启动的限制
     */
    public static final String SP_KEY_LOAD_OR_NOT = "load_or_not";

    /**
     * 用户是否已经导入了
     */

    public static final String SP_KEY_IS_LOAD_DATA = "is_load_data";


    /**
     * 输入的对比数据
     */

    public static final String SP_KEY_ENTER_STEP = "enter_step";



    /**
     * 登陆返回的token
     */


    public static final String SP_KEY_TOKEN = "token";



    /**
     * 相关value
     */
    public class SP_VALUE {

        public static final String DEFAULT_STRING = "";





        public static final boolean DEFAULT_BOOLEAN = false;

        public static final int DEFAULT_INT = 0;

        /**
         * 用户已登陆
         */
        public static final boolean IS_LOGIN = true;



   
    }

}
