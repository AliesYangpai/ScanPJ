package com.scanpj.work.presenter;

import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.ConstDbRemote;
import com.scanpj.work.constant.ConstSign;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.BatchInfo;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ErrorEntity;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.logic.LogicChicken;
import com.scanpj.work.logic.LogicChickenHouse;
import com.scanpj.work.method.IChicken;
import com.scanpj.work.method.IChickenBatch;
import com.scanpj.work.method.impl.IChickenBatchImpl;
import com.scanpj.work.method.impl.IChickenImpl;
import com.scanpj.work.ui.iview.IConfigView;
import com.scanpj.work.universal.cache.db.dao.IBaseDao;
import com.scanpj.work.universal.cache.db.dao.impl.IChickenInfoRawDaoimpl;
import com.scanpj.work.universal.cache.db.dao.impl.IClearDataDao;
import com.scanpj.work.universal.connectremote.RemoteDbConnect;
import com.scanpj.work.universal.function.FunctionSql;
import com.scanpj.work.universal.logic.LogicLoadData;
import com.scanpj.work.universal.parse.HttpResult;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


/**
 * Created by admin on 2018/5/9.
 * 类描述 配置界面的presenter
 * 版本
 */
public class PresenterConfig extends BasePresenter<IConfigView> {

    private IConfigView iConfigView;

    private IChickenBatch iChickenBatch;

    private IBaseDao<ChickenInfoRaw> iChickenInfoRawDao;

    private RemoteDbConnect remoteDbConnect;


    private LogicChicken logicChicken;
    private LogicChickenHouse logicChickenHouse;
    private LogicLoadData logicLoadData;
    private FunctionSql functionSql;


    private IClearDataDao iClearDataDao;

    public PresenterConfig(IConfigView iConfigView) {
        this.iConfigView = iConfigView;
        this.iChickenBatch = new IChickenBatchImpl();
        this.remoteDbConnect = new RemoteDbConnect();
        this.iChickenInfoRawDao = new IChickenInfoRawDaoimpl();
        this.logicChicken = new LogicChicken();
        this.logicChickenHouse = new LogicChickenHouse();
        this.functionSql = new FunctionSql();
        this.logicLoadData = new LogicLoadData();
        this.iClearDataDao = new IClearDataDao();
    }


    /**
     * 检查权限
     */
    public void doPermissionCheck(int requestCode, String... permissions) {

        iConfigView.doPermissionCheck(requestCode, permissions);

    }


    /**
     * 权限提示
     */
    public void doShowPermissionDialog() {

        iConfigView.doShowPermissionDialog();
    }


    /**
     * 根据获取批次详情
     *
     * @param url
     */
    public void doGetChickenBatchRecords(String url) {

        iChickenBatch.doGetChickenBatchRecords(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iConfigView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                HttpResult<List<BatchInfo>> parseToObj = parseSerilizable.getParseDataListZTE(data, BatchInfo.class);


                if (vertifyNotNull.isNotNullObj(parseToObj)) {
                    if (parseToObj.code == HttpConst.CODE_SUCCESS) {
                        List<BatchInfo> list = parseToObj.data;
                        if (vertifyNotNull.isNotNullListSize(list)) {
                            iConfigView.onDataBackSuccessForChickenBatchDetail(list);
                            iConfigView.doTitleCbVisible();
                        }else {
                            iConfigView.doTitleCbGone();
                        }
                    } else {

                        iConfigView.doTitleCbGone();
                        iConfigView.onDataBackFail(parseToObj.code, parseToObj.msg);
                    }
                }

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iConfigView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iConfigView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iConfigView.doTitleCbGone();

            }

            @Override
            public void onFinish() {
                iConfigView.dismissLoadingDialog();
            }
        });
    }


    public void doAllSelect(List<BatchInfo> list, Map map) {

        if (vertifyNotNull.isNotNullListSize(list)) {
            for (BatchInfo batchInfo : list) {
                map.put(String.valueOf(batchInfo.getId()), batchInfo.getId());
            }

            iConfigView.doAllSelect();
            iConfigView.doRightTextAllCancel();
        }
    }


    public void doDealSelect(boolean isChecked, List<BatchInfo> list, Map map) {


        if (isChecked) {


           this.doAllSelect(list, map);
//            iConfigView.doAllUnSelect();

        } else {
            this.doAllUnSelect(list,map);
//            iConfigView.doAllSelect();
        }

    }




    public void doAllUnSelect( List<BatchInfo> list, Map map) {
        if (vertifyNotNull.isNotNullListSize(list)) {

            if(vertifyNotNull.isNotNullObj(map)) {

                map.clear();
            }
            iConfigView.doAllUnSelect();
            iConfigView.doRightCbTextAllSelect();
        }

    }


    /**
     * 连接远程db
     */
    public void doConnectRemoteDb() {


//        if (logicChicken.isNullStep(step)) {
//
//            iConfigView.onVertifyErrorNullStep();
//            return;
//        }
//
//        if (logicChicken.isStep0(step)) {
//            iConfigView.onVertifyError0Step();
//            return;
//        }
//
//        if (logicChickenHouse.isNoSelectBatch(map)) {
//
//            iConfigView.onVertifyErrorNoSelectBatch();
//            return;
//        }


        iConfigView.doRxRemoteDbConnect(remoteDbConnect);
    }


    /**
     * 查询
     *
     * @param connection
     * @param remoteDbConnect
     */
    public void doQueryRemoteDb(Connection connection, RemoteDbConnect remoteDbConnect, Map map) {


        if (vertifyNotNull.isNotNullObj(connection)) {


            String targetData = functionSql.getStringFromMap(map);


            targetData = ConstDbRemote.SQL + ConstSign.SPACE + targetData;

//            targetData = ConstDbRemote.SQL + ConstSign.SPACE + "(198,197,199)";


            iConfigView.doRxRemoteDbQuery(connection, remoteDbConnect, targetData);
        } else {

            iConfigView.onVertifyErrorConnectRemoteDbFail();
        }

    }


    public void doSaveRemoteDataToLocal(List<ChickenInfoRaw> list) {


        if (!vertifyNotNull.isNotNullListSize(list)) {

            iConfigView.onVertifyErrorNoSelectData();
            return;
        }

        List<ChickenInfoRaw> chickenInfoRaws = iChickenInfoRawDao.findAll(ChickenInfoRaw.class);

        if (vertifyNotNull.isNotNullListSize(chickenInfoRaws)) {

            iChickenInfoRawDao.deleteAll(ChickenInfoRaw.class);
        }

        iChickenInfoRawDao.saveAll(list);
        iConfigView.onDataBackSuccessGoToOperate();
    }


    public void doDealLoadOrReload(String step, Map map, String currentTag) {


        if (logicChicken.isNullStep(step)) {

            iConfigView.onVertifyErrorNullStep();
            return;
        }

        if (logicChicken.isStep0(step)) {
            iConfigView.onVertifyError0Step();
            return;
        }

        if (logicChickenHouse.isNoSelectBatch(map)) {

            iConfigView.onVertifyErrorNoSelectBatch();
            return;
        }


        if (logicLoadData.isReload(currentTag)) {
            iConfigView.doShowReloadAlertDialog();
        } else {

            iConfigView.doLoadData();
        }

    }


    /**
     * 滑动到界面底部
     * @param step
     * @param map
     */
    public void doDealInSoftKey(String step, Map map) {


        if (logicChicken.isNullStep(step)) {

            iConfigView.onVertifyErrorNullStep();
            return;
        }

        if (logicChicken.isStep0(step)) {
            iConfigView.onVertifyError0Step();
            return;
        }

        if (logicChickenHouse.isNoSelectBatch(map)) {

            iConfigView.onVertifyErrorNoSelectBatch();
            return;
        }


        iConfigView.doScrollToUiBottom();


    }



    public void doDealClearScannedOrNot(String tag) {

        if (logicLoadData.isReload(tag)) {
            iClearDataDao.doClearTableExceptScanAbout();
            iConfigView.doGotoOpeatePageFromReload();
        }else {
            iConfigView.doGotoOpeatePageFromFirstLoad();

        }


    }

    public void doChangeTitleRightCbUi(Map mapCurrent,List<BatchInfo> list) {

        if(!vertifyNotNull.isNotNullListSize(list)) {
            return;
        }

        if(logicChickenHouse.isItemNoSelectBatch(mapCurrent)) {

            iConfigView.doTitleRightCbAllCheckedOrNotInItemSelect(false);
            return;
        }


        if(logicChickenHouse.isItemAllSelectBatch(mapCurrent,list.size())) {

            iConfigView.doTitleRightCbAllCheckedOrNotInItemSelect(true);

            return;

        }


    }


}