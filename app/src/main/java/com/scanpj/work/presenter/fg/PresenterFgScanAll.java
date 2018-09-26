package com.scanpj.work.presenter.fg;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.presenter.BasePresenter;
import com.scanpj.work.ui.iview.fg.IFgScanAllView;
import com.scanpj.work.ui.iview.fg.IFgScanCurrentView;
import com.scanpj.work.universal.cache.db.dao.IBaseDao;
import com.scanpj.work.universal.cache.db.dao.impl.IChickenInfoRawDaoimpl;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述  全部的data数据fg
 * 版本
 */

public class PresenterFgScanAll extends BasePresenter<IFgScanAllView> {

    private IFgScanAllView iFgScanAllView;


    private IBaseDao<ChickenInfoRaw> iChickenInfoRawIBaseDao;

    public PresenterFgScanAll(IFgScanAllView iFgScanAllView) {
        this.iFgScanAllView = iFgScanAllView;
        this.iChickenInfoRawIBaseDao = new IChickenInfoRawDaoimpl();
    }


    public void doDBGetChickenRecords(int size, int offset) {
        List<ChickenInfoRaw> list =
                iChickenInfoRawIBaseDao.findAllWithLimiteOffset(ChickenInfoRaw.class, size, offset);

        iFgScanAllView.onDbDataBackSuccessGetChickenRecords(list);
//        if (vertifyNotNull.isNotNullListSize(list)) {
//            iFgScanAllView.onDbDataBackSuccessGetChickenRecords(list);
//        }
    }

    public void doDBGetChickenRecordsInLoadMore(int size, int offset) {


        List<ChickenInfoRaw> list =
                iChickenInfoRawIBaseDao.findAllWithLimiteOffset(ChickenInfoRaw.class, size, offset);
//        List<ChickenInfoRaw> list = DataSupport.limit(size).offset(offset).find(ChickenInfoRaw.class);

        if (vertifyNotNull.isNotNullListSize(list)) {

            iFgScanAllView.onDbDataBackSuccessGetChickenRecordsInLoadMore(list);
        } else {

            iFgScanAllView.onDbDataLoadMoreEnd();
        }

    }


    public void doShowBatchAlertDialog(ChickenInfoRaw chickenInfoRaw) {


        if (vertifyNotNull.isNotNullObj(chickenInfoRaw)) {

            String name = chickenInfoRaw.getName();
            iFgScanAllView.doShowBatchAlertDialog(name);
        }
    }


    public void doDestroyBatcgAlertDialog() {
        iFgScanAllView.doDestroyBatcgAlertDialog();
    }
}
