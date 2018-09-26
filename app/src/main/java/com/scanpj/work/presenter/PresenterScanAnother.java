package com.scanpj.work.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.scanpj.work.App;
import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.ConstHz;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.ConstLog;
import com.scanpj.work.constant.ConstSign;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ErrorEntity;
import com.scanpj.work.function.FunctionScanAnother;
import com.scanpj.work.function.FunctionScanInfoList;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.logic.LogicQrCode;
import com.scanpj.work.logic.LogicScan;
import com.scanpj.work.logic.LogicScanAnother;
import com.scanpj.work.method.IScanAnother;
import com.scanpj.work.method.impl.IScanAnotherImpl;
import com.scanpj.work.ui.iview.IScanAnotherView;
import com.scanpj.work.ui.iview.IScanOperateView;
import com.scanpj.work.universal.logic.LogicMap;
import com.scanpj.work.universal.mgr.FgScanMgr;
import com.scanpj.work.universal.parse.HttpResult;
import com.scanpj.work.util.ToastUtil;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.net.PortUnreachableException;
import java.util.List;
import java.util.Map;

/**
 * Created by Alie on 2018/6/11.
 * 类描述  另外的扫码界面
 * 版本
 */

public class PresenterScanAnother extends BasePresenter<IScanAnotherView> {


    private IScanAnotherView iScanAnotherView;
    private LogicScan logicScan;
    private IScanAnother iScanAnother;

    private LogicQrCode logicQrCode;

    private LogicScanAnother logicScanAnother;
    private LogicMap logicMap;
    private FunctionScanInfoList functionScanInfoList;

    public PresenterScanAnother(IScanAnotherView iScanAnotherView) {
        this.iScanAnotherView = iScanAnotherView;
        this.logicScan = new LogicScan();
        this.iScanAnother = new IScanAnotherImpl();
        this.logicQrCode = new LogicQrCode();
        this.logicScanAnother = new LogicScanAnother();
        this.functionScanInfoList = new FunctionScanInfoList();
        this.logicMap = new LogicMap();
    }


    /**
     * 初始化scan
     */
    public void doInitConfigScan() {
        iScanAnotherView.doInitConfigScan();
    }


    public void doShowDeviceInitError() {

        iScanAnotherView.onVertifyErrorScanInitError();
    }


    /**
     * 开始扫描或者停止扫描
     *
     * @param statues
     * @param scanType
     * @param barcode2DWithSoft
     */
    public void doDealStartOrStopScan(String statues, String scanType, Barcode2DWithSoft barcode2DWithSoft) {

        if (logicScan.isStop(statues)) {

            doDealStartScan(scanType, barcode2DWithSoft);
        }

        if (logicScan.isScanning(statues)) {

            doDealStopScan(scanType, barcode2DWithSoft);
        }


    }


    public void doStopScanWhenCheckTrue(String statues, String scanType, Barcode2DWithSoft barcode2DWithSoft) {
        if (logicScan.isScanning(statues)) {
            doDealStopScan(scanType, barcode2DWithSoft);
            return;
        }
    }

//

    /**
     * 开始逻辑判断扫描
     *
     * @param scanType
     * @param barcode2DWithSoft
     */
    public void doDealStartScan(String scanType, Barcode2DWithSoft barcode2DWithSoft) {
        if (logicScan.isSingleScan(scanType)) {
            doStartSingleScan(barcode2DWithSoft);
        }


        if (logicScan.isContinueScan(scanType)) {
            doStartContinueScan(barcode2DWithSoft);
        }


    }


    /**
     * 逻辑判断停止扫描
     *
     * @param scanType
     * @param barcode2DWithSoft
     */
    public void doDealStopScan(String scanType, Barcode2DWithSoft barcode2DWithSoft) {


        if (logicScan.isSingleScan(scanType)) {
            doStopSingleScan(barcode2DWithSoft);
            return;
        }

        if (logicScan.isContinueScan(scanType)) {
            doStopContinueScan(barcode2DWithSoft);
        }


    }

    /**
     * 处理停止扫描
     *
     * @param scanType
     * @param barcode2DWithSoft
     */
    public void doDealCloseScan(String scanType, Barcode2DWithSoft barcode2DWithSoft) {

        if (logicScan.isSingleScan(scanType)) {
            doStopSingleScan(barcode2DWithSoft);
        }
        if (logicScan.isContinueScan(scanType)) {

            doStopContinueScan(barcode2DWithSoft);
        }


        doCloseScan(barcode2DWithSoft);
    }

    /**
     * 处理扫描结果
     *
     * @param scanType
     * @param length
     * @param bytes
     */
    public void doDealScanResult(String scanType, int length, byte[] bytes, List<String> tempDataList) {

        /**
         * 处理逻辑
         */

        /**
         * 处理界面按钮
         */
        if (logicScan.isSingleScan(scanType)) {

            doSetScanButtonImgStart();
        }


        if (logicScan.isScanDataSuccess(length)) {

            String string = new String(bytes, 0, length);


            Log.i("scansssss", "扫描结果：" + string);


            iScanAnotherView.doPlaySound();
            if (logicQrCode.isCode(string)) {

                if (logicQrCode.isTempContainsCode(tempDataList, string)) {

                    iScanAnotherView.doVertifyErrorHasCodeId();
                } else {
                    iScanAnotherView.doSetDataToUiCodeId(string);
                }
            } else {
                if (logicQrCode.isTempContainsRingId(tempDataList, string)) {
                    iScanAnotherView.doVertifyErrorHasRingId();
                } else {
                    iScanAnotherView.doSetDataToUiFeetRingId(string);
                }

            }

        } else {


            if (logicScan.isScanDataErrorCancel(length)) {
//                iScanAnotherView.onVertifyErrorScanCancel();
            } else if (logicScan.isScanDataErrorTimeOut(length)) {
                iScanAnotherView.onVertifyErrorScanTimeOut();
            } else {
                iScanAnotherView.onVertifyErrorScanFail();
            }

        }


    }


    /**
     * 开始单次扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStartSingleScan(Barcode2DWithSoft barcode2DWithSoft) {
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
            barcode2DWithSoft.scan();
            doSetScanButtonImgStop();
            doSetScanStatus(ConstLocalData.IS_SCANNING);
        }
    }


    /**
     * 持续扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStartContinueScan(Barcode2DWithSoft barcode2DWithSoft) {
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
            barcode2DWithSoft.startHandsFree();
            doSetScanButtonImgStop();
            doSetScanStatus(ConstLocalData.IS_SCANNING);

        }
    }


    /**
     * 停止单次扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStopSingleScan(Barcode2DWithSoft barcode2DWithSoft) {
        doSetScanButtonImgStart();
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {

            try {

                barcode2DWithSoft.stopScan();
                doSetScanStatus(ConstLocalData.IS_STOP);
            } catch (Exception e) {
                Log.i(ConstLog.SCAN_ERROR, "ScanAnotherActivity,单次扫描stop异常：" + e.toString());
            }


        }


//        doSetScanButtonImgStart();
//        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
//            barcode2DWithSoft.stopScan();
//            doSetScanStatus(ConstLocalData.IS_STOP);
//        }
    }


    /**
     * 停止单次扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStopContinueScan(Barcode2DWithSoft barcode2DWithSoft) {
        doSetScanButtonImgStart();
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {

            try {

                barcode2DWithSoft.stopHandsFree();
                doSetScanStatus(ConstLocalData.IS_STOP);
            } catch (Exception e) {
                Log.i(ConstLog.SCAN_ERROR, "ScanAnotherActivity,连续扫描stop异常：" + e.toString());
            }


//            barcode2DWithSoft.stopHandsFree();
//            doSetScanStatus(ConstLocalData.IS_STOP);
        }


//        doSetScanButtonImgStart();
//        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
//            barcode2DWithSoft.stopHandsFree();
//            doSetScanStatus(ConstLocalData.IS_STOP);
//        }


    }


    /**
     * 关闭扫描
     *
     * @param barcode2DWithSoft
     */
    public void doCloseScan(Barcode2DWithSoft barcode2DWithSoft) {
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
            try {
                barcode2DWithSoft.close();
            } catch (Exception e) {
                Log.i(ConstLog.SCAN_ERROR, "ScanAnotherActivity,扫描Close异常：" + e.toString());
            }
        }

//        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
//            barcode2DWithSoft.close();
//        }
    }


    /**
     * 扫描已开始
     */
    public void doSetScanButtonImgStart() {

        iScanAnotherView.doSetScanButtonImgStart();
    }


    /**
     * 扫描已停止
     */
    public void doSetScanButtonImgStop() {

        iScanAnotherView.doSetScanButtonImgStop();
    }


    /**
     * 设置扫描状态
     */


    public void doSetScanStatus(String status) {

        iScanAnotherView.doSetScanStatus(status);
    }


    public void doCommit(String url, final List<AnotherScanInfo> list) {


        if (logicScanAnother.isNoScaned(list)) {

            iScanAnotherView.onVertifyErrorNoScan();
            return;
        }


        if (!logicScanAnother.isFullLine(list)) {


            iScanAnotherView.onVertifyErrorNotCompleteScan();
            return;
        }


//        list.remove(list.size() - 1);


        iScanAnother.doCommitScanResult(url, list, new OnDataBackListener() {
            @Override
            public void onStart() {
                iScanAnotherView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                HttpResult<AnotherScanInfo> parseToObj = parseSerilizable.getParseDataListZTE(data, AnotherScanInfo.class);


                if (vertifyNotNull.isNotNullObj(parseToObj)) {
                    if (parseToObj.code == HttpConst.CODE_SUCCESS) {

                        iScanAnotherView.onDataBackSuccessForCommitAndRemoveListSize(list);

                        if (vertifyNotNull.isNotNullString(parseToObj.msg)) {


                            String msg = parseToObj.msg;
                            if (msg.contains(ConstSign.JS_RELINE_TIB)) {

                                msg = msg.replaceAll(ConstSign.JS_RELINE_TIB, ConstSign.CHANGE_LINE);
                            }
                            iScanAnotherView.doShowAlertDialog(msg);
                        }else {

                            iScanAnotherView.onDataBackSuccessForCommit();
                        }
                    } else {

                        iScanAnotherView.onDataBackFail(parseToObj.code, parseToObj.msg);
                    }

                    iScanAnotherView.doClearAllAddNewLine();
                }

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iScanAnotherView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iScanAnotherView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iScanAnotherView.dismissLoadingDialog();
            }
        });


    }


    public void doAddNewLine() {

        iScanAnotherView.doAddNewLine();
    }


    public void doClearAllAndAddNewLine() {

        iScanAnotherView.doClearAllAddNewLine();
    }


    public void doShowTopRightCbView() {

        iScanAnotherView.doShowTopRightCbView();
    }

    public void doHideTopRightCbView() {
        iScanAnotherView.doHideTopRightCbView();
    }

    public void doSetTopRightCbText(String text) {
        iScanAnotherView.doSetTopRightCbText(text);
    }


    public void doShowBottomSelectLayout() {
        iScanAnotherView.doShowBottomSelectLayout();
    }


    public void doHindBottomSelectLayout() {
        iScanAnotherView.doHindBottomSelectLayout();
    }


    public void doInvisibleImageViewOccupy() {
        iScanAnotherView.doInvisibleImageViewOccupy();
    }

    public void doHideImageViewOccupy() {
        iScanAnotherView.doHideImageViewOccupy();
    }


    public void doAdapterShowCheck(List<AnotherScanInfo> list) {

        if (vertifyNotNull.isNotNullListSize(list)) {


            List<AnotherScanInfo> targetList = functionScanInfoList.getTagetScanInfoInStartCheck(list);
            iScanAnotherView.doAdapterShowCheck(targetList);
        }


    }


    public void doAdapterHideCheck(List<AnotherScanInfo> list) {
        if (vertifyNotNull.isNotNullListSize(list)) {


            List<AnotherScanInfo> targetList = functionScanInfoList.getTagetScanInfoInCloseCheck(list);
            iScanAnotherView.doAdapterHideCheck(targetList);
        }
    }


    public void doSetBottomCheckTextAllSelect() {

        iScanAnotherView.doSetBottomCheckTextAllSelect();
    }


    public void doSetBottomCheckTextAllUnSelect() {
        iScanAnotherView.doSetBottomCheckTextAllUnSelect();
    }


    public void doSelectAll() {
        iScanAnotherView.doSelectAll();
    }


    public void doUnSelectAll() {
        iScanAnotherView.doUnSelectAll();
    }


    public void doDealUpdateBottomSelectUi(Map map, List<AnotherScanInfo> list) {

        int selectCount = logicMap.getMapSelectedSize(map);
        if (selectCount == list.size()) {
            iScanAnotherView.doSetBottomCbChecked();
        }

    }

    public void doDealUpdateBottomUnSelectUi(Map map, List<AnotherScanInfo> list) {

        int unSelectCount = logicMap.getMapUnSelectedSize(map);
        if (unSelectCount == list.size()) {
            iScanAnotherView.doSetBottomCbUnChecked();
        }

    }


    public void doDealSelectAndDelet(Map<Integer, Boolean> map, List<AnotherScanInfo> list, List<String> tempDataList) {

        int selectSize = logicMap.getMapSelectedSize(map);

        if (selectSize == 0) {
            iScanAnotherView.onVertifyErrorForNoSelect();
            return;
        }


        List<Integer> integersNotSelect = logicMap.getUnSelectIndexFromMap(map);
        List<Integer> integersSelect = logicMap.getSelectIndexFromMap(map);
//        iScanAnotherView.doSetBottomCbChecked();

        List<AnotherScanInfo> targetNotSelect = functionScanInfoList.getUnSelectAnotherScanInfoListFromSelectMap(integersNotSelect, list);

        List<AnotherScanInfo> targetSelect = functionScanInfoList.getSelectAnotherScanInfoListFromSelectMap(integersSelect, list);


        if (targetNotSelect.size() == 0) {


            tempDataList.clear();

            iScanAnotherView.doAllAdapterClear();

        } else {


            if (logicScanAnother.isHasSingleLine(targetSelect)) {

                if (tempDataList.size() != 0) {
                    functionScanInfoList.doDealTempScanList(targetSelect, tempDataList);
                }

                iScanAnotherView.doSetDataSelectHasSingleLineAfterDel(targetNotSelect);
                return;
            } else {

                if (tempDataList.size() != 0) {
                    functionScanInfoList.doDealTempScanList(targetSelect, tempDataList);
                }

                if (logicScanAnother.isHasSingleLine(targetNotSelect)) {

                    iScanAnotherView.doSetDataSelectNoSingleLineAndStillHasSingleLineAfterDel(targetNotSelect);
                } else {
                    iScanAnotherView.doSetDataSelectNoSingleLineAfterDel(targetNotSelect);
                }


            }

        }
    }


    public void doShowDelScanedDataSuccessToast() {

        iScanAnotherView.doDelScanedDataSuccessToast();
    }


    /**
     * 滑动到底部
     */
    public void doScrollToUiBottom() {
        iScanAnotherView.doScrollToUiBottom();
    }






    public void doDestroyAlertDialog() {
        iScanAnotherView.doDestroyAlertDialog();
    }
}
