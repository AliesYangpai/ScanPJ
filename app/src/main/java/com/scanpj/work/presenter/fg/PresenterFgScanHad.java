package com.scanpj.work.presenter.fg;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.presenter.BasePresenter;
import com.scanpj.work.ui.iview.fg.IFgScanAllView;
import com.scanpj.work.ui.iview.fg.IFgScanHadView;
import com.scanpj.work.universal.cache.db.dao.IBaseDao;
import com.scanpj.work.universal.cache.db.dao.impl.IChickenInfoRawDaoimpl;
import com.scanpj.work.universal.cache.db.dao.impl.IChickenInfoScanAboutimpl;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述  已经扫描的data数据fg
 * 版本
 */

public class PresenterFgScanHad extends BasePresenter<IFgScanHadView> {

    private IFgScanHadView iFgScanHadView;
    private IBaseDao<ChickenInfoScanAbout> iChickenInfoScanAboutIBaseDao;

    public PresenterFgScanHad(IFgScanHadView iFgScanHadView) {
        this.iFgScanHadView = iFgScanHadView;
        this.iChickenInfoScanAboutIBaseDao = new IChickenInfoScanAboutimpl();
    }




    public void doDBGetScanHadRecords(int size, int offset,String... condition) {
        List<ChickenInfoScanAbout> list =
                iChickenInfoScanAboutIBaseDao.findAllWithLimiteOffsetByCondition(ChickenInfoScanAbout.class, size, offset,condition);
        iFgScanHadView.onDbDataBackSuccessGetScanHadRecords(list);

//        if (vertifyNotNull.isNotNullListSize(list)) {
//            iFgScanHadView.onDbDataBackSuccessGetScanHadRecords(list);
//        }
    }

    public void doDBGetScanHadRecordsInLoadMore(int size, int offset,String... condition) {


        List<ChickenInfoScanAbout> list =
                iChickenInfoScanAboutIBaseDao.findAllWithLimiteOffsetByCondition(ChickenInfoScanAbout.class, size, offset,condition);
//        List<ChickenInfoRaw> list = DataSupport.limit(size).offset(offset).find(ChickenInfoRaw.class);

        if (vertifyNotNull.isNotNullListSize(list)) {

            iFgScanHadView.onDbDataBackSuccessGetScanHadRecordsInLoadMore(list);
        } else {

            iFgScanHadView.onDbDataLoadMoreEnd();
        }

    }




    public void doShowBatchAlertDialog(ChickenInfoScanAbout chickenInfoScanAbout) {


        if (vertifyNotNull.isNotNullObj(chickenInfoScanAbout)) {

            String name = chickenInfoScanAbout.getName();
            iFgScanHadView.doShowBatchAlertDialog(name);
        }
    }


    public void doDestroyBatcgAlertDialog() {
        iFgScanHadView.doDestroyBatcgAlertDialog();
    }





}
