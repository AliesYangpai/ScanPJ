package com.scanpj.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scanpj.work.R;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.function.FunctionState;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class ScanCurrentAdapter extends BaseQuickAdapter<ChickenInfoRaw, BaseViewHolder> {


    private FunctionState functionState;




    public ScanCurrentAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        this.functionState = new FunctionState();

    }

    @Override
    protected void convert(BaseViewHolder helper, final ChickenInfoRaw item) {



        helper.setText(R.id.tv_1, item.getRingid())
                .setText(R.id.tv_2,String.valueOf(item.getTotal()))
                .setText(R.id.tv_3,functionState.getCurrentChickenState(item.getFlag()))
                .setText(R.id.tv_4,item.getName())
                .addOnClickListener(R.id.tv_4);



//        helper.setText(R.id.tv_1, item.getRingid())
//                .setText(R.id.tv_2,item.getHouseid())
//                .setText(R.id.tv_3,item.getBatchid())
//                .setText(R.id.tv_4,String.valueOf(item.getTotal()))
//                .setText(R.id.tv_5,functionState.getCurrentChickenState(item.getFlag()));
    }
}
