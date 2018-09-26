package com.scanpj.work.constant;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述  手机配置相关信息
 * 版本
 */

public class ConstDbRemote {

    /**
     * 测试环境
     */
    public static final String REMOTE_IP = "36.41.184.66:3306";
    public static final String DB_NAME = "chicken";
    public static final String USER_NAME = "root";
    public static final String PASS_WORD = "123456";



    /**
     * 正环境
     */
//    public static final String REMOTE_IP = "47.104.100.13:3306";
//    public static final String DB_NAME = "chicken";
//    public static final String USER_NAME = "root";
//    public static final String PASS_WORD = "welink@123";




    public static final String URL = "jdbc:mysql://" + REMOTE_IP + ConstSign.INCLINE_1 + DB_NAME;




    public static final String RING_ID = "ringid";
    public static final String HOUSE_ID = "houseid";
    public static final String BATCH_ID = "batchid";
    public static final String TOTAL_STEP = "total";
    public static final String SHORT_URL = "short_url";
    public static final String BATCH_NAME = "name";//批次名称

//    public static final String SQL = "SELECT za.`ringid`,za.`houseid`,za.`batchid`,zs.`total_motion_quantity` AS total,ci.`short_url`" +
//            " FROM z_all_ring za,z_as_ring zs,code_info ci" +
//            " WHERE za.`ringid`=zs.`ring_id`" +
//            " AND za.`qr_code`=ci.`code`" +
//            " AND za.`batchid` IN";




    public static final String SQL = "SELECT za.`ringid`,za.`houseid`,za.`batchid`,zb.`name`,zs.`total_motion_quantity` AS total,ci.`short_url`" +
            " FROM z_all_ring za,z_as_ring zs,code_info ci,z_ring_batch zb" +
            " WHERE za.`ringid`=zs.`ring_id`" +
            " AND za.`qr_code`=ci.`code`" +
            " AND za.`batchid`=zb.`id`" +
            " AND za.`batchid` IN";





}
