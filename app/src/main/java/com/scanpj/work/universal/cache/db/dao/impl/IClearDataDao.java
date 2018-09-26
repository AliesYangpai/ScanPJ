package com.scanpj.work.universal.cache.db.dao.impl;





import com.scanpj.work.constant.ConstSp;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.universal.cache.sp.SpUtil;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述   清楚数据的Dao
 * 版本
 */

public class IClearDataDao {


    /**
     * 删除单个本地表
     * @param mClass
     */
    public void doClearSingleTable(Class<?> mClass) {

        DataSupport.deleteAll(mClass);

    }


    /**
     * 删除全部的表
     */
    public void doClearAllTable() {

    }


    public void doClearTableExceptScanAbout() {
        DataSupport.deleteAll(ChickenInfoScanAbout.class);
    }


    /**
     * 删除全部本地标记
     */
    public void doClearSp() {



        /**
         * 清除token
         */
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN,ConstSp.SP_VALUE.DEFAULT_STRING);

    }



}
