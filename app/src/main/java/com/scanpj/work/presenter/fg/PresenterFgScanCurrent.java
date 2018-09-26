package com.scanpj.work.presenter.fg;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.presenter.BasePresenter;
import com.scanpj.work.ui.iview.fg.IFgScanCurrentView;

/**
 * Created by Alie on 2018/6/11.
 * 类描述  当前扫描显示的fg
 * 版本
 */

public class PresenterFgScanCurrent extends BasePresenter<IFgScanCurrentView> {

    private IFgScanCurrentView iFgScanCurrentView;

    public PresenterFgScanCurrent(IFgScanCurrentView iFgScanCurrentView) {
        this.iFgScanCurrentView = iFgScanCurrentView;
    }


    public void doSetScanDataToUi(ChickenInfoRaw chickenInfoRaw) {


    }




    public void doShowBatchAlertDialog(ChickenInfoRaw chickenInfoRaw) {


        if (vertifyNotNull.isNotNullObj(chickenInfoRaw)) {

            String name = chickenInfoRaw.getName();
            iFgScanCurrentView.doShowBatchAlertDialog(name);
        }
    }


    public void doDestroyBatcgAlertDialog() {
        iFgScanCurrentView.doDestroyBatcgAlertDialog();
    }




//    public void doRecycleViewScrollTop() {
//
//        iFgScanCurrentView.doRecycleViewScrollTop();
//    }


}
