package com.scanpj.work;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.scanpj.work.universal.crash.CrashHandler;
import com.scanpj.work.universal.img.ImgUtil;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

import org.litepal.LitePalApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alie on 2018/6/9.
 * 类描述
 * 版本
 */

public class App  extends LitePalApplication implements  Application.ActivityLifecycleCallbacks {


    public static final String TAG = App.class.getSimpleName();

    private static volatile  App mInstance;

    public static App getInstance() {

        if(null == mInstance) {
            synchronized (App.class) {
                if(null == mInstance) {
                    mInstance = new App();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initImageLoader(mInstance);
        initNoHttp();
        registerActivityLifecycleCallbacks(mInstance);

        CrashHandler.getInstance().init(this);
//        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
//        BlockCanary.install(this, new BlockCanaryContext()).start();

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base); MultiDex.install(this);
    }

    /**
     * 初始化ImageLoader
     */
    private void initImageLoader(Context context) {


        String cachePath = this.getCacheDir().getAbsolutePath() + File.separator + "imageCache/";

        File cacheDir = new File(cachePath);

        ImageLoaderConfiguration config = new
                ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)// 设置当前线程优先级
                .denyCacheImageMultipleSizesInMemory() // 缓存显示不同 大小的同一张图片
                .diskCacheSize(50 * 1024 * 1024) // 本地Sd卡的缓存最大值
                .diskCache(new UnlimitedDiscCache(cacheDir))// sd卡缓存
                .memoryCache(new WeakMemoryCache()) // 内存缓存
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImgUtil.getInstance().getImageLoader().init(config);


    }


    /**
     * 初始化网路请求框架
     */
    private void initNoHttp() {

        Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("NoHttpMyTest"); // 设置NoHttp打印Log的TAG。
        /**
         * 1.超时配置
         * 2.配置缓存 （实际就是数据的本地存储）
         * 3.配置Cookie
         * 4.配置网络层 （这大概是http网络层的异常处理）
         *
         */
        NoHttp.Config config = new NoHttp.Config();
        config.setConnectTimeout(30 * 1000); // 全局连接超时时间，单位毫秒。
        config.setReadTimeout(30 * 1000); // 全局服务器响应超时时间，单位毫秒。
        config.setNetworkExecutor(new OkHttpNetworkExecutor()); //2.配置网络层

        NoHttp.initialize(mInstance, config);


    }


    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable throwable) {
            try {
                Log.e(TAG, Log.getStackTraceString(throwable));
                throwable.printStackTrace();
                // 指定输出日志的路径
                File errerLog = new File(getSdcardPackagePath() + "/scanpjError.log");
                FileWriter fw = new FileWriter(errerLog, true);
                fw.write("\n\n" + SimpleDateFormat.getInstance().format(new Date()) + "\n");
                PrintWriter pw = new PrintWriter(fw);
                throwable.printStackTrace(pw);
                fw.close();
                pw.close();

                // 捕获未捕获的异常,杀掉进程闪退
                catheUnhanldeCatch();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * 杀死当前进程
     */
    private void catheUnhanldeCatch() {
        // 得到当前应用的进程ID
        int pid = android.os.Process.myPid();
        // 杀死进程 闪退
        android.os.Process.killProcess(pid);
    }



    private String getSdcardPackagePath() {
        // 判断sdcard是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 获取根目录
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            return getFilesDir().getPath();
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


}
