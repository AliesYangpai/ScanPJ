package com.scanpj.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.scanpj.work.R;


/**
 * Created by Administrator on 2017/4/25.
 * 类描述  批次的dialog
 * 版本
 */

public class ScanAnotherCommitPartSuccessDialog extends AlertDialog {


    /**
     * 底部按钮
     */
    private TextView tv_name;


    public ScanAnotherCommitPartSuccessDialog(Context context) {
        super(context, R.style.dialogBatchAlert);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_scan_another_commit_part_success);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);


        //初始化界面控件
        initView();
        //初始化界面数据


    }


    public void doInitData(String name) {

        tv_name.setText(name);
    }


    /**
     * 初始化界面控件
     */
    private void initView() {


        /**
         * 文本信息
         */
        tv_name = (TextView) findViewById(R.id.tv_name);


    }


    /**
     * 点击事件
     */


}
