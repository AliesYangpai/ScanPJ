package com.scanpj.work.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.scanpj.work.R;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.ConstDbLocal;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.entity.temp.EventEntity;
import com.scanpj.work.presenter.fg.PresenterFgScanNot;
import com.scanpj.work.ui.BaseFragment;
import com.scanpj.work.ui.adapter.ScanNotAdapter;
import com.scanpj.work.ui.iview.fg.IFgScanNotView;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;

import java.util.List;

/**
 *未扫描的data数据fg
 */
public class FragmentScanNot extends BaseFragment<IFgScanNotView,PresenterFgScanNot> implements
        IFgScanNotView,
        RequestLoadMoreListener{



    public static final String TAG = FragmentScanNot.class.getSimpleName();
    private PresenterFgScanNot presenterFgScanNot;



    private RecyclerView rv_list;
    private ScanNotAdapter scanNotAdapter;
    private RecyclerView.LayoutManager layoutManager;


    /**
     * 数据相关
     */
    private int currentSize = ConstDbLocal.INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = 0;//用于上拉加载更多；

    public FragmentScanNot() {
        // Required empty public constructor
    }



    @Override
    protected PresenterFgScanNot creatPresenter() {
        if(null == presenterFgScanNot) {
            presenterFgScanNot = new PresenterFgScanNot(this);
        }
        return presenterFgScanNot;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_scan_not;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {
        rv_list = findZETScanViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(mActivity);
        scanNotAdapter = new ScanNotAdapter(R.layout.item_scan_result);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(scanNotAdapter);
    }

    @Override
    protected void initListener() {
        scanNotAdapter.setOnLoadMoreListener(this, rv_list);
        scanNotAdapter.disableLoadMoreIfNotFullPage(rv_list);
    }

    @Override
    protected void onLazyLoad() {
        presenterFgScanNot.doDBGetScanNotRecords(currentSize,currentIndex, ConstDbLocal.ScanAbout.FLAG,"0");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(!hidden) {

            presenterFgScanNot.doDBGetScanNotRecords(currentSize,currentIndex, ConstDbLocal.ScanAbout.FLAG,"1");
        }
    }


    @Override
    protected void onEventBack(EventEntity eventEntity) {

    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

        CustomToastUtil.showToast(mActivity.getApplicationContext(),errorMsg, ConstLocalData.TOAST_DURATION);
    }


    @Override
    public void onLoadMoreRequested() {
        int tempIndex = currentIndex + ConstDbLocal.INCREMENT_OFFSET;

        presenterFgScanNot.doDBGetScanNotRecordsInLoadMore(currentSize,tempIndex, ConstDbLocal.ScanAbout.FLAG,"0");
    }

    @Override
    public void onDbDataBackSuccessGetScanNotRecords(List<ChickenInfoScanAbout> list) {
        scanNotAdapter.setNewData(list);
    }

    @Override
    public void onDbDataBackSuccessGetScanNotRecordsInLoadMore(List<ChickenInfoScanAbout> list) {
        scanNotAdapter.addData(list);
        scanNotAdapter.loadMoreComplete();
        currentIndex += ConstDbLocal.INCREMENT_OFFSET;
    }

    @Override
    public void onDbDataLoadMoreEnd() {
        scanNotAdapter.loadMoreEnd();
    }


}
