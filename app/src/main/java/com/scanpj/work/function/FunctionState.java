package com.scanpj.work.function;

import com.scanpj.work.constant.ConstHz;
import com.scanpj.work.constant.ConstLocalData;

/**
 * Created by Alie on 2018/6/22.
 * 类描述
 * 版本
 */

public class FunctionState {

    public String getCurrentChickenState(int flag) {


        String text = "";
        if(flag == ConstLocalData.SCAN_ACCESS) {

            text = ConstHz.SCAN_HAD_ACCESS;
        }

        if(flag == ConstLocalData.SCAN_DENY) {

            text = ConstHz.SCAN_NOT_ACCESS;
        }

        return text;
    }
}
