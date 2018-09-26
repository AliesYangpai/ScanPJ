package com.scanpj.work.ui.iview;

import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.ui.IBasePermissionView;
import com.scanpj.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public interface IScanAnotherView extends IBaseView {


    /**
     * 扫描初始化异常
     */
    void onVertifyErrorScanInitError();

    /**
     * 开始初始化扫描器
     */
    void doInitConfigScan();


    /**
     * 设置扫描文本"开始"
     */
    void doSetScanButtonImgStart();


    /**
     * 设置扫描文本"停止"
     */
    void doSetScanButtonImgStop();


    /**
     * 设置扫描的状态
     */
    void doSetScanStatus(String status);


    /**
     * 验证扫描结果:扫描取消
     */
    void onVertifyErrorScanCancel();


    /**
     * 验证扫描结果:扫描超时
     */
    void onVertifyErrorScanTimeOut();

    /**
     * 验证扫描结果:扫描失败
     */
    void onVertifyErrorScanFail();


    void doShowInitScanDeviceDialog();


    void doDestroyInitScanDeviceDialog();


    /**
     * 请求提交数据 相关
     */


    void onVertifyErrorNullFeedRingId();


    void onVertifyErrorNullCode();


    void doClearUiData();

    void onDataBackSuccessForCommit();

    void onDataBackSuccessForCommitAndRemoveListSize(List<AnotherScanInfo> list);


    void doVertifyErrorHasRingId();

    void doVertifyErrorHasCodeId();


    void doSetDataToUiFeetRingId(String str);

    void doSetDataToUiCodeId(String str);

    void doAddNewLine();

    void doClearAllAddNewLine();

    void onVertifyErrorNoScan();

    void onVertifyErrorNotCompleteScan();

    void doPlaySound();


    void doShowTopRightCbView();

    void doHideTopRightCbView();

    void doSetTopRightCbText(String text);

    void doShowBottomSelectLayout();

    void doHindBottomSelectLayout();


    void doInvisibleImageViewOccupy();

    void doHideImageViewOccupy();


    void doAdapterShowCheck(List<AnotherScanInfo> targetList);

    void doAdapterHideCheck(List<AnotherScanInfo> targetList);


    void doSetBottomCheckTextAllSelect();


    void doSetBottomCheckTextAllUnSelect();


    void doSelectAll();

    void doUnSelectAll();


    void doSetBottomCbChecked();

    void doSetBottomCbUnChecked();

    void onVertifyErrorForNoSelect();


    void doAllAdapterClear();


    void doSetDataSelectHasSingleLineAfterDel(List<AnotherScanInfo> targetNotSelects);


    void doSetDataSelectNoSingleLineAfterDel(List<AnotherScanInfo> targets);


    void doSetDataSelectNoSingleLineAndStillHasSingleLineAfterDel(List<AnotherScanInfo> targets);


    void doDelScanedDataSuccessToast();


    void doScrollToUiBottom();







    void doShowAlertDialog(String name);


    void doDestroyAlertDialog();
}
