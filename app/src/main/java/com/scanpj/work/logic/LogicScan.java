package com.scanpj.work.logic;

import com.scanpj.work.constant.ConstLocalData;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class LogicScan {


    /**
     * 连续扫描
     *
     * @param type
     * @return
     */
    public boolean isContinueScan(String type) {

        return type.equals(ConstLocalData.IS_CONTINUE_SCAN);
    }


    /**
     * 单词扫描
     *
     * @param type
     * @return
     */
    public boolean isSingleScan(String type) {

        return type.equals(ConstLocalData.IS_SINGLE_SCAN);
    }

    /**
     * 正在扫描
     * @param type
     * @return
     */
    public boolean isScanning(String type) {

        return type.equals(ConstLocalData.IS_SCANNING);
    }

    /**
     * 停止扫描
     * @param type
     * @return
     */
    public boolean isStop(String type) {

        return type.equals(ConstLocalData.IS_STOP);
    }



    /**
     * 扫描成功返回
     * @param length
     * @return
     */
    public boolean isScanDataSuccess(int length) {
        return length >= 1;
    }


    /**
     * 扫描取消
     * @param length
     * @return
     */
    public boolean isScanDataErrorCancel(int length) {

        return length == -1;
    }


    /**
     * 扫描超时
     * @param length
     * @return
     */
    public boolean isScanDataErrorTimeOut(int length) {

        return length == 0;
    }



}
