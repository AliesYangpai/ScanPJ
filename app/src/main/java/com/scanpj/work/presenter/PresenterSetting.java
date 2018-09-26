package com.scanpj.work.presenter;

import com.scanpj.work.ui.iview.ISettingView;

/**
 * Created by Alie on 2018/6/15.
 * 类描述
 * 版本
 */

public class PresenterSetting extends BasePresenter<ISettingView> {
    private ISettingView iSettingView;

    public PresenterSetting(ISettingView iSettingView) {
        this.iSettingView = iSettingView;
    }


    /**
     * 检查权限
     */
    public void doPermissionCheck(int requestCode, String... permissions) {

        iSettingView.doPermissionCheck(requestCode, permissions);

    }


    /**
     * 权限提示
     */
    public void doShowPermissionDialog() {

        iSettingView.doShowPermissionDialog();
    }

}
