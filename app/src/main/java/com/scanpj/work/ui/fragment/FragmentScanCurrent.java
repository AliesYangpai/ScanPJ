package com.scanpj.work.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scanpj.work.R;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.temp.EventEntity;
import com.scanpj.work.presenter.fg.PresenterFgScanCurrent;
import com.scanpj.work.ui.BaseFragment;
import com.scanpj.work.ui.adapter.ScanCurrentAdapter;
import com.scanpj.work.ui.iview.fg.IFgScanCurrentView;
import com.scanpj.work.ui.widget.dialog.BatchInfoDialog;
import com.scanpj.work.util.CustomToastUtil;


/**
 * 当前扫描结果的fg
 */
public class FragmentScanCurrent extends BaseFragment<IFgScanCurrentView, PresenterFgScanCurrent> implements
        IFgScanCurrentView,
        BaseQuickAdapter.OnItemChildClickListener {

    public static final String TAG = FragmentScanCurrent.class.getSimpleName();

    private PresenterFgScanCurrent presenterFgScanCurrent;


    private RecyclerView rv_list;
    private ScanCurrentAdapter scanCurrentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ChickenInfoRaw currentChickenInfoRaw;


    /**
     * dialog相关
     */
    private BatchInfoDialog batchInfoDialog;




    @Override
    protected PresenterFgScanCurrent creatPresenter() {
        if (null == presenterFgScanCurrent) {

            presenterFgScanCurrent = new PresenterFgScanCurrent(this);
        }
        return presenterFgScanCurrent;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_scan_current;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {
        rv_list = findZETScanViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(mActivity);
        scanCurrentAdapter = new ScanCurrentAdapter(R.layout.item_scan_result_with_state);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(scanCurrentAdapter);
    }

    @Override
    protected void initListener() {
        scanCurrentAdapter.setOnItemChildClickListener(this);


    }



    @Override
    protected void onLazyLoad() {



        presenterFgScanCurrent.doSetScanDataToUi(currentChickenInfoRaw);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {

            presenterFgScanCurrent.doDestroyBatcgAlertDialog();
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
    public void doSetScanDataToUi(ChickenInfoRaw chickenInfoRaw) {


        scanCurrentAdapter.addData( 0,chickenInfoRaw);
        rv_list.scrollToPosition(0);

    }

    @Override
    public void doSetClearData() {
        scanCurrentAdapter.setNewData(null);
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
        ChickenInfoRaw chickenInfoRaw = (ChickenInfoRaw) adapter.getData().get(position);
        presenterFgScanCurrent.doShowBatchAlertDialog(chickenInfoRaw);
    }
}
