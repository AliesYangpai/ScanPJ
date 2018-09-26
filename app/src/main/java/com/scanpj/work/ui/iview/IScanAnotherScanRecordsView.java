package com.scanpj.work.ui.iview;

import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public interface IScanAnotherScanRecordsView extends IBaseView {






    void onDataBackSuccessForGetScanRecords(List<AnotherScanInfo> list);




}
