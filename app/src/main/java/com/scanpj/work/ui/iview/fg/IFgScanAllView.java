package com.scanpj.work.ui.iview.fg;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public interface IFgScanAllView extends IBaseView {

    void onDbDataBackSuccessGetChickenRecords(List<ChickenInfoRaw> list);


    void onDbDataBackSuccessGetChickenRecordsInLoadMore(List<ChickenInfoRaw> list);

    void onDbDataLoadMoreEnd();


    void doShowBatchAlertDialog(String name);


    void doDestroyBatcgAlertDialog();
}
