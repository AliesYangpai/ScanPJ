package com.scanpj.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.scanpj.work.R;
import com.scanpj.work.callback.dialogcallback.ReloadAlertDialogCallBack;


/**
 * Created by Administrator on 2017/4/25.
 * 类描述  批次的dialog
 * 版本
 */

public class BatchInfoDialog extends AlertDialog {


    /**
     * 底部按钮
     */
    private TextView tv_batch_name;


    public BatchInfoDialog(Context context) {
        super(context, R.style.dialogBatchAlert);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_batch_info);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);


        //初始化界面控件
        initView();
        //初始化界面数据


    }


    public void doInitData(String name) {

        tv_batch_name.setText(name);
    }


    /**
     * 初始化界面控件
     */
    private void initView() {


        /**
         * 文本信息
         */
        tv_batch_name = (TextView) findViewById(R.id.tv_batch_name);


    }


    /**
     * 点击事件
     */


}
