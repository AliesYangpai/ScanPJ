package com.scanpj.work.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.scanpj.work.App;


/**
 * Created by admin on 2018/5/9.
 * 类描述  网络的工具类
 * 版本
 * 1.2 特别注意
 * <p>
 * 由于在API23及以上时，getNetworkInfo(int networkType)方法已被弃用，取而代之的是：
 * getAllNetworks();
 * getNetworkInfo(android.net.Network);
 * getNetworkInfo(Network network);
 *     //    ConnectivityManager.TYPE_WIFI   //wifi；链接判断
 //    ConnectivityManager.TYPE_MOBILE  //4G连入判断
 */
public class NetUtil {



    private ConnectivityManager connMgr;

    public NetUtil() {

         connMgr = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * @param type
     * @return
     */
    public NetworkInfo getNewWorkInfo23Below(int type) {


        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo;
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NetworkInfo getNewWorkInfo23Beyond(int type) {
        NetworkInfo networkInfo = null;
        connMgr = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        Network[] networks = connMgr.getAllNetworks();        //获取所有网络连接的信息


        if(null != networks) {
            for (Network network:networks) {
                networkInfo = connMgr.getNetworkInfo(network);
                if(networkInfo.getType() == type) {
                    break;
                }else {
                    networkInfo = null;
                }
            }
        }


        return  networkInfo;
    }

}