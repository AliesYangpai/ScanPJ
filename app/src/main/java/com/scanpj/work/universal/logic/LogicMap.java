package com.scanpj.work.universal.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alie on 2018/6/13.
 * 类描述
 * 版本
 */

public class LogicMap {


    public boolean isChecked(Object o) {

        return null != o;
    }


    public int getMapSelectedSize(Map<Integer, Boolean> map) {


        int count = 0;
        for (int i = 0; i < map.size(); i++) {

            if (map.get(i)) {
                count += 1;
            }
        }
        return count;

    }

    public int getMapUnSelectedSize(Map<Integer, Boolean> map) {


        int count = 0;
        for (int i = 0; i < map.size(); i++) {

            if (!map.get(i)) {
                count += 1;
            }
        }
        return count;
    }


    public List<Integer> getSelectIndexFromMap(Map<Integer, Boolean> map) {


        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {

            if (map.get(i)) {
                integers.add(i);
            }
        }
        return integers;
    }



    public List<Integer> getUnSelectIndexFromMap(Map<Integer, Boolean> map) {


        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {

            if (!map.get(i)) {
                integers.add(i);
            }
        }
        return integers;
    }


}
