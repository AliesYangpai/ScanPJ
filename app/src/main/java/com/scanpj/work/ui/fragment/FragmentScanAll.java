package com.scanpj.work.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scanpj.work.R;
import com.scanpj.work.constant.ConstDbLocal;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.temp.EventEntity;
import com.scanpj.work.presenter.fg.PresenterFgScanAll;
import com.scanpj.work.test.TestContent;
import com.scanpj.work.ui.BaseFragment;
import com.scanpj.work.ui.adapter.ScanAllAdapter;
import com.scanpj.work.ui.iview.fg.IFgScanAllView;
import com.scanpj.work.ui.widget.dialog.BatchInfoDialog;
import com.scanpj.work.ui.widget.dialog.ReloadAlertDialog;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 全部数据的界面
 */
public class FragmentScanAll extends BaseFragment<IFgScanAllView, PresenterFgScanAll> implements
        IFgScanAllView,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemChildClickListener {

    public static final String TAG = FragmentScanAll.class.getSimpleName();
    private PresenterFgScanAll presenterFgScanAll;


    private RecyclerView rv_list;
    private ScanAllAdapter scanAllAdapter;
    private RecyclerView.LayoutManager layoutManager;


    /**
     * 数据相关
     */
    private int currentSize = ConstDbLocal.INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = 0;//用于上拉加载更多；       默认1       //加载刷新



    /**
     * dialog相关
     */
    private BatchInfoDialog batchInfoDialog;

    @Override
    protected PresenterFgScanAll creatPresenter() {
        if (null == presenterFgScanAll) {
            presenterFgScanAll = new PresenterFgScanAll(this);
        }
        return presenterFgScanAll;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_scan_all;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {
        rv_list = findZETScanViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(mActivity);
        scanAllAdapter = new ScanAllAdapter(R.layout.item_scan_result);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(scanAllAdapter);
    }

    @Override
    protected void initListener() {
        scanAllAdapter.setOnLoadMoreListener(this, rv_list);
        scanAllAdapter.disableLoadMoreIfNotFullPage(rv_list);
        scanAllAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void onLazyLoad() {


//        List<ChickenInfoRaw> chickenRawlist = TestContent.getChickenRawlist();
//        DataSupport.saveAll(chickenRawlist);

        presenterFgScanAll.doDBGetChickenRecords(currentSize, currentIndex);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            presenterFgScanAll.doDBGetChickenRecords(currentSize, currentIndex);


        } else {




            if (0 != currentIndex) {

                currentSize = currentIndex;
                currentIndex = 0;
            }

            presenterFgScanAll.doDestroyBatcgAlertDialog();
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


        presenterFgScanAll.doDBGetChickenRecordsInLoadMore(ConstDbLocal.INCREMENT_SIZE, tempIndex);
//        presenterFgScanAll.doDBGetChickenRecordsInLoadMore(currentSize,tempIndex);

    }

    @Override
    public void onDbDataBackSuccessGetChickenRecords(List<ChickenInfoRaw> list) {
        scanAllAdapter.setNewData(list);
    }

    @Override
    public void onDbDataBackSuccessGetChickenRecordsInLoadMore(List<ChickenInfoRaw> list) {

        scanAllAdapter.addData(list);
        scanAllAdapter.loadMoreComplete();
        currentIndex += ConstDbLocal.INCREMENT_OFFSET;
    }

    @Override
    public void onDbDataLoadMoreEnd() {
        scanAllAdapter.loadMoreEnd();
    }

    @Override
    public void doShowBatchAlertDialog(String name) {

        if (batchInfoDialog == null) {
            batchInfoDialog = new BatchInfoDialog(mActivity);
        }
        batchInfoDialog.show();
        batchInfoDialog.doInitData(name);
        Log.i("batch_name_indialog",name);
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
        ChickenInfoRaw chickenInfoRaw = (ChickenInfoRaw) adapter.getData().get(position);
        presenterFgScanAll.doShowBatchAlertDialog(chickenInfoRaw);

    }
}
