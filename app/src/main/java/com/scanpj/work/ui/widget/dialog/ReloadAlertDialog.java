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
 * 类描述  有2个button的dialog
 * 版本
 */

public class ReloadAlertDialog extends AlertDialog implements View.OnClickListener {


    /**
     * 中间消息
     */

    private String message;//从外界设置的消息文本
    private String msgSure;//确定的按钮文字
    private String msgCancel;//取消的按钮文字





    /**
     * 底部按钮
     */
    private TextView tv_message_duoble;
    private TextView tv_double_cancel; //底部取消
    private TextView tv_double_sure;//底部确认


    private ReloadAlertDialogCallBack reloadAlertDialogCallBack;

    public void setReloadAlertDialogCallBack(ReloadAlertDialogCallBack reloadAlertDialogCallBack) {
        this.reloadAlertDialogCallBack = reloadAlertDialogCallBack;
    }

    public ReloadAlertDialog(Context context, int tag) {
        super(context, R.style.dialogReloadAlert);
    }


    public ReloadAlertDialog(Context context) {
        super(context, R.style.dialogReloadAlert);

    }






    /**
     * 设置系显示数据
     * @param message
     */

    public void setMessage(String message) {
        this.message = message;
//        tv_message_duoble.setText(this.message);
    }

    public void setMsgSure(String msgSure) {
        this.msgSure = msgSure;


//        tv_double_sure.setText(this.msgSure);

    }

    public void setMsgCancel(String msgCancel) {
        this.msgCancel = msgCancel;
//        tv_double_cancel.setText(this.msgCancel);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reload_alert);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initListener();


    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {
        /**
         * 底部按钮
         */
        tv_double_cancel.setOnClickListener(this); //底部取消
        tv_double_sure.setOnClickListener(this);//底部确认

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {


//        tv_message_duoble.setText(message);
//        tv_double_cancel.setText(msgCancel);
//        tv_double_sure.setText(msgSure);

    }


    /**
     * 初始化界面控件
     */
    private void initView() {


        /**
         * 文本信息
         */
        tv_message_duoble = (TextView) findViewById(R.id.tv_message_duoble);

        /**
         * 底部按钮
         */
          tv_double_cancel = (TextView) findViewById(R.id.tv_double_cancel); //底部取消
          tv_double_sure = (TextView) findViewById(R.id.tv_double_sure);//底部确认



    }



    @Override
    public void onClick(View v) {



        switch (v.getId()) {

            case R.id.tv_double_cancel:  //取消

                if(null!= reloadAlertDialogCallBack) {
                    reloadAlertDialogCallBack.onReloadAlertCancel();
                }

                break;


            case R.id.tv_double_sure:    //确认

                if(null!= reloadAlertDialogCallBack) {
                    reloadAlertDialogCallBack.onReloadAlertSure();
                }
                break;



        }
    }


    /**
     * 点击事件
     */


}
