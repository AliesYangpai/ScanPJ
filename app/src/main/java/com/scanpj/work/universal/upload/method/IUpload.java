package com.scanpj.work.universal.upload.method;

import com.scanpj.work.listener.OnDataBackListener;
import com.yanzhenjie.nohttp.FileBinary;

/**
 * Created by admin on 2018/5/7.
 * 类描述  文件上传接口 (单文件上传，多文件上传)
 * 版本
 */
public interface IUpload {


    /**
     * 单文件上传
     */


    void doSingleUpLoad(String url,
                        FileBinary fileBinary,
                        OnDataBackListener onDataBackListener);

//    /**
//     * 多文件上传
//     */
//    void doMultiUpload(String url,
//                       List<Binary> binaries,
//                       OnDataBackListener onDataBackListener);
}