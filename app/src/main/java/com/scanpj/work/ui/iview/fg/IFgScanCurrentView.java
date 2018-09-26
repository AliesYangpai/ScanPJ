package com.scanpj.work.ui.iview.fg;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.ui.IBaseView;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public interface IFgScanCurrentView extends IBaseView {


    void doSetScanDataToUi(ChickenInfoRaw chickenInfoRaw);

    void doSetClearData();

    void doShowBatchAlertDialog(String name);


    void doDestroyBatcgAlertDialog();


//    void doRecycleViewScrollTop();
}
