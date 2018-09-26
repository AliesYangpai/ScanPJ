package com.scanpj.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scanpj.work.R;
import com.scanpj.work.entity.BatchInfo;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.universal.logic.LogicMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class ScanAllAdapter extends BaseQuickAdapter<ChickenInfoRaw, BaseViewHolder> {


    public ScanAllAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);

    }

    @Override
    protected void convert(BaseViewHolder helper, final ChickenInfoRaw item) {


        Log.i("db_select", " " + item.getTotal());

        helper.setText(R.id.tv_1, item.getRingid())
                .setText(R.id.tv_2, String.valueOf(item.getTotal()))
                .setText(R.id.tv_3, item.getName())
                .addOnClickListener(R.id.tv_3);


//        helper.setText(R.id.tv_1, item.getRingid())
//
//                .setText(R.id.tv_2,item.getHouseid())
//                .setText(R.id.tv_3,item.getBatchid())
//                .setText(R.id.tv_4,String.valueOf(item.getTotal()));
    }
}
