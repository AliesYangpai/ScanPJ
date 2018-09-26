package com.scanpj.work.ui.iview;


import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.ui.IBaseView;

import java.util.List;

/**
 * Created by admin on 2018/5/9.
 * 类描述  主页的mainView
 * 版本
 */
public interface IMainView extends IBaseView {


    /**
     * 这里暂时没有任何操作
     */


    void dismissFreshLoading();

    void onDataBackSuccessForGetChickenHouseRecords(List<ChickenHouseInfo> list);

}