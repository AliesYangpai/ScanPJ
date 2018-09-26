package com.scanpj.work.universal.logic.view;


import com.scanpj.work.ui.fragment.FragmentScanAll;
import com.scanpj.work.ui.fragment.FragmentScanCurrent;
import com.scanpj.work.ui.fragment.FragmentScanHad;
import com.scanpj.work.ui.fragment.FragmentScanNot;

/**
 * Created by admin on 2018/5/9.
 * 类描述  根据View的position获取对应的fragment
 * 版本
 */
public class LogicViewFg {


    /**
     *   扫描结果界中的fgmgr
     * @param position
     * @return
     */
    public String getFgScanTagByPosition(int position) {

        String fgTag = null;
        switch (position) {

            case 0:
                fgTag = FragmentScanCurrent.TAG;
                break;

            case 1:
                fgTag = FragmentScanAll.TAG;
                break;

            case 2:
                fgTag = FragmentScanHad.TAG;
                break;

            case 3:
                fgTag = FragmentScanNot.TAG;
                break;

        }

        return fgTag;
    }





}