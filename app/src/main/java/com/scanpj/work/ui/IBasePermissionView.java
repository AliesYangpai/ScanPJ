package com.scanpj.work.ui;

/**
 * Created by admin on 2018/5/4.
 * 类描述  带有权限检测的基类View
 * 版本
 */
public interface IBasePermissionView  extends IBaseView{


    void doPermissionCheck(int requestCode, String... permissions);

    void doShowPermissionDialog();

}