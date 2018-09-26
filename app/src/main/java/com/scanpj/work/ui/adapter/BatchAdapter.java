package com.scanpj.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scanpj.work.R;
import com.scanpj.work.callback.adapter.BatchItemSelectCallBack;
import com.scanpj.work.entity.BatchInfo;
import com.scanpj.work.universal.logic.LogicMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class BatchAdapter extends BaseQuickAdapter<BatchInfo, BaseViewHolder> {


    private Map<String, Integer> map;

    public Map<String, Integer> getMap() {
        return map;
    }


    private LogicMap logicMap;


    private BatchItemSelectCallBack batchItemSelectCallBack;


    public void setBatchItemSelectCallBack(BatchItemSelectCallBack batchItemSelectCallBack) {
        this.batchItemSelectCallBack = batchItemSelectCallBack;
    }

    public BatchAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        logicMap = new LogicMap();
        map = new HashMap<>();
    }

    @Override
    protected void convert(BaseViewHolder helper, final BatchInfo item) {




        Object o = map.get(String.valueOf(item.getId()));
        helper.setText(R.id.tv_batch_name, item.getName())
                .setChecked(R.id.cb_check, logicMap.isChecked(o))
                .setOnCheckedChangeListener(R.id.cb_check, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            if(null != batchItemSelectCallBack) {
                                batchItemSelectCallBack.onItemDoSelect(map,item);
                            }
                        } else {
                            if(null != batchItemSelectCallBack) {
                                batchItemSelectCallBack.onItemDoUnSelect(map,item);
                            }
                        }
                        
                    }
                });

    }
}
