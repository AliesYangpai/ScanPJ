package com.scanpj.work.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.entity.ErrorEntity;
import com.scanpj.work.listener.OnDataBackListener;
import com.scanpj.work.method.IChickenHouse;
import com.scanpj.work.method.impl.IChickenHouseImpl;
import com.scanpj.work.ui.iview.IMainView;
import com.scanpj.work.universal.parse.HttpResult;
import com.scanpj.work.universal.parse.ParseSerilizable;
import com.scanpj.work.universal.verification.VertifyNotNull;

import java.util.List;


/**
 * Created by admin on 2018/5/9.
 * 类描述  主页的presenter
 * 版本
 */
public class PresenterMain extends BasePresenter<IMainView> {

    private IMainView iMainView;


    private IChickenHouse iChickenHouse;

    public PresenterMain(IMainView iMainView) {
        this.iMainView = iMainView;
        this.iChickenHouse = new IChickenHouseImpl();
    }


    public void doGetChickenHouseList(String url) {


        iChickenHouse.doGetChickenHouseRecords(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iMainView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {



                if(null== parseSerilizable) {

                    parseSerilizable = new ParseSerilizable();
                }

                if(null == vertifyNotNull) {

                    vertifyNotNull = new VertifyNotNull();
                }

                HttpResult<List<ChickenHouseInfo>> parseToObj = parseSerilizable.getParseDataListZTE(data, ChickenHouseInfo.class);


                if (vertifyNotNull.isNotNullObj(parseToObj)) {
                    if(parseToObj.code == HttpConst.CODE_SUCCESS) {
                        List<ChickenHouseInfo> list = parseToObj.data;
                        if(vertifyNotNull.isNotNullListSize(list)) {
                            iMainView.onDataBackSuccessForGetChickenHouseRecords(list);
                        }
                    }else {

                        iMainView.onDataBackFail(parseToObj.code, parseToObj.msg);
                    }
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iMainView.dismissLoadingDialog();
            }
        });

    }


    public void doGetChickenHouseListInFresh(String url) {
        iChickenHouse.doGetChickenHouseRecords(url, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {


                HttpResult<List<ChickenHouseInfo>> parseToObj = parseSerilizable.getParseDataListZTE(data, ChickenHouseInfo.class);


                if (vertifyNotNull.isNotNullObj(parseToObj)) {
                    if(parseToObj.code == HttpConst.CODE_SUCCESS) {
                        List<ChickenHouseInfo> list = parseToObj.data;
                        if(vertifyNotNull.isNotNullListSize(list)) {
                            iMainView.onDataBackSuccessForGetChickenHouseRecords(list);
                        }
                    }else {

                        iMainView.onDataBackFail(parseToObj.code, parseToObj.msg);
                    }
                }

//                List<ChickenHouseInfo> list = parseSerilizable.getParseToNoItemsList(data, ChickenHouseInfo.class);
//
//                if (vertifyNotNull.isNotNullListSize(list)) {
//                    iMainView.onDataBackSuccessForGetChickenHouseRecords(list);
//                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iMainView.dismissFreshLoading();
            }
        });
    }

}