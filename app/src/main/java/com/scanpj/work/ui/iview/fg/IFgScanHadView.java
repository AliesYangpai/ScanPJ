package com.scanpj.work.ui.iview.fg;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public interface IFgScanHadView extends IBaseView {

    void onDbDataBackSuccessGetScanHadRecords(List<ChickenInfoScanAbout> list);


    void onDbDataBackSuccessGetScanHadRecordsInLoadMore(List<ChickenInfoScanAbout> list);

    void onDbDataLoadMoreEnd();



    void doShowBatchAlertDialog(String name);


    void doDestroyBatcgAlertDialog();
}
