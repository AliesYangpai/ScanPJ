package com.scanpj.work.ui;

/**
 * Created by admin on 2018/5/4.
 * 类描述  带有权限验证回调 和 上传回调的view
 * 版本
 */
public interface IBaseUploadPermissionView extends IBasePermissionView {

    void onUploadStart(int what);

    void onUploadCancel(int what);

    void onUploadProgress(int what, int progress);

    void onUploadFinish(int what);

    void onUploadError(int what, Exception exception);

    void onDataBackSuccessForUpload(String path);

}