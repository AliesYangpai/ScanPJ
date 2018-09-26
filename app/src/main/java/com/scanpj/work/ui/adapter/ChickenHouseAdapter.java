package com.scanpj.work.ui.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scanpj.work.R;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.entity.ChickenHouseInfo;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class ChickenHouseAdapter extends BaseQuickAdapter<ChickenHouseInfo, BaseViewHolder> {


    public ChickenHouseAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChickenHouseInfo item) {

        helper.setText(R.id.tv_chicken_house_name, item.getName());
    }
}
