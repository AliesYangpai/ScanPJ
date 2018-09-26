package com.scanpj.work.constant;

/**
 * Created by admin on 2018/5/31.
 * 类描述
 * 版本
 */
public class ConstDbLocal {


    public static final int INCREMENT_SIZE = 6;  //默认本地分页起始量
    public static final int INCREMENT_OFFSET = 6;  //默认本地分页查询增量


    public static final int UPLOAD_LIMITE = 100;  //默认一次同步数
    public static final int UPLOAD_OFFSET = 100;  //默认一次同步增量

    public static final String AND = " and ";//这里一定要以空格空开
    public static final String OR = " or ";//这里一定要以空格空开

    public static final String DB_TRUE = "1";
    public static final String DB_FAULSE = "0";
    /**
     * bundleKey
     */
    public class ScanAbout {

        public static final String RING_ID = "ringid = ?";//脚环的模糊语句
        public static final String FLAG = "flag = ?";//名称的模糊语句
        public static final String IS_SCAND = "isscaned = ?";//已经扫描的
        public static final String IS_SYNC = "isSync = ?";//已经扫描的ASDASD

    }
}