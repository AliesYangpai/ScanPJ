package com.scanpj.work.androidelement.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.scanpj.work.callback.NetWorkStateChangedCallBack;
import com.scanpj.work.constant.ConstLog;
import com.scanpj.work.universal.logic.net.LogicNetCheck;


/**
 * Created by Administrator on 2018/1/22.
 * 类描述   网络状态监听的广播
 * 版本
 */

public class NetWorkBroadCastReciver extends BroadcastReceiver {

    private NetWorkStateChangedCallBack netWorkStateChangedCallBack;
    private LogicNetCheck logicNetCheck = new LogicNetCheck();

    public void setNetWorkStateChangedCallBack(NetWorkStateChangedCallBack netWorkStateChangedCallBack) {
        this.netWorkStateChangedCallBack = netWorkStateChangedCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        if (null != netWorkStateChangedCallBack) {

            if (logicNetCheck.isMobilConnected() && logicNetCheck.isWifiConnected()) {
                Log.i(ConstLog.BREOAD_CAST,intent.getAction()+" 4G连入 && wifi连入");
                return;
            }

            if (logicNetCheck.isMobilConnected() && !logicNetCheck.isWifiConnected()) {

                Log.i(ConstLog.BREOAD_CAST,intent.getAction()+" 4G连入 && wifi断开");
                return;
            }


            if (!logicNetCheck.isMobilConnected() && logicNetCheck.isWifiConnected()) {
                Log.i(ConstLog.BREOAD_CAST,intent.getAction()+" 4G断开 && wifi连入");
                return;
            }

            if (!logicNetCheck.isWifiConnected() && !logicNetCheck.isMobilConnected()) {

                Log.i(ConstLog.BREOAD_CAST,intent.getAction()+" 4G断开 && wifi断开");
                netWorkStateChangedCallBack.onNetChangeNoConnect();
                return;
            }


        }


    }


}
