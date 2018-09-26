package com.scanpj.work.method;

import com.scanpj.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/6/11.
 * 类描述   鸡场
 * 版本
 */

public interface IChickenHouse {





    /**
     * 获取鸡场列表
     * @param url
     * @param onDataBackListener
     */
    void doGetChickenHouseRecords(String url,OnDataBackListener onDataBackListener);

    
}
