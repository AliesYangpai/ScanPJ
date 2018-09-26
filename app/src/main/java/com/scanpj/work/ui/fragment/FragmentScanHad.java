package com.scanpj.work.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scanpj.work.R;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.ConstDbLocal;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.entity.temp.EventEntity;
import com.scanpj.work.presenter.fg.PresenterFgScanHad;
import com.scanpj.work.ui.BaseFragment;
import com.scanpj.work.ui.adapter.ScanHadAdapter;
import com.scanpj.work.ui.iview.fg.IFgScanHadView;
import com.scanpj.work.ui.widget.dialog.BatchInfoDialog;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;

import java.util.List;

/**
 * 已经扫描的data数据fg
 */
public class FragmentScanHad extends BaseFragment<IFgScanHadView, PresenterFgScanHad> implements
        IFgScanHadView,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemChildClickListener{


    public static final String TAG = FragmentScanHad.class.getSimpleName();

    private PresenterFgScanHad presenterFgScanHad;


    private RecyclerView rv_list;
    private ScanHadAdapter scanHadAdapter;
    private RecyclerView.LayoutManager layoutManager;


    /**
     * 数据相关
     */
    private int currentSize = ConstDbLocal.INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = 0;//用于上拉加载更多；

    /**
     * dialog相关
     */
    private BatchInfoDialog batchInfoDialog;

    @Override
    protected PresenterFgScanHad creatPresenter() {
        if (null == presenterFgScanHad) {

            presenterFgScanHad = new PresenterFgScanHad(this);
        }
        return presenterFgScanHad;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_scan_had;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {
        rv_list = findZETScanViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(mActivity);
        scanHadAdapter = new ScanHadAdapter(R.layout.item_scan_result_with_state);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(scanHadAdapter);
    }

    @Override
    protected void initListener() {
        scanHadAdapter.setOnLoadMoreListener(this, rv_list);
        scanHadAdapter.disableLoadMoreIfNotFullPage(rv_list);
        scanHadAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void onLazyLoad() {
        presenterFgScanHad.doDBGetScanHadRecords(currentSize, currentIndex, ConstDbLocal.ScanAbout.IS_SCAND, "1");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {

            presenterFgScanHad.doDBGetScanHadRecords(currentSize, currentIndex, ConstDbLocal.ScanAbout.IS_SCAND, "1");
        }else {


            if(0 != currentIndex) {

                currentSize = currentIndex;
                currentIndex = 0;
            }

            presenterFgScanHad.doDestroyBatcgAlertDialog();
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

        CustomToastUtil.showToast(mActivity.getApplicationContext(), errorMsg, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onLoadMoreRequested() {
        int tempIndex = currentIndex + ConstDbLocal.INCREMENT_OFFSET;


        presenterFgScanHad.doDBGetScanHadRecordsInLoadMore(ConstDbLocal.INCREMENT_SIZE, tempIndex, ConstDbLocal.ScanAbout.IS_SCAND, "1");
//        presenterFgScanHad.doDBGetScanHadRecordsInLoadMore(currentSize,tempIndex, ConstDbLocal.ScanAbout.IS_SCAND,"1");
    }


    @Override
    public void onDbDataBackSuccessGetScanHadRecords(List<ChickenInfoScanAbout> list) {
        scanHadAdapter.setNewData(list);
    }

    @Override
    public void onDbDataBackSuccessGetScanHadRecordsInLoadMore(List<ChickenInfoScanAbout> list) {
        scanHadAdapter.addData(list);
        scanHadAdapter.loadMoreComplete();
        currentIndex += ConstDbLocal.INCREMENT_OFFSET;
    }

    @Override
    public void onDbDataLoadMoreEnd() {
        scanHadAdapter.loadMoreEnd();
    }

    @Override
    public void doShowBatchAlertDialog(String name) {

        if (batchInfoDialog == null) {
            batchInfoDialog = new BatchInfoDialog(mActivity);
        }
        batchInfoDialog.show();
        batchInfoDialog.doInitData(name);
    }

    @Override
    public void doDestroyBatcgAlertDialog() {

        if (null != batchInfoDialog && batchInfoDialog.isShowing()) {
            batchInfoDialog.dismiss();
            batchInfoDialog = null;
        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ChickenInfoScanAbout chickenInfoScanAbout = (ChickenInfoScanAbout) adapter.getData().get(position);
        presenterFgScanHad.doShowBatchAlertDialog(chickenInfoScanAbout);
    }
}
