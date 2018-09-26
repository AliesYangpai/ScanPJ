package com.scanpj.work.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;


import com.scanpj.work.R;
import com.scanpj.work.androidelement.receiver.NetWorkBroadCastReciver;
import com.scanpj.work.callback.NetWorkStateChangedCallBack;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.presenter.BasePresenter;
import com.scanpj.work.ui.widget.AdhDialog;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import crossoverone.statuslib.StatusUtil;


public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public static String TAG = BaseActivity.class.getSimpleName(); //获得类名称

    public static ConcurrentHashMap<String, Activity> ACTIVITY_STACK = new ConcurrentHashMap<>(); //activty栈


    private T presenter;


    private AdhDialog adhDialog;

    private WeakReference<Context> weakReference;

    private NetWorkBroadCastReciver netWorkBroadCastReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        presenter = creatPresenter();

        Log.i("ceshi", "==requestWindowFeature");
        addToActivitesStack(this.getClass().getSimpleName(), this);
        Log.i("ceshi", "==addToActivitesStack");


        Log.i("activtyTag", "========" + "===============" + getLocalClassName());
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Log.i("ceshi", "==setContentView");

        /**
         * 设置沉浸式状态栏
         */
        StatusUtil.setUseStatusBarColor(this, Color.parseColor("#10c55b"));
        StatusUtil.setSystemStatus(this, true, false);

        weakReference = new WeakReference<Context>(this);
        processIntent();
//        presenter = creatPresenter();
        if (null != presenter) {

            presenter.attachView((V) this);
        }
        initViews();
        initListener();


    }


    protected abstract T creatPresenter();


    /**
     * 封装findviewById方法
     */
    protected <T extends View> T findZETScanViewById(int id) {
        return (T) findViewById(id);
    }

    /**
     * 初始化控件
     * 子activity 覆盖这个方法初始化ui控件
     */
    protected abstract void initViews();

    /**
     * 初始化监听
     * 子activity 覆盖这个方法初始化ui控件的监听事件
     */
    protected abstract void initListener();


    /**
     * @Title processIntent
     * @Description 获取Intent携带数据
     */
    protected abstract void processIntent();


    /**
     * 打开activity的方法
     */
    protected void openActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 打开activity并且销毁自己
     */
    protected void openActivityAndFinishItself(Class<?> targetClass, Bundle bundle) {

        Intent intent = new Intent(this, targetClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        this.finish();

    }


    /**
     * activity加入到回退栈方法
     */
    public void addToActivitesStack(String activityTag, Activity activity) {


        ACTIVITY_STACK.put(activityTag, activity); //将activity加入到回退栈
    }


    /**
     * activity退出回退栈方法
     */
    public void removeFromActiviiesStack(String activityTag) {

        ACTIVITY_STACK.remove(activityTag);
    }


    /**
     * 清空指定activity以外的所有Activty所有的Activity
     */
    public void removeAllFromActiviiesStack(String activityTag) {


        if (null != ACTIVITY_STACK && ACTIVITY_STACK.size() > 0) {


            Iterator<String> iterator = ACTIVITY_STACK.keySet().iterator();


            while (iterator.hasNext()) {

                String tag = iterator.next();

                if (tag.equals(activityTag)) {

                    continue;

                }

                ACTIVITY_STACK.get(tag).finish();

            }


        }


    }


    /**
     * 销毁指定的activity
     */
    public void removeTargetActivityFromStack(String activityTag) {


        if (null != ACTIVITY_STACK && ACTIVITY_STACK.size() > 0) {


            Iterator<String> iterator = ACTIVITY_STACK.keySet().iterator();


            while (iterator.hasNext()) {


                String tag = iterator.next();

                if (tag.equals(activityTag)) {

                    ACTIVITY_STACK.get(tag).finish();

                    break;

                }


            }


        }


    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {


        doRegisterNewStateReceiver();
        super.onResume();


        /**
         * 处理是否推送
         */
        String claName = this.getClass().getSimpleName();


    }

    @Override
    protected void onPause() {

        doUnRegisterNetStateReceiver();
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();


        destroyLoadDialog();

        removePresenter();

        removeFromActiviiesStack(this.getClass().getSimpleName());


    }


    private void removePresenter() {

        if (null != presenter) {
            presenter.detachView();
        }
    }

    /**
     * 在嵌套fragmnet的时候解决 回收崩溃问题
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }


    /**
     * 显示加载Dialog
     */
    protected void showLoadDialog() {


//        if(null != weakReference ) {
//            if(null == adhDialog) {
//                adhDialog = new AdhDialog(weakReference.get());
//            }
//
//            if(!isFinishing()) {
//
//                adhDialog.show();
//            }
//
//        }


        if (!isFinishing()) {


            if (null != weakReference) {
                if (null == adhDialog) {
                    adhDialog = new AdhDialog(weakReference.get());
                }

//                adhDialog.show();
                if (!adhDialog.isShowing()) {
                    adhDialog.show();
                }


            }
        }

    }


    protected void dismissLoadDialog() {

        if (null != weakReference) {


            if (null != adhDialog && adhDialog.isShowing()) {

                adhDialog.dismiss();
            }

        }

    }

    protected void destroyLoadDialog() {

        if (null != adhDialog) {

            if (adhDialog.isShowing()) {

                adhDialog.dismiss();
                adhDialog = null;

            } else {

                adhDialog = null;
            }
        }
    }

    protected void dofinishItself() {

        if (!isFinishing()) {

            this.finish();

        }

    }


    /**
     * 获取swiplayout的颜色
     *
     * @return
     */
    public int[] getSwipeRefreshColor() {

//        return new int[]{Color.parseColor("#30baff")};


//        return new int[]{android.R.color.holo_blue_light,
//                android.R.color.holo_red_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_green_light};



        return new int[]{
                Color.parseColor("#ff99cc00"),
                Color.parseColor("#ff33b5e5"),
                Color.parseColor("#ffff4444"),
                Color.parseColor("#ffffbb33")};

    }


    /**
     * 注册网路网络状态监听
     */
    private void doRegisterNewStateReceiver() {
        if (netWorkBroadCastReciver == null) {
            netWorkBroadCastReciver = new NetWorkBroadCastReciver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        netWorkBroadCastReciver.setNetWorkStateChangedCallBack(new NetWorkStateChangedCallBack() {
            @Override
            public void onNetChangeToWifi() {
            }

            @Override
            public void onNetChangeToMobel() {
            }

            @Override
            public void onNetChangeNoConnect() {




                CustomToastUtil.showToast(getApplicationContext(),R.string.no_net_connect, ConstLocalData.TOAST_DURATION);

            }
        });
        registerReceiver(netWorkBroadCastReciver, filter);

    }

    /**
     * 取消注册网络状态监听
     */
    private void doUnRegisterNetStateReceiver() {


        if (null != netWorkBroadCastReciver) {


            unregisterReceiver(netWorkBroadCastReciver);
            netWorkBroadCastReciver = null;
        }

    }

}
