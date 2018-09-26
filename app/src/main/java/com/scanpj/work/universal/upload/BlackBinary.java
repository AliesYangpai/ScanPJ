package com.scanpj.work.universal.upload;

import com.scanpj.work.constant.HttpConst;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.OnUploadListener;

import java.io.File;

/**
 * Created by admin on 2018/5/7.
 * 类描述  文件上传的 binary
 * 版本
 */
public class BlackBinary {


    public BlackBinary() {


    }


    /**
     * 获取上传单个文件的Binary
     *
     * @param filePath
     * @param onUploadListener
     * @return
     */
    public FileBinary getSingleFileUploloadBinary(String filePath, OnUploadListener onUploadListener) {


        File file = new File(filePath);
        FileBinary fileBinary = new FileBinary(file);

        fileBinary.setUploadListener(HttpConst.HTTP_WHAT, onUploadListener);

        return fileBinary;

    }


//    /**
//     * 获取多个文件的
//     * @param filePaths
//     * @param onUploadListener
//     * @return
//     */
//    public List<Binary> getMultiFileUploadBinary(List<String> filePaths, OnUploadListener onUploadListener) {
//
//
//        List<Binary> binaries = new ArrayList<>();
//
//        for (String filePath : filePaths) {
//
//            File file = new File(filePath);
//            FileBinary fileBinary = new FileBinary(file);
//            fileBinary.setUploadListener(HttpConst.HTTP_WHAT, onUploadListener);
//            binaries.add(fileBinary);
//        }
//
//        return binaries;
//
//
//    }


//    /**
//     * 获取上传单个文件的Binary
//     * @param filePath
//     * @param iBaseUploadPermissionView
//     * @return
//     */
//    public FileBinary getSingleFileUploloadBinary(String filePath, final IBaseUploadPermissionView iBaseUploadPermissionView)  {
//
//
//        File file = new File(filePath);
//        FileBinary fileBinary = new FileBinary(file);
//
//        fileBinary.setUploadListener(HttpConst.HTTP_WHAT, new OnUploadListener() {
//            @Override
//            public void onStart(int what) {
//
//                iBaseUploadPermissionView.onUploadStart(what);
//
//            }
//
//            @Override
//            public void onCancel(int what) {
//
//
//                iBaseUploadPermissionView.onUploadCancel(what);
//            }
//
//            @Override
//            public void onProgress(int what, int progress) {
//
//
//                iBaseUploadPermissionView.onUploadProgress(what,progress);
//            }
//
//            @Override
//            public void onFinish(int what) {
//
//
//                iBaseUploadPermissionView.onUploadFinish(what);
//            }
//
//            @Override
//            public void onError(int what, Exception exception) {
//
//
//                iBaseUploadPermissionView.onUploadError(what,exception);
//            }
//        });
//
//        return fileBinary;
//
//    }
}