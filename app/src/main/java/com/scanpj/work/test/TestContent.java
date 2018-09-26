package com.scanpj.work.test;


import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.entity.BatchInfo;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.entity.ChickenInfoRaw;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/15.
 * 类描述
 * 版本
 */
public class TestContent {


    public static final String TEST_BUNDLE_KEY = "test_bundle_key";

    public static final String headUrl = "http://img.sportsv.net/img/photo/image/2/36682/aspect-jcOad0Axdv-500xauto.jpg";

    public static final String headUrl1 = "http://img1.imgtn.bdimg.com/it/u=2989180251,2413232656&fm=214&gp=0.jpg";

    public static final String headUrl2 = "http://www.hkbtv.cn/upload/article/1/2016/11/17/kqz2l0512m0.jpg";


    public static final String APPLICATION_LOCAL_TAG_CHECK_IN = "check_in";//考勤打卡
    public static final String APPLICATION_LOCAL_TAG_TAKE_BREAK = "take_break";//请假
    public static final String APPLICATION_LOCAL_TAG_REISSUE = "reissue";//补卡
    public static final String APPLICATION_LOCAL_TAG_GO_OUT = "go_out";//外出

    public static final String APPLICATION_LOCAL_TAG_BUSINESS_TRIP = "business_trip";//出差
    public static final String APPLICATION_LOCAL_TAG_OVER_TIME = "over_time";//加班
    public static final String APPLICATION_LOCAL_TAG_GOODS = "goods";//物品


    public static final String APPLICATION_LOCAL_TAG_REPORT_DAY = "report_day";//日报
    public static final String APPLICATION_LOCAL_TAG_REPORT_WEEK = "report_week";//周报
    public static final String APPLICATION_LOCAL_TAG_REPORT_MONTH = "report_month";//月报
    public static final String APPLICATION_LOCAL_TAG_REPORT_STATISTICS = "statistics";//统计


    public static final String APPLICATION_LOCAL_TAG_MGR_CLIENT = "mgr_client";//客户管理
    public static final String APPLICATION_LOCAL_TAG_MGR_PRODUCT = "mgr_product";//产品管理
    public static final String APPLICATION_LOCAL_TAG_MGR_ACT = "mgr_act";//活动管理


    public static final String APPLICATION_LOCAL_TAG_ANNOUNCE = "announce";//公告
    public static final String APPLICATION_LOCAL_TAG_DIARY = "diary";//日志
    public static final String APPLICATION_LOCAL_TAG_APPROVE = "approve";//审批


    public static final String html = "<p>\n" +
            "\tThink Defferent\n" +
            "</p>\n" +
            "<p>\n" +
            "\tHere’s to the crazy ones. The misfits. The rebels. The troublemakers. The round pegs in the square holes. The ones who see things differently. They’re not fond of rules. And they have no respect for the status quo. You can quote them, disagree with them, glorify or vilify them. About the only thing you can’t do is ignore them. Because they change things. They push the human race forward. And while some may see them as the crazy ones, we see genius. Because the people who are crazy enough to think they can change the world, are the ones who do.\n" +
            "</p>\n" +
            "<h1>\n" +
            "\t- Apple Inc.大西瓜\n" +
            "</h1>\n" +
            "<p>\n" +
            "\t<img src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517085727052&di=ac23a5f5bea899e9858f1c2cee05a3ea&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa5c27d1ed21b0ef47ecfc962d6c451da81cb3ee2.jpg\" alt=\"\" />\n" +
            "</p>\n" +
            "<p>\n" +
            "\t<br />\n" +
            "</p>";


    public static List<ChickenHouseInfo> getList() {

        List<ChickenHouseInfo> list = new ArrayList<>();
        ChickenHouseInfo chickenHouseInfo = new ChickenHouseInfo();
        chickenHouseInfo.setName("北川鸡场");
        chickenHouseInfo.setAddress("陕西省西安市莲湖区北大街12号");


        ChickenHouseInfo chickenHouseInfo2 = new ChickenHouseInfo();
        chickenHouseInfo2.setName("安丰鸡场");
        chickenHouseInfo2.setAddress("陕西省西安市未央区草滩路12号");
        list.add(chickenHouseInfo);
        list.add(chickenHouseInfo2);



        return list;
    }


    public static List<BatchInfo> getBatchList() {

        List<BatchInfo> list = new ArrayList<>();
        BatchInfo batchInfo = new BatchInfo();
        batchInfo.setName("1碰");
        batchInfo.setId(1);


        BatchInfo batchInfo2 = new BatchInfo();
        batchInfo2.setName("2碰");
        batchInfo2.setId(2);


        BatchInfo batchInfo3 = new BatchInfo();
        batchInfo3.setName("3碰");
        batchInfo3.setId(3);


        BatchInfo batchInfo4 = new BatchInfo();
        batchInfo4.setName("4碰");
        batchInfo4.setId(4);


        list.add(batchInfo);
        list.add(batchInfo2);
        list.add(batchInfo3);
        list.add(batchInfo4);



        return list;
    }


    public static List<ChickenInfoRaw> getChickenRawlist() {


        List<ChickenInfoRaw> list = new ArrayList<>();
        ChickenInfoRaw chickenInfoRaw1 = new ChickenInfoRaw();
        chickenInfoRaw1.setTotal(1);

        ChickenInfoRaw chickenInfoRaw2 = new ChickenInfoRaw();
        chickenInfoRaw2.setTotal(2);

        ChickenInfoRaw chickenInfoRaw3 = new ChickenInfoRaw();
        chickenInfoRaw3.setTotal(3);

        ChickenInfoRaw chickenInfoRaw4 = new ChickenInfoRaw();
        chickenInfoRaw4.setTotal(4);

        ChickenInfoRaw chickenInfoRaw5 = new ChickenInfoRaw();
        chickenInfoRaw5.setTotal(5);
        ChickenInfoRaw chickenInfoRaw6 = new ChickenInfoRaw();
        chickenInfoRaw6.setTotal(6);
        ChickenInfoRaw chickenInfoRaw7 = new ChickenInfoRaw();
        chickenInfoRaw7.setTotal(7);
        ChickenInfoRaw chickenInfoRaw8 = new ChickenInfoRaw();
        chickenInfoRaw8.setTotal(8);
        ChickenInfoRaw chickenInfoRaw9 = new ChickenInfoRaw();
        chickenInfoRaw9.setTotal(9);
        ChickenInfoRaw chickenInfoRaw10 = new ChickenInfoRaw();
        chickenInfoRaw10.setTotal(10);
        ChickenInfoRaw chickenInfoRaw11 = new ChickenInfoRaw();
        chickenInfoRaw11.setTotal(11);
        ChickenInfoRaw chickenInfoRaw12 = new ChickenInfoRaw();
        chickenInfoRaw12.setTotal(12);
        ChickenInfoRaw chickenInfoRaw13 = new ChickenInfoRaw();
        chickenInfoRaw13.setTotal(13);
        ChickenInfoRaw chickenInfoRaw14 = new ChickenInfoRaw();
        chickenInfoRaw14.setTotal(14);
        ChickenInfoRaw chickenInfoRaw15 = new ChickenInfoRaw();
        chickenInfoRaw15.setTotal(15);
        ChickenInfoRaw chickenInfoRaw16 = new ChickenInfoRaw();
        chickenInfoRaw16.setTotal(16);


        list.add(chickenInfoRaw1);
        list.add(chickenInfoRaw2);
        list.add(chickenInfoRaw3);
        list.add(chickenInfoRaw4);
        list.add(chickenInfoRaw5);
        list.add(chickenInfoRaw6);
        list.add(chickenInfoRaw7);
        list.add(chickenInfoRaw8);
        list.add(chickenInfoRaw9);
        list.add(chickenInfoRaw10);
        list.add(chickenInfoRaw11);
        list.add(chickenInfoRaw12);
        list.add(chickenInfoRaw13);
        list.add(chickenInfoRaw14);
        list.add(chickenInfoRaw15);
        list.add(chickenInfoRaw16);

        return list;
    }
}