package com.scanpj.work.ui.iview;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.ui.IBasePermissionView;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public interface IScanOperateView extends IBasePermissionView {


    /**
     * 扫描初始化异常
     */
    void onVertifyErrorScanInitError();

    /**
     * 开始初始化扫描器
     */
    void doInitConfigScan();

    /**
     * 禁用扫描类型rb按钮
     */
    void doUnableScanTypeCheck();

    /**
     * 启用扫描类型rb按钮
     */
    void doEnableScanTypeCheck();

    /**
     * 设置扫描文本"开始"
     */
    void doSetScanButtonTextStart();


    /**
     * 设置扫描文本"停止"
     */
    void doSetScanButtonTextStop();


    /**
     * 验证扫描结果:扫描取消
     */
    void onVertifyErrorScanCancel();


    /**
     * 验证扫描结果:扫描超时
     */
    void onVertifyErrorScanTimeOut();

    void doCbUnChecked();

    /**
     * 验证扫描结果:扫描失败
     */
    void onVertifyErrorScanFail();


    void doShowInitScanDeviceDialog();


    void doDestroyInitScanDeviceDialog();



    void onDataBackSuccessScan(String target);

    void onVertifyErrorNoSuchData();




    void doSaveData(ChickenInfoRaw chickenInfoRaw);

    void doSetScanDataToFgUi(ChickenInfoRaw chickenInfoRaw);


    void doPlaySoundAccess();
    void doPlaySoundDeny();





    void doVertifyErrorHasScanned();



    void onDataBackSuccessAndContinue(int uploadLimite,int uploadOffset);



    void onDataBackSuccessAndStop();


    void doSetBottomTextAll(int count);
    void doSetBottomTextHasScanned(int count);
    void doSetBottomTextDeny(int count);
    void doSetBottomTextAccess(int count);

    void doVertifyErrorNoScanBeforeSync();

    void doVertifySyncFinish();


    void doShowUploadDialog();


    void doDestroyUploadDialog();


    void doClearFgCurrentScanDataWhenReload();
}
