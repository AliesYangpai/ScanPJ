package com.scanpj.work.presenter.fg;

import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.presenter.BasePresenter;
import com.scanpj.work.ui.iview.fg.IFgScanNotView;
import com.scanpj.work.universal.cache.db.dao.IBaseDao;
import com.scanpj.work.universal.cache.db.dao.impl.IChickenInfoScanAboutimpl;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述  未扫描的data数据fg
 * 版本
 */

public class PresenterFgScanNot extends BasePresenter<IFgScanNotView> {

    private IFgScanNotView iFgScanNotView;
    private IBaseDao<ChickenInfoScanAbout> iChickenInfoScanAboutIBaseDao;

    public PresenterFgScanNot(IFgScanNotView iFgScanNotView) {
        this.iFgScanNotView = iFgScanNotView;
        this.iChickenInfoScanAboutIBaseDao = new IChickenInfoScanAboutimpl();
    }


    public void doDBGetScanNotRecords(int size, int offset,String... condition) {
        List<ChickenInfoScanAbout> list =
                iChickenInfoScanAboutIBaseDao.findAllWithLimiteOffsetByCondition(ChickenInfoScanAbout.class, size, offset,condition);

        iFgScanNotView.onDbDataBackSuccessGetScanNotRecords(list);
//        if (vertifyNotNull.isNotNullListSize(list)) {
//            iFgScanNotView.onDbDataBackSuccessGetScanNotRecords(list);
//        }
    }

    public void doDBGetScanNotRecordsInLoadMore(int size, int offset,String... condition) {


        List<ChickenInfoScanAbout> list =
                iChickenInfoScanAboutIBaseDao.findAllWithLimiteOffsetByCondition(ChickenInfoScanAbout.class, size, offset,condition);
//        List<ChickenInfoRaw> list = DataSupport.limit(size).offset(offset).find(ChickenInfoRaw.class);

        if (vertifyNotNull.isNotNullListSize(list)) {

            iFgScanNotView.onDbDataBackSuccessGetScanNotRecordsInLoadMore(list);
        } else {

            iFgScanNotView.onDbDataLoadMoreEnd();
        }

    }

}
