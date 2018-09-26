package com.scanpj.work.constant;

/**
 * Created by Administrator on 2016/9/22 0022.
 * 类描述  intent常量
 * 版本
 */
public class ConstIntent {

    /**
     * bundleKey
     */
    public class BundleKEY {



        public static final String DELIVER_CHICKEN_HOUSE_DETIAL = "deliver_chicken_house_detial";//传递鸡场实体类


        public static final String DELIVER_TAG_RELOAD = "ireload";//重新导入

    }


    public class BundleValue {


        public static final String DO_RELOAD = "do_reload";//重新导入数据
        public static final String DEFAULT_STRING = "";//默认String类型数据

        public static final int DRIVER_FIND_PASS = 1; //司机找回密码
        public static final int BUSINESS_FIND_PASS = 27; //企业找回密码
        public static final int DRIVER_REG = 2; //司机注册界面跳转value
        public static final int COMPANY_REG = 3; //企业注册界面跳转value

        public static final int REG_DRIVER_UPLOAD = 4; //司机上传图片
        public static final int REG_COMPANY_UPLOAD = 5; //企业上传图片

        public static final int DRIVER_STUDY_AND_EXAM = 6; //司机学习和考试
        public static final int COMPANY_STUDY_AND_EXAM = 7;//企业学习和考试

        public static final int DRIVER_TO_MAP = 8;//司机进入到地图界面
        public static final int COMPANY_TO_MAP = 9;//企业进入到地图界面

        public static final int GRAB_TO_ORDER_DETAIL_NOT_TAKE = 10;//抢单列表进入到界面详情（还未接单）
        public static final int BIDDING_TO_ORDER_DETAIL_NOT_TAKE = 11;//竞价列表进入到界面详情（还未接单）


        public static final int ORDER_TYPE_MY_APPOINT = 12;//我的指派界面
        public static final int ORDER_TYPE_MY_GRAB = 13;//我的抢单
        public static final int ORDER_TYPE_MY_BIDDING = 14;//我的竞价


        public static final int CHANGE_LOGIN_PASS = 15;//进入到修改登陆密码

        public static final int CHANGE_WITHDRAWW_PASS = 16;//进入到修改提现密码


        public static final int SET_START_PLACE = 17;//设置开始地点


        public static final int SET_END_PLACE = 18;//设置终止地点


        public static final int SET_PAYPASS = 19;//设置支付密码密码

        public static final int CONFIRM_PAYPASS = 20;//验证支付密码密码


        public static final int BIDSUCCESS_HAS_TAKE = 21;//竞价成功界面跳转到我的竞价界面，【已接单】

        public static final int BIDSUCCESS_TAKE_GOODS = 22;//竞价成功界面跳转到我的竞价界面，【取货中】

        public static final int BIDSUCCESS_TRAVELLING = 23;//竞价成功界面跳转到我的竞价界面，【进行中】

        public static final int BIDSUCCESS_FINISH = 24;//竞价成功界面跳转到我的竞价界面，【已完成】



        public static final int REG_TO_SET_VEHICLE_NO = 25;//注册时候设置车牌号


        public static final int ADD_TO_SET_VEHICLE_NO = 26;//添加车辆时，设置车牌号




        public static final int BUSINESS_SHOW_DRIVER_DETIAL = 29;//对应BUSINESS_SHOW_DRIVER_DETAIL_MODE 这个是显示司机详情

        public static final int BUSINESS_ADD_DRIVER_DETIAL = 30;//对应BUSINESS_SHOW_DRIVER_DETAIL_MODE 这个是显示司机详情【添加司机的时候】
    }









    /**
     * requestCode,activty跳转时，携带的请求码
     */

    public class RequestCode {




        public static final int APPLY_FOR_PERMISSION_UPLOAD =202;//申请上传拍照本地请求码
        public static final int APPLY_FOR_PERMISSION_SWEEP =201;//申请权限扫码的本地请求码
        public static final int APPLY_FOR_PERMISSION =200;//申请权限扫码的本地请求码

        public static final int SYSYEM_SETTING = 400;//到系统中设置


        public static final int GO_TO_BATCH_RECODS = 11;//编辑用户信信息


    }

    /**
     * responseCode,目标activty销毁时，携带的返回码
     */


    public class ResponseCode {

        public static final int BATCH_LOAD_SUCCESS = 11; //批次数据加载成功

    }



    /**
     * 广播相关
     */
    public class IntentAction {

        public static final String USER_EDIT_SUCCESS = "user_edit_success";

        public static final String AWAKE_WATCH_1 = "anction_awake_watch1";//


        public static final String AWAKE_WATCH_2 = "anction_awake_watch2";//


    }




    /**
     * 首页默认订单标记
     */
    public class OrderMainDefault {

        public static final int ORDER_ABOUT_DEFUALT = 1000;  //默认外层订单default

        public static final int ORDER_CHILDE_ABOUT_DEFAULT = 1001;//默认childeDEFAULT


    }

}
