package com.scanpj.work.util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Alie on 2018/6/19.
 * 类描述  自定义toastUtil用于处理高频率的提示
 * 版本
 */

public class CustomToastUtil {



    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static boolean mCanceled = true;

    private static Runnable r = new Runnable() {
        public void run() {
            mCanceled = true;
            mToast.cancel();
        }
    };

    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null) {
            mToast.setText(text);
        }else {
            mToast = Toast.makeText(mContext, text,duration);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
        mHandler.postDelayed(r, duration);
        if(duration > 3000){//显示时间大于3秒
            mCanceled  = false;
            showAgain(duration);
        }else {
            mToast.show();
        }

    }

    private static void showAgain(int duration){
        if(mCanceled)return;
        mToast.show();
        duration = duration - 1000;
        if(duration > 0) {
            final int t = duration;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mHandler.removeCallbacks(r);
                    mCanceled = false;
                    mHandler.postDelayed(r, t);
                    showAgain(t);
                }
            }, 1000);
        }
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }
}
