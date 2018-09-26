package com.scanpj.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scanpj.work.R;
import com.scanpj.work.entity.ChickenInfoScanAbout;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class ScanNotAdapter extends BaseQuickAdapter<ChickenInfoScanAbout, BaseViewHolder> {







    public ScanNotAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);

    }

    @Override
    protected void convert(BaseViewHolder helper, final ChickenInfoScanAbout item) {


        Log.i("db_select"," "+item.getTotal());
        helper.setText(R.id.tv_1, item.getRingid())
                .setText(R.id.tv_2,item.getHouseid())
                .setText(R.id.tv_3,item.getBatchid())
                .setText(R.id.tv_4,String.valueOf(item.getTotal()));
    }
}
