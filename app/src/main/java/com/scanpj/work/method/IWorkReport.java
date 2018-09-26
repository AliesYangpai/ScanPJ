package com.scanpj.work.method;


import com.scanpj.work.listener.OnDataBackListener;

/**
 * Created by admin on 2018/5/4.
 * 类描述  工作汇报
 * 版本
 */
public interface IWorkReport {


    /**
     * 创建工作汇报（日报、周报、月报）
     * @param url
     * @param onDataBackListener
     */
    void doCreatWorkReport(String url, OnDataBackListener onDataBackListener);


    /**
     * 获取工作汇报详情
     * @param url
     * @param onDataBackListener
     */
    void doGetWorkReportInfo(String url, OnDataBackListener onDataBackListener);



    /**
     * 获取工作汇报列表记录
     * @param url
     * @param tag
     * @param size
     * @param index
     * @param onDataBackListener
     */
    void doGetWorkReportRecords(String url, int tag, int size, int index, OnDataBackListener onDataBackListener);

}