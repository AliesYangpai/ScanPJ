package com.scanpj.work.ui.iview.fg;

import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public interface IFgScanNotView extends IBaseView {



    void onDbDataBackSuccessGetScanNotRecords(List<ChickenInfoScanAbout> list);


    void onDbDataBackSuccessGetScanNotRecordsInLoadMore(List<ChickenInfoScanAbout> list);

    void onDbDataLoadMoreEnd();
}
