package com.scanpj.work.logic;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Alie on 2018/6/13.
 * 类描述
 * 版本
 */

public class LogicChicken {

    public boolean isNullStep(String str) {
        return TextUtil.isEmpty(str);
    }

    public boolean isStep0(String str) {

        return Long.valueOf(str) == 0;

    }


    /**
     * 判断是否合适
     * @param stepRaw
     * @return
     */
    public boolean isAccessStep(int stepRaw ,long stepEnter) {
        return stepRaw >= stepEnter;
    }


    /**
     * 用于判断是否扫描时已经被扫过
     * @param target
     * @param list
     * @return
     */
    public boolean isHasBeenScaned(String target, List<ChickenInfoScanAbout> list) {

        boolean result = false;
        for (ChickenInfoScanAbout chickenInfoScanAbout:list) {
            if(target.equals(chickenInfoScanAbout.getRingid())) {
                result = true;
                break;
            }
        }

        return result;

    }


}
