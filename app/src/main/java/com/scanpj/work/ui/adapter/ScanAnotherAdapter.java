package com.scanpj.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scanpj.work.R;
import com.scanpj.work.callback.adapter.ScanAnotherItemSelectCallBack;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.universal.logic.LogicMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class ScanAnotherAdapter extends BaseQuickAdapter<AnotherScanInfo, BaseViewHolder> {


    private boolean tag; //用于标记当前adapter是否是出于选择状态






    public boolean isTag() {
        return tag;
    }


    public void setTag(boolean tag) {
        this.tag = tag;
    }


    private LogicMap logicMap;

    private Map<Integer, Boolean> map = new HashMap<>();

    public Map<Integer, Boolean> getMap() {
        return map;
    }

    private ScanAnotherItemSelectCallBack scanAnotherItemSelectCallBack;

    public void setScanAnotherItemSelectCallBack(ScanAnotherItemSelectCallBack scanAnotherItemSelectCallBack) {
        this.scanAnotherItemSelectCallBack = scanAnotherItemSelectCallBack;
    }


    public void doInitMap(List<AnotherScanInfo> list) {
        if(null != list && list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                map.put(i, false);
            }
        }


//        for (int i = 0; i < list.size(); i++) {
//            map.put(i, false);
//        }


    }


    public void doClearAllMap(List<AnotherScanInfo> noSelectList) {
        map.clear();
        if(null != noSelectList && noSelectList.size() > 0) {
            for (int i = 0; i < noSelectList.size(); i++) {
                map.put(i, false);
            }
        }
    }



    public ScanAnotherAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        this.logicMap = new LogicMap();

    }

    @Override
    protected void convert(final BaseViewHolder helper, final AnotherScanInfo item) {
        CheckBox checkBox = helper.getView(R.id.cb_check);
        final int position = helper.getLayoutPosition();
        if (tag) {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setChecked(map.get(position));

        } else {
            checkBox.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_feet_ring_id, item.getRingid())
                .setText(R.id.tv_code, item.getCode());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    if (null != scanAnotherItemSelectCallBack) {
                        scanAnotherItemSelectCallBack.onItemDoSelect(map, position);
                    }
                } else {
                    if (null != scanAnotherItemSelectCallBack) {
                        scanAnotherItemSelectCallBack.onItemDoUnSelect(map, position);
                    }
                }
            }
        });

    }


}
