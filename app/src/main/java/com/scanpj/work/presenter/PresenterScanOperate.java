package com.scanpj.work.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.scanpj.work.App;
import com.scanpj.work.constant.ConstDbLocal;
import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.ConstLog;
import com.scanpj.work.constant.ConstSign;
import com.scanpj.work.constant.ConstSp;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.entity.ErrorEntity;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.logic.LogicChicken;
import com.scanpj.work.logic.LogicScan;
import com.scanpj.work.method.IChicken;
import com.scanpj.work.method.impl.IChickenImpl;
import com.scanpj.work.ui.iview.IScanOperateView;
import com.scanpj.work.universal.cache.db.dao.IBaseDao;
import com.scanpj.work.universal.cache.db.dao.impl.IChickenInfoRawDaoimpl;
import com.scanpj.work.universal.cache.db.dao.impl.IChickenInfoScanAboutimpl;
import com.scanpj.work.universal.cache.sp.SpUtil;
import com.scanpj.work.universal.logic.LogicLoadData;
import com.scanpj.work.universal.mgr.FgScanMgr;
import com.scanpj.work.universal.parse.HttpResult;
import com.scanpj.work.util.ToastUtil;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述  扫描操作的界面
 * 版本
 */

public class PresenterScanOperate extends BasePresenter<IScanOperateView> {


    private IScanOperateView iScanOperateView;

    private FgScanMgr fgScanMgr;
    private LogicScan logicScan;
    private LogicChicken logicChicken;
    private LogicLoadData logicLoadData;
    private IBaseDao<ChickenInfoScanAbout> iChickenInfoScanAboutIBaseDao;
    private IBaseDao<ChickenInfoRaw> iChickenInfoRawIBaseDao;

    private IChicken iChicken;

    public FgScanMgr getFgScanMgr() {
        return fgScanMgr;
    }

    public PresenterScanOperate(IScanOperateView iScanOperateView, FragmentManager fragmentManager, FragmentTransaction transaction) {
        this.iScanOperateView = iScanOperateView;
        this.fgScanMgr = new FgScanMgr(fragmentManager, transaction);
        this.logicScan = new LogicScan();
        this.logicChicken = new LogicChicken();
        this.iChickenInfoRawIBaseDao = new IChickenInfoRawDaoimpl();
        this.iChickenInfoScanAboutIBaseDao = new IChickenInfoScanAboutimpl();
        this.iChicken = new IChickenImpl();
        this.logicLoadData = new LogicLoadData();
    }


    /**
     * 判断当重新导入的时候，则清空fgCurrent中的数据
     * @param tag
     */
    public void doClearFgCurrentScanDataWhenReload(String tag) {


        if(logicLoadData.isReload(tag)) {
            iScanOperateView.doClearFgCurrentScanDataWhenReload();
        }
    }


    public void doSetInitData() {


        int  allListCount = iChickenInfoRawIBaseDao.findAllCount(ChickenInfoRaw.class);
        int scannedListCount = iChickenInfoScanAboutIBaseDao.findAllCount(ChickenInfoScanAbout.class);
        int scannedDenyListCount = iChickenInfoScanAboutIBaseDao.findCountByCondition(ChickenInfoScanAbout.class, ConstDbLocal.ScanAbout.FLAG, "2");
        int  scannedAccessListCount = iChickenInfoScanAboutIBaseDao.findCountByCondition(ChickenInfoScanAbout.class, ConstDbLocal.ScanAbout.FLAG, "1");


        iScanOperateView.doSetBottomTextAll(allListCount);
        iScanOperateView.doSetBottomTextHasScanned(scannedListCount);
        iScanOperateView.doSetBottomTextDeny(scannedDenyListCount);
        iScanOperateView.doSetBottomTextAccess(scannedAccessListCount);


//        List<ChickenInfoRaw> allList = iChickenInfoRawIBaseDao.findAll(ChickenInfoRaw.class);
//        List<ChickenInfoScanAbout> scannedList = iChickenInfoScanAboutIBaseDao.findAll(ChickenInfoScanAbout.class);
//        List<ChickenInfoScanAbout> scannedDenyList = iChickenInfoScanAboutIBaseDao.findByCondition(ChickenInfoScanAbout.class, ConstDbLocal.ScanAbout.FLAG, "2");
//        List<ChickenInfoScanAbout> scannedAccessList = iChickenInfoScanAboutIBaseDao.findByCondition(ChickenInfoScanAbout.class, ConstDbLocal.ScanAbout.FLAG, "1");
//
//
//        iScanOperateView.doSetBottomTextAll(allList.size());
//
//        if (vertifyNotNull.isNotNullListSize(scannedList)) {
//            iScanOperateView.doSetBottomTextHasScanned(scannedList.size());
//        } else {
//            iScanOperateView.doSetBottomTextHasScanned(0);
//        }
//
//        if (vertifyNotNull.isNotNullListSize(scannedDenyList)) {
//            iScanOperateView.doSetBottomTextDeny(scannedList.size());
//        } else {
//            iScanOperateView.doSetBottomTextDeny(0);
//        }
//
//
//        if (vertifyNotNull.isNotNullListSize(scannedAccessList)) {
//            iScanOperateView.doSetBottomTextAccess(scannedList.size());
//        } else {
//            iScanOperateView.doSetBottomTextAccess(0);
//        }

    }


    /**
     * 根据fragment的position获取对应的fragment
     *
     * @param position
     */
    public void doGetDifferentFragmentByPosition(int position) {

        fgScanMgr.getDifferentFragmentByPosition(position);
    }


    /**
     * 检查权限
     */
    public void doPermissionCheck(int requestCode, String... permissions) {

        iScanOperateView.doPermissionCheck(requestCode, permissions);

    }


    /**
     * 权限提示
     */
    public void doShowPermissionDialog() {

        iScanOperateView.doShowPermissionDialog();
    }


    /**
     * 扫描相关开始
     * *******************************************************************
     */

    /**
     * 初始化scan
     */
    public void doInitConfigScan() {
        iScanOperateView.doInitConfigScan();
    }


    public void doShowDeviceInitError() {

        iScanOperateView.onVertifyErrorScanInitError();
    }

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

        doEnableScanTypeCheck();
        doSetScanButtonTextStart();
        if (logicScan.isSingleScan(scanType)) {
            doStopSingleScan(barcode2DWithSoft);
            return;
        }

        if (logicScan.isContinueScan(scanType)) {
            doStopContinueScan(barcode2DWithSoft);
            return;
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
    public void doDealScanResult(String scanType, int length, byte[] bytes) {

        /**
         * 处理界面按钮
         */
        if (logicScan.isSingleScan(scanType)) {
            doEnableScanTypeCheck();
            doSetScanButtonTextStart();
            iScanOperateView.doCbUnChecked();
        }


        /**
         * 处理逻辑
         */
        if (logicScan.isScanDataSuccess(length)) {
            String data = new String(bytes, 0, length);

            Log.i("scan", "result:" + data + " 扫描类型：" + scanType);
            iScanOperateView.onDataBackSuccessScan(data);
        } else {


            /**
             * 处理界面按钮
             */
            if (logicScan.isContinueScan(scanType)) {
                doEnableScanTypeCheck();
                doSetScanButtonTextStart();
            }

            if (logicScan.isScanDataErrorCancel(length)) {
//                iScanOperateView.onVertifyErrorScanCancel();
            } else if (logicScan.isScanDataErrorTimeOut(length)) {
                iScanOperateView.onVertifyErrorScanTimeOut();

            } else {
                iScanOperateView.onVertifyErrorScanFail();
            }

        }


    }


    /**
     * 文本开始
     */
    public void doSetScanButtonTextStart() {

        iScanOperateView.doSetScanButtonTextStart();
    }


    /**
     * 文本停止
     */
    public void doSetScanButtonTextStop() {

        iScanOperateView.doSetScanButtonTextStop();
    }


    /**
     * 按钮禁用
     */
    public void doUnableScanTypeCheck() {
        iScanOperateView.doUnableScanTypeCheck();
    }

    /**
     * 按钮启用
     */
    public void doEnableScanTypeCheck() {

        iScanOperateView.doEnableScanTypeCheck();
    }


    /**
     * 开始扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStartSingleScan(Barcode2DWithSoft barcode2DWithSoft) {
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
            barcode2DWithSoft.scan();
            doUnableScanTypeCheck();
            doSetScanButtonTextStop();
        }
    }


    /**
     * 开始连续扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStartContinueScan(Barcode2DWithSoft barcode2DWithSoft) {
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
            barcode2DWithSoft.startHandsFree();
            doUnableScanTypeCheck();
            doSetScanButtonTextStop();
        }
    }

    /**
     * 停止扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStopSingleScan(Barcode2DWithSoft barcode2DWithSoft) {
//        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
//            barcode2DWithSoft.stopScan();
//        }


        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
            try {
                barcode2DWithSoft.stopScan();
            } catch (Exception e) {

                Log.i(ConstLog.SCAN_ERROR, "ScanOperateActivity,单次扫描stop异常：" + e.toString());
            }

        }


    }


    /**
     * 停止连续扫描
     *
     * @param barcode2DWithSoft
     */
    public void doStopContinueScan(Barcode2DWithSoft barcode2DWithSoft) {
//        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
//            barcode2DWithSoft.stopHandsFree();
//        }

        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {

            try {
                barcode2DWithSoft.stopHandsFree();
            } catch (Exception e) {
                Log.i(ConstLog.SCAN_ERROR, "ScanOperateActivity,连续扫描stop异常：" + e.toString());
            }

        }


    }


    /**
     * 关闭销毁扫描
     *
     * @param barcode2DWithSoft
     */
    public void doCloseScan(Barcode2DWithSoft barcode2DWithSoft) {
        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {

            try {
                barcode2DWithSoft.close();
            }catch (Exception e) {

                Log.i(ConstLog.SCAN_ERROR, "ScanOperateActivity,扫描close异常：" + e.toString());
            }

        }




//        if (vertifyNotNull.isNotNullObj(barcode2DWithSoft)) {
//            barcode2DWithSoft.close();
//        }
    }

    /**
     * 扫描相关结束
     * *******************************************************************
     */


    /**
     * 处理扫描结果
     *
     * @param taget
     * @param args
     */
    public void doDealCheckScanResultToLocal(String taget, int hasScanned, int deny, int access, String... args) {


        List<ChickenInfoScanAbout> hadScanedlist = iChickenInfoScanAboutIBaseDao.findAll(ChickenInfoScanAbout.class);
        if (vertifyNotNull.isNotNullListSize(hadScanedlist)) {


            if (logicChicken.isHasBeenScaned(taget, hadScanedlist)) {
                iScanOperateView.doVertifyErrorHasScanned();
                return;
            }


        }


        List<ChickenInfoRaw> list = iChickenInfoRawIBaseDao.findByCondition(ChickenInfoRaw.class, args);
        if (vertifyNotNull.isNotNullListSize(list)) {

//            hasScanned+=1;
            iScanOperateView.doSetBottomTextHasScanned(++hasScanned);

            ChickenInfoRaw chickenInfoRaw = list.get(0);
            int stepRaw = chickenInfoRaw.getTotal();
            long stepEnter = SpUtil.getInstance().getLongValue(ConstSp.SP_KEY_ENTER_STEP, ConstSp.SP_VALUE.DEFAULT_INT);
            boolean result = logicChicken.isAccessStep(stepRaw, stepEnter);
            if (result) {


                iScanOperateView.doPlaySoundAccess();
                chickenInfoRaw.setFlag(ConstLocalData.SCAN_ACCESS);
//                access+=1;
                iScanOperateView.doSetBottomTextAccess(++access);
            } else {

                iScanOperateView.doPlaySoundDeny();

                chickenInfoRaw.setFlag(ConstLocalData.SCAN_DENY);
                iScanOperateView.doSetBottomTextDeny(++deny);
            }


//            deny+=1;

            iScanOperateView.doSaveData(chickenInfoRaw);
            iScanOperateView.doSetScanDataToFgUi(chickenInfoRaw);
        } else {
            iScanOperateView.onVertifyErrorNoSuchData();
        }

    }


    public void doSaveScanResultData(ChickenInfoRaw chickenInfoRaw) {

        ChickenInfoScanAbout chickenInfoScanAbout = new ChickenInfoScanAbout();
        chickenInfoScanAbout.setBatchid(chickenInfoRaw.getBatchid());
        chickenInfoScanAbout.setHouseid(chickenInfoRaw.getHouseid());
        chickenInfoScanAbout.setTotal(chickenInfoRaw.getTotal());
        chickenInfoScanAbout.setFlag(chickenInfoRaw.getFlag());
        chickenInfoScanAbout.setRingid(chickenInfoRaw.getRingid());
        chickenInfoScanAbout.setShortUrl(chickenInfoRaw.getShortUrl());
        chickenInfoScanAbout.setIsscaned(true);
        chickenInfoScanAbout.setSync(false);
        chickenInfoScanAbout.setName(chickenInfoRaw.getName());
        iChickenInfoScanAboutIBaseDao.save(chickenInfoScanAbout);
    }


//    public void doChickenInfoUpdate(List<ChickenInfoScanAbout> list) {
//
//
//        for (ChickenInfoScanAbout chickenInfoScanAbout : list) {
//            chickenInfoScanAbout.setSync(true);
//            chickenInfoScanAbout.update(chickenInfoScanAbout.getId());
//        }
//
//    }


    /**
     * 更新同步过 数据的表
     *
     * @param
     */
    public void doUpdateLocalChickenScanData(String... arg) {


        List<ChickenInfoScanAbout> list = iChickenInfoScanAboutIBaseDao.findByCondition(ChickenInfoScanAbout.class, arg);

        if (vertifyNotNull.isNotNullListSize(list)) {
            for (ChickenInfoScanAbout chickenInfoScanAbout : list) {
                chickenInfoScanAbout.setSync(true);
                chickenInfoScanAbout.update(chickenInfoScanAbout.getId());
            }
        }


    }

    /**
     * 初次上传数据
     *
     * @param url
     * @param uploadLimite
     * @param uploadOffset
     */
    public void doUpload(String url, final int uploadLimite, final int uploadOffset, String... condition) {


        final List<ChickenInfoScanAbout> list = iChickenInfoScanAboutIBaseDao.findAllWithLimiteOffsetByCondition(ChickenInfoScanAbout.class, uploadLimite, uploadOffset, condition);


        if (!vertifyNotNull.isNotNullListSize(list)) {

            iScanOperateView.doVertifyErrorNoScanBeforeSync();
            return;
        }

        iChicken.doSetScanResault(url, list, new OnDataBackListener() {
            @Override
            public void onStart() {
                iScanOperateView.doShowUploadDialog();
            }

            @Override
            public void onSuccess(String data) {


                HttpResult<ChickenInfoScanAbout> parseToObj = parseSerilizable.getParseDataListZTE(data, ChickenInfoScanAbout.class);


                if (vertifyNotNull.isNotNullObj(parseToObj)) {
                    if (parseToObj.code == HttpConst.CODE_SUCCESS) {


                        iScanOperateView.onDataBackSuccessAndContinue(uploadLimite, uploadOffset);

                    } else {

                        iScanOperateView.onDataBackFail(parseToObj.code, parseToObj.msg);
                        iScanOperateView.doDestroyUploadDialog();
                    }
                } else {

                    iScanOperateView.doDestroyUploadDialog();
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iScanOperateView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iScanOperateView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iScanOperateView.doDestroyUploadDialog();
            }

            @Override
            public void onFinish() {
            }
        });
    }


    /**
     * 继续删除上传数据
     *
     * @param url
     * @param uploadLimite
     * @param uploadOffset
     */
    public void doUploadContinue(String url, final int uploadLimite, final int uploadOffset, String... condition) {


        final List<ChickenInfoScanAbout> list = iChickenInfoScanAboutIBaseDao.findAllWithLimiteOffsetByCondition(ChickenInfoScanAbout.class, uploadLimite, uploadOffset, condition);


        if (!vertifyNotNull.isNotNullListSize(list)) {


            iScanOperateView.doDestroyUploadDialog();
            iScanOperateView.doVertifySyncFinish();
            return;
        }

        iChicken.doSetScanResault(url, list, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                HttpResult<ChickenInfoScanAbout> parseToObj = parseSerilizable.getParseDataListZTE(data, ChickenInfoScanAbout.class);


                if (vertifyNotNull.isNotNullObj(parseToObj)) {
                    if (parseToObj.code == HttpConst.CODE_SUCCESS) {

                        iScanOperateView.onDataBackSuccessAndContinue(uploadLimite, uploadOffset);

                    } else {

                        iScanOperateView.doDestroyUploadDialog();
                        iScanOperateView.onDataBackFail(parseToObj.code, parseToObj.msg);
                    }
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iScanOperateView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iScanOperateView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iScanOperateView.doDestroyUploadDialog();
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
