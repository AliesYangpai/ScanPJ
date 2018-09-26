package com.scanpj.work.presenter;


import com.scanpj.work.constant.ConstSp;
import com.scanpj.work.ui.iview.IFirstEnterView;
import com.scanpj.work.universal.cache.sp.SpUtil;

import java.util.List;

/**
 * Created by admin on 2018/5/4.
 * 类描述  首页的presenter
 * 版本
 */
public class PresenterFirstEnter extends BasePresenter<IFirstEnterView> {


    private IFirstEnterView iFirstEnterView;


    public PresenterFirstEnter(IFirstEnterView iFirstEnterView) {
        this.iFirstEnterView = iFirstEnterView;

    }


    /**
     * 检查权限
     */
    public void doPermissionCheck(int requestCode, String... permissions) {

        iFirstEnterView.doPermissionCheck(requestCode, permissions);

    }


    /**
     * 权限提示
     */
    public void doShowPermissionDialog() {

        iFirstEnterView.doShowPermissionDialog();
    }

    public void doGoToLoadOrScanOperate() {


        boolean isLoadData = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_LOAD_OR_NOT, ConstSp.SP_VALUE.DEFAULT_BOOLEAN);


        if (isLoadData) {

            iFirstEnterView.doGoToScanOperate();
        } else {
            iFirstEnterView.doGoToMain();

        }


    }

}