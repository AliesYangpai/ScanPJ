package com.scanpj.work.presenter;

import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.entity.ErrorEntity;
import com.scanpj.work.entity.PakegeScanInfo;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.method.IScanAnother;
import com.scanpj.work.method.impl.IScanAnotherImpl;
import com.scanpj.work.ui.iview.IScanAnotherScanRecordsView;
import com.scanpj.work.universal.parse.HttpResult;

import java.util.List;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class PresenterScanAnotherScanRecords extends BasePresenter<IScanAnotherScanRecordsView> {

    private IScanAnotherScanRecordsView iScanAnotherScanRecordsView;
    private IScanAnother iScanAnother;

    public PresenterScanAnotherScanRecords(IScanAnotherScanRecordsView iScanAnotherScanRecordsView) {
        this.iScanAnotherScanRecordsView = iScanAnotherScanRecordsView;
        this.iScanAnother = new IScanAnotherImpl();
    }

    public void doGetAnotherScanRecords(String url) {

        iScanAnother.doGetAnotherScanRecords(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iScanAnotherScanRecordsView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                HttpResult<PakegeScanInfo> parseToObj = parseSerilizable.getParseDataListZTE(data, PakegeScanInfo.class);


                if (vertifyNotNull.isNotNullObj(parseToObj)) {
                    if (parseToObj.code == HttpConst.CODE_SUCCESS) {
                        PakegeScanInfo pakegeScanInfo = parseToObj.data;
                        if (vertifyNotNull.isNotNullObj(pakegeScanInfo)) {

                            List<AnotherScanInfo> rows = pakegeScanInfo.getRows();
                            if (vertifyNotNull.isNotNullListSize(rows)) {
                                iScanAnotherScanRecordsView.onDataBackSuccessForGetScanRecords(rows);
                            }

                        }
                    } else {

                        iScanAnotherScanRecordsView.onDataBackFail(parseToObj.code, parseToObj.msg);
                    }
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iScanAnotherScanRecordsView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iScanAnotherScanRecordsView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iScanAnotherScanRecordsView.dismissLoadingDialog();
            }
        });

    }


}
