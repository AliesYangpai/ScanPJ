package com.scanpj.work.universal.logic;

import android.os.Build;

/**
 * Created by admin on 2018/5/9.
 * 类描述  android版本号判断的逻辑类，
 * 版本
 */
public class LogicBuildVersion {


    public LogicBuildVersion() {
    }


    /**
     * 版本号大于等于23时
     * @return
     */
    public boolean isEqualOrBigerThan23() {

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;

    }

}