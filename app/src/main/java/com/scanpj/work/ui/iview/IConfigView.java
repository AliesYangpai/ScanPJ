package com.scanpj.work.ui.iview;


import com.scanpj.work.entity.BatchInfo;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.ui.IBasePermissionView;
import com.scanpj.work.ui.IBaseView;
import com.scanpj.work.universal.connectremote.RemoteDbConnect;

import java.sql.Connection;
import java.util.List;

/**
 * Created by admin on 2018/5/9.
 * 类描述  配置 的
 * 版本
 */
public interface IConfigView extends IBasePermissionView {


    /**
     * 这里暂时没有任何操作
     */

    void doTitleCbVisible();

    void doTitleCbGone();

    void onDataBackSuccessForChickenBatchDetail(List<BatchInfo> list);


    void doAllSelect();


    void doAllUnSelect();


    void doRightCbTextAllSelect();

    void doRightTextAllCancel();

    void doRxRemoteDbConnect(RemoteDbConnect remoteDbConnect);

    /**
     * 查询数据
     *
     * @param connection
     */
    void doRxRemoteDbQuery(Connection connection, RemoteDbConnect remoteDbConnect, String sql);

    void doShowRemoteDbDialog();


    void doDestroyRemoteDbDialog();


    void onVertifyErrorConnectRemoteDbFail();


    void onVertifyErrorNullStep();


    void onVertifyError0Step();


    void onVertifyErrorNoSelectBatch();

    void onVertifyErrorNoSelectData();

    void onDataBackSuccessGoToOperate();

    void doShowReloadAlertDialog();


    void doLoadData();


    void doTitleRightCbAllCheckedOrNotInItemSelect(boolean isCheck);



    void doScrollToUiBottom();



    void doGotoOpeatePageFromFirstLoad();
    void doGotoOpeatePageFromReload();
}