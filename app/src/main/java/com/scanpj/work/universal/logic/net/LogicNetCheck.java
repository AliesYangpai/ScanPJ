package com.scanpj.work.universal.logic.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.scanpj.work.universal.logic.LogicBuildVersion;
import com.scanpj.work.util.NetUtil;


/**
 * Created by admin on 2018/5/9.
 * 类描述  网络判断
 * 版本
 */
public class LogicNetCheck {


    private LogicBuildVersion logicBuildVersion;
    private NetUtil netUtil;

    public LogicNetCheck() {

        logicBuildVersion = new LogicBuildVersion();
        netUtil = new NetUtil();
    }

    /**
     * 判斷wifi是否链接
     * @return
     */
    public boolean isWifiConnected() {


        boolean result = false;
        NetworkInfo networkInfo = null;
        if (logicBuildVersion.isEqualOrBigerThan23()) {

            networkInfo = netUtil.getNewWorkInfo23Beyond(ConnectivityManager.TYPE_WIFI);
            if(null != networkInfo) {

                result = networkInfo.isConnected();
            }
        } else {

            networkInfo = netUtil.getNewWorkInfo23Below(ConnectivityManager.TYPE_WIFI);
            if(null != networkInfo) {

                result = networkInfo.isConnected();
            }
        }
        return result;
    }


    /**
     * 判断是否是手机网络连入
     * @return
     */
    public boolean isMobilConnected() {


        boolean result = false;
        NetworkInfo networkInfo = null;
        if (logicBuildVersion.isEqualOrBigerThan23()) {

            networkInfo = netUtil.getNewWorkInfo23Beyond(ConnectivityManager.TYPE_MOBILE);

            if(null != networkInfo) {
                result = networkInfo.isConnected();
            }

        } else {

            networkInfo = netUtil.getNewWorkInfo23Below(ConnectivityManager.TYPE_MOBILE);
            if(null != networkInfo) {
                result = networkInfo.isConnected();
            }
        }
        return result;
    }


}