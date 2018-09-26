package com.scanpj.work.logic;

import java.util.Map;

/**
 * Created by Alie on 2018/6/13.
 * 类描述
 * 版本
 */

public class LogicChickenHouse {

    public boolean isNoSelectBatch(Map map) {
        return null == map || map.size() == 0;
    }


    /**
     * 在列表中 点击选择了部分
     * @param map
     * @return
     */
    public boolean isItemNoSelectBatch(Map map) {
        return null == map || map.size() == 0;
    }



    /**
     * 在列表中 点击选择了全部
     * @param map
     * @return
     */
    public boolean isItemAllSelectBatch(Map map, int size) {


        boolean result = false;
        if (null != map && map.size() > 0) {
            if(map.size() == size) {
                result = true;
            }
        }
        return result;
    }
}
