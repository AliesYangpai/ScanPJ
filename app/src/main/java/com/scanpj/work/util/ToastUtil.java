package com.scanpj.work.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import org.feezu.liuli.timeselector.Utils.TextUtil;


/**
 * Created by Administrator on 2016/9/22 0022.
 * 类描述
 * 版本
 */
public class ToastUtil {



    public static void showMsg(Context context, String message) {

//        if (!TextUtil.isEmpty(message)) {
//
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//
//        }



        if (!TextUtil.isEmpty(message)) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }


    }


    public static void showMsg(Context context, int id) {

//        Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT).show();


        Toast toast = Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }


}
