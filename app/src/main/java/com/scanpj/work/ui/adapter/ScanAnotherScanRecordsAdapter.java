package com.scanpj.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scanpj.work.R;
import com.scanpj.work.entity.AnotherScanInfo;

import java.util.List;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class ScanAnotherScanRecordsAdapter extends BaseQuickAdapter<AnotherScanInfo, BaseViewHolder> {


    public ScanAnotherScanRecordsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnotherScanInfo item) {

        helper.setText(R.id.tv_feet_ring_id, item.getRingid())
                .setText(R.id.tv_code, item.getCode())
                .setText(R.id.tv_insert_time, item.getInserttime());
    }
}
