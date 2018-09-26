package com.scanpj.work.universal.logic;

import com.scanpj.work.constant.ConstIntent;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Alie on 2018/6/15.
 * 类描述
 * 版本
 */

public class LogicLoadData {

    public boolean isReload(String tag){

        return !TextUtil.isEmpty(tag) && ConstIntent.BundleValue.DO_RELOAD.equals(tag);
    }
}
