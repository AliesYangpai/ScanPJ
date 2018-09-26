package com.scanpj.work.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scanpj.work.R;
import com.scanpj.work.callback.adapter.ScanAnotherItemSelectCallBack;
import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.ConstHz;
import com.scanpj.work.constant.ConstIntent;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.ConstSign;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.entity.BatchInfo;
import com.scanpj.work.presenter.PresenterScanAnother;
import com.scanpj.work.ui.BaseActivity;
import com.scanpj.work.ui.adapter.BatchAdapter;
import com.scanpj.work.ui.adapter.ScanAnotherAdapter;
import com.scanpj.work.ui.iview.IScanAnotherView;
import com.scanpj.work.ui.widget.dialog.BatchInfoDialog;
import com.scanpj.work.ui.widget.dialog.ScanAnotherCommitPartSuccessDialog;
import com.scanpj.work.universal.sound.SoundManage;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScanAnotherActivity extends BaseActivity<IScanAnotherView, PresenterScanAnother> implements
        IScanAnotherView,
        Barcode2DWithSoft.ScanCallback,
        OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        ScanAnotherItemSelectCallBack,
        BaseQuickAdapter.OnItemClickListener {

    private PresenterScanAnother presenterScanAnother;


    /**
     * title
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private CheckBox cb_check_title;
    private ImageView iv_left_seen;
    private ImageView iv_right_scan_operate;


    /**
     * 中间
     */

    private ImageView iv_occupy_position;


    /**
     * 底部
     */
    private TextView tv_focus;//用于动态滑动到底部
    private TextView tv_bottom;
    private TextView tv_del;
    private LinearLayout ll_select_bottom;
    private CheckBox cb_check_bottom;
    private NestedScrollView nsv_list;
    private RecyclerView rv_list;
    private ScanAnotherAdapter scanAnotherAdapter;

    /**
     * dialog相关
     *
     * @param savedInstanceState
     */
    private ProgressDialog progressDialog;


    /**
     * 数据相关
     */
    private Barcode2DWithSoft barcode2DWithSoft = null;
    private String currentScanStatues = ConstLocalData.IS_STOP;

    private List<String> tempDataList = new ArrayList<>();
    private Map<String, String> tempMap = new HashMap<>();


    /**
     * dialog相关
     */

    private ScanAnotherCommitPartSuccessDialog scanAnotherCommitPartSuccessDialog;

    /**
     * 滑动到底部的runnable
     */
    class MyRunnable implements Runnable {


        private WeakReference<Context> weakReference;

        public MyRunnable(Context context) {

            weakReference = new WeakReference<Context>(context);


        }

        @Override
        public void run() {

            if (weakReference.get() == null) {


                return;
            }


            int count = nsv_list.getChildCount();


            int height = 0;

            for (int i = 0; i < count; i++) {

                View view = nsv_list.getChildAt(i);

                int h = view.getMeasuredHeight();

                height += h;
            }

            Log.i("quanbugaodu", height + " 当前线程id：" + Thread.currentThread().getId());


            nsv_list.smoothScrollTo(0, height);


            tv_focus.setFocusable(true);
            tv_focus.setFocusableInTouchMode(true);
            tv_focus.requestFocus();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_another);

//        presenterScanAnother.doInitConfigScan();

        presenterScanAnother.doAddNewLine();
    }


    @Override
    protected void onStop() {
        super.onStop();
        presenterScanAnother.doDealCloseScan(ConstLocalData.IS_CONTINUE_SCAN, barcode2DWithSoft);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenterScanAnother.doInitConfigScan();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 139) {
            if (event.getRepeatCount() == 0) {
                if (null != barcode2DWithSoft && null != presenterScanAnother) {


                    if (null != scanAnotherAdapter) {


                        if (scanAnotherAdapter.isTag()) {
                            CustomToastUtil.showToast(getApplicationContext(), R.string.selecting_not_operate, ConstLocalData.TOAST_DURATION);
                        } else {
                            presenterScanAnother.doDealStartOrStopScan(
                                    currentScanStatues,
                                    ConstLocalData.IS_CONTINUE_SCAN,
                                    barcode2DWithSoft);
                        }
                    }

//
//                    presenterScanAnother.doDealStartOrStopScan(
//                            currentScanStatues,
//                            ConstLocalData.IS_CONTINUE_SCAN,
//                            barcode2DWithSoft);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");


//        presenterScanAnother.doDealCloseScan(ConstLocalData.IS_CONTINUE_SCAN, barcode2DWithSoft);

        presenterScanAnother.doDestroyAlertDialog();

        super.onDestroy();
    }


    @Override
    protected PresenterScanAnother creatPresenter() {
        if (null == presenterScanAnother) {
            presenterScanAnother = new PresenterScanAnother(this);
        }
        return presenterScanAnother;
    }

    @Override
    protected void initViews() {
        /**
         * title
         */

        iv_common_back = findZETScanViewById(R.id.iv_common_back);
        tv_common_title = findZETScanViewById(R.id.tv_common_title);
        tv_common_title.setText(R.string.scan);
        cb_check_title = findZETScanViewById(R.id.cb_check_title);
        cb_check_title.setVisibility(View.GONE);

        nsv_list = findZETScanViewById(R.id.nsv_list);

        iv_left_seen = findZETScanViewById(R.id.iv_left_seen);
        iv_right_scan_operate = findZETScanViewById(R.id.iv_right_scan_operate);


        iv_occupy_position = findZETScanViewById(R.id.iv_occupy_position);

        rv_list = findZETScanViewById(R.id.rv_list);
        scanAnotherAdapter = new ScanAnotherAdapter(R.layout.item_scan_another);
        scanAnotherAdapter.setScanAnotherItemSelectCallBack(this);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(scanAnotherAdapter);


        tv_focus = findZETScanViewById(R.id.tv_focus);
        tv_del = findZETScanViewById(R.id.tv_del);
        tv_bottom = findZETScanViewById(R.id.tv_bottom);
        ll_select_bottom = findZETScanViewById(R.id.ll_select_bottom);
        cb_check_bottom = findZETScanViewById(R.id.cb_check_bottom);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        cb_check_title.setOnCheckedChangeListener(this);
        cb_check_bottom.setOnCheckedChangeListener(this);
        iv_left_seen.setOnClickListener(this);
        iv_right_scan_operate.setOnClickListener(this);
        tv_bottom.setOnClickListener(this);
        tv_del.setOnClickListener(this);
        scanAnotherAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void processIntent() {

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
        CustomToastUtil.showToast(getApplicationContext(), errorMsg, ConstLocalData.TOAST_DURATION);
    }


    @Override
    public void onScanComplete(int i, int length, byte[] bytes) {
        presenterScanAnother.doDealScanResult(ConstLocalData.IS_CONTINUE_SCAN, length, bytes, tempDataList);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.iv_left_seen:

                openActivity(ScanAnotherScanRecordsActivity.class, null);
                break;

            case R.id.iv_right_scan_operate:

                boolean isSelecting = scanAnotherAdapter.isTag();
                if (isSelecting) {
                    CustomToastUtil.showToast(getApplicationContext(), R.string.selecting_not_operate, ConstLocalData.TOAST_DURATION);
                } else {

                    presenterScanAnother.doDealStartOrStopScan(
                            currentScanStatues,
                            ConstLocalData.IS_CONTINUE_SCAN,
                            barcode2DWithSoft);
                }

                break;

            case R.id.tv_bottom:


                presenterScanAnother.doStopContinueScan(barcode2DWithSoft);
                presenterScanAnother.doCommit(HttpConst.URL.GET_SCAN_ADD + HttpConst.ENCRIYTION, scanAnotherAdapter.getData());
                break;


            case R.id.tv_del:


                Map map = scanAnotherAdapter.getMap();
                List<AnotherScanInfo> list = scanAnotherAdapter.getData();
                presenterScanAnother.doDealSelectAndDelet(map, list, tempDataList);
                break;

        }
    }

    @Override
    public void onVertifyErrorScanInitError() {
        ToastUtil.showMsg(getApplicationContext(), ConstError.ERROR_DEVICE_SCAN_INIT_MSG);


    }

    @Override
    public void doInitConfigScan() {


        doShowInitScanDeviceDialog();
        Observable.create(new Observable.OnSubscribe<Barcode2DWithSoft>() {
            @Override
            public void call(Subscriber<? super Barcode2DWithSoft> subscriber) {
                Barcode2DWithSoft b2DWithSoft = Barcode2DWithSoft.getInstance();
                subscriber.onNext(b2DWithSoft);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Barcode2DWithSoft>() {
                    @Override
                    public void onCompleted() {
                        doDestroyInitScanDeviceDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Barcode2DWithSoft b2DWithSoft) {


                        if (null != b2DWithSoft) {

                            boolean result = b2DWithSoft.open(ScanAnotherActivity.this);


                            Log.i("init_diei", "scan_another中" + result);
                            if (result) {
//                                b2DWithSoft.setParameter(324, 1);
//                                b2DWithSoft.setParameter(300, 0); // Snapshot Aiming
//                                b2DWithSoft.setParameter(361, 0); // Image Capture Illumination


                                // interleaved 2 of 5
                                b2DWithSoft.setParameter(6, 1);
                                b2DWithSoft.setParameter(22, 0);
                                b2DWithSoft.setParameter(23, 55);


                                /**
                                 * 这个是设置扫描识别度的参数(764,0-10)
                                 */
                                b2DWithSoft.setParameter(764, 10);
                                b2DWithSoft.setScanCallback(ScanAnotherActivity.this);

                                barcode2DWithSoft = b2DWithSoft;

                            } else {
                                presenterScanAnother.doShowDeviceInitError();
                            }

                        }

                    }
                });


    }

    @Override
    public void doSetScanButtonImgStart() {
        iv_right_scan_operate.setImageResource(R.drawable.img_scan);
    }

    @Override
    public void doSetScanButtonImgStop() {


        iv_right_scan_operate.setImageResource(R.drawable.img_scan_stop);
    }

    @Override
    public void doSetScanStatus(String status) {
        currentScanStatues = status;
    }


    @Override
    public void onVertifyErrorScanCancel() {

        CustomToastUtil.showToast(getApplicationContext(), ConstError.ERROR_DEVICE_SCAN_CANCEL_MSG, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onVertifyErrorScanTimeOut() {

        CustomToastUtil.showToast(getApplicationContext(), ConstError.ERROR_DEVICE_SCAN_TIME_OUT_MSG, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onVertifyErrorScanFail() {

        CustomToastUtil.showToast(getApplicationContext(), ConstError.ERROR_DEVICE_SCAN_UNKNOWN_MSG, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doShowInitScanDeviceDialog() {
        if (!isFinishing()) {
            if (null == progressDialog) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage(ConstHz.INIT_SCAN_DEVICE);
                progressDialog.setCanceledOnTouchOutside(false);
            }
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    @Override
    public void doDestroyInitScanDeviceDialog() {
        if (null != progressDialog) {

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }

    @Override
    public void onVertifyErrorNullFeedRingId() {


        CustomToastUtil.showToast(getApplicationContext(), R.string.please_enter_feet_ring_id, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onVertifyErrorNullCode() {

        CustomToastUtil.showToast(getApplicationContext(), R.string.please_enter_code, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doClearUiData() {

    }

    @Override
    public void onDataBackSuccessForCommit() {


//        presenterScanAnother.doClearAllAndAddNewLine();


        CustomToastUtil.showToast(getApplicationContext(), R.string.commit_success, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onDataBackSuccessForCommitAndRemoveListSize(List<AnotherScanInfo> list) {
        list.remove(list.size() - 1);
    }


    @Override
    public void doVertifyErrorHasRingId() {
        CustomToastUtil.showToast(getApplicationContext(), R.string.has_scanned_this_ring_id, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doVertifyErrorHasCodeId() {
        CustomToastUtil.showToast(getApplicationContext(), R.string.has_scanned_this_code, ConstLocalData.TOAST_DURATION);

    }

    @Override
    public void doSetDataToUiFeetRingId(String str) {


        List<AnotherScanInfo> list = scanAnotherAdapter.getData();
        AnotherScanInfo anotherScanInfo = list.get(list.size() - 1);


        tempMap.clear();

        if (TextUtil.isEmpty(anotherScanInfo.getRingid()) &&
                !TextUtil.isEmpty(anotherScanInfo.getCode())) {
            presenterScanAnother.doAddNewLine();
            tempMap.put(ConstLocalData.RING_ID, str);
            tempMap.put(ConstLocalData.CODE, anotherScanInfo.getCode());
        }


        anotherScanInfo.setRingid(str);
        for (String v : tempMap.values()) {
            tempDataList.add(v);
        }

        scanAnotherAdapter.notifyDataSetChanged();
        presenterScanAnother.doShowTopRightCbView();
        presenterScanAnother.doSetTopRightCbText(ConstHz.CLEAR);
        presenterScanAnother.doScrollToUiBottom();

    }

    @Override
    public void doSetDataToUiCodeId(String str) {

        List<AnotherScanInfo> list = scanAnotherAdapter.getData();
        AnotherScanInfo anotherScanInfo = list.get(list.size() - 1);

        tempMap.clear();
        if (TextUtil.isEmpty(anotherScanInfo.getCode()) &&
                !TextUtil.isEmpty(anotherScanInfo.getRingid())) {

            presenterScanAnother.doAddNewLine();
            tempMap.put(ConstLocalData.RING_ID, str);
            tempMap.put(ConstLocalData.CODE, anotherScanInfo.getRingid());
        }

        anotherScanInfo.setCode(str);
        for (String v : tempMap.values()) {
            tempDataList.add(v);
        }
        scanAnotherAdapter.notifyDataSetChanged();

        presenterScanAnother.doShowTopRightCbView();
        presenterScanAnother.doSetTopRightCbText(ConstHz.CLEAR);
        presenterScanAnother.doScrollToUiBottom();
    }

    @Override
    public void doAddNewLine() {
        scanAnotherAdapter.addData(new AnotherScanInfo());
    }

    @Override
    public void doClearAllAddNewLine() {
        scanAnotherAdapter.setNewData(null);
        presenterScanAnother.doAddNewLine();
    }

    @Override
    public void onVertifyErrorNoScan() {


        CustomToastUtil.showToast(getApplicationContext(), R.string.please_scan, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onVertifyErrorNotCompleteScan() {

        CustomToastUtil.showToast(getApplicationContext(), R.string.please_finish_scan, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doPlaySound() {
        SoundManage.PlaySound(this, SoundManage.SoundType.NORMAL);
    }

    @Override
    public void doShowTopRightCbView() {
        cb_check_title.setVisibility(View.VISIBLE);
    }

    @Override
    public void doHideTopRightCbView() {
        cb_check_title.setVisibility(View.GONE);
    }

    @Override
    public void doSetTopRightCbText(String text) {

        cb_check_title.setText(text);
    }

    @Override
    public void doShowBottomSelectLayout() {
        ll_select_bottom.setVisibility(View.VISIBLE);
    }

    @Override
    public void doHindBottomSelectLayout() {
        ll_select_bottom.setVisibility(View.GONE);
    }

    @Override
    public void doInvisibleImageViewOccupy() {
        iv_occupy_position.setVisibility(View.INVISIBLE);
    }

    @Override
    public void doHideImageViewOccupy() {
        iv_occupy_position.setVisibility(View.GONE);
    }

    @Override
    public void doAdapterShowCheck(final List<AnotherScanInfo> list) {

        scanAnotherAdapter.setTag(true);
        scanAnotherAdapter.doInitMap(list);
        scanAnotherAdapter.setNewData(null);
        scanAnotherAdapter.setNewData(list);


//        Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            public void run() {
//
//
//                scanAnotherAdapter.setNewData(null);
//                scanAnotherAdapter.setTag(true);
//                scanAnotherAdapter.setNewData(list);
////                scanAnotherAdapter.notifyDataSetChanged();
//            }
//        };
//        handler.post(r);
    }

    @Override
    public void doAdapterHideCheck(List<AnotherScanInfo> targetList) {

        scanAnotherAdapter.setNewData(null);
        scanAnotherAdapter.setTag(false);
        scanAnotherAdapter.setNewData(targetList);

//        Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            public void run() {
//                scanAnotherAdapter.setNewData(null);
//                scanAnotherAdapter.setTag(false);
//                scanAnotherAdapter.setNewData(targetList);
//            }
//        };
//        handler.post(r);
    }

    @Override
    public void doSetBottomCheckTextAllSelect() {
        cb_check_bottom.setText(R.string.select_all);
    }

    @Override
    public void doSetBottomCheckTextAllUnSelect() {
        cb_check_bottom.setText(R.string.select_cancel);
    }

    @Override
    public void doSelectAll() {
        Map map = scanAnotherAdapter.getMap();

        for (int i = 0; i < map.size(); i++) {

            map.put(i, true);
        }


        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                scanAnotherAdapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
//        scanAnotherAdapter.notifyDataSetChanged();
    }

    @Override
    public void doUnSelectAll() {
        Map map = scanAnotherAdapter.getMap();
        for (int i = 0; i < map.size(); i++) {
            map.put(i, false);
        }

        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                scanAnotherAdapter.notifyDataSetChanged();
            }
        };
        handler.post(r);

//        scanAnotherAdapter.notifyDataSetChanged();
    }

    @Override
    public void doSetBottomCbChecked() {
        cb_check_bottom.setChecked(true);
    }

    @Override
    public void doSetBottomCbUnChecked() {
        cb_check_bottom.setChecked(false);
    }

    @Override
    public void onVertifyErrorForNoSelect() {

        CustomToastUtil.showToast(getApplicationContext(), R.string.please_select, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doAllAdapterClear() {

        presenterScanAnother.doSetTopRightCbText(ConstHz.CLEAR);
        cb_check_title.toggle();
        presenterScanAnother.doHideTopRightCbView();
        presenterScanAnother.doHideImageViewOccupy();
        presenterScanAnother.doHindBottomSelectLayout();
        scanAnotherAdapter.setTag(false);
        scanAnotherAdapter.doClearAllMap(null);
        scanAnotherAdapter.setNewData(null);
        presenterScanAnother.doAddNewLine();
        presenterScanAnother.doShowDelScanedDataSuccessToast();
    }

    @Override
    public void doSetDataSelectHasSingleLineAfterDel(List<AnotherScanInfo> targetNoSelects) {


        presenterScanAnother.doSetTopRightCbText(ConstHz.CLEAR);
        cb_check_title.toggle();
        presenterScanAnother.doHideImageViewOccupy();
        presenterScanAnother.doHindBottomSelectLayout();
        scanAnotherAdapter.setTag(false);
        scanAnotherAdapter.doClearAllMap(targetNoSelects);
        scanAnotherAdapter.setNewData(targetNoSelects);
        presenterScanAnother.doAddNewLine();
        presenterScanAnother.doShowDelScanedDataSuccessToast();
        presenterScanAnother.doScrollToUiBottom();
    }

    @Override
    public void doSetDataSelectNoSingleLineAfterDel(List<AnotherScanInfo> targets) {
        presenterScanAnother.doSetTopRightCbText(ConstHz.CLEAR);
        cb_check_title.toggle();
        presenterScanAnother.doHideImageViewOccupy();
        presenterScanAnother.doHindBottomSelectLayout();
        scanAnotherAdapter.setTag(false);
        scanAnotherAdapter.doClearAllMap(targets);
        scanAnotherAdapter.setNewData(targets);
        presenterScanAnother.doAddNewLine();
        presenterScanAnother.doShowDelScanedDataSuccessToast();
        presenterScanAnother.doScrollToUiBottom();

    }

    @Override
    public void doSetDataSelectNoSingleLineAndStillHasSingleLineAfterDel(List<AnotherScanInfo> targets) {
        presenterScanAnother.doSetTopRightCbText(ConstHz.CLEAR);
        cb_check_title.toggle();
        presenterScanAnother.doHideImageViewOccupy();
        presenterScanAnother.doHindBottomSelectLayout();
        scanAnotherAdapter.setTag(false);
        scanAnotherAdapter.doClearAllMap(targets);
        scanAnotherAdapter.setNewData(targets);
        presenterScanAnother.doShowDelScanedDataSuccessToast();
        presenterScanAnother.doScrollToUiBottom();
    }

    @Override
    public void doDelScanedDataSuccessToast() {
        CustomToastUtil.showToast(getApplicationContext(), R.string.del_success, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doScrollToUiBottom() {
        nsv_list.post(new MyRunnable(this));
    }



    @Override
    public void doShowAlertDialog(String name) {
        if (scanAnotherCommitPartSuccessDialog == null) {
            scanAnotherCommitPartSuccessDialog = new ScanAnotherCommitPartSuccessDialog(this);
        }
        scanAnotherCommitPartSuccessDialog.show();
        scanAnotherCommitPartSuccessDialog.doInitData(name);
    }

    @Override
    public void doDestroyAlertDialog() {
        if (null != scanAnotherCommitPartSuccessDialog && scanAnotherCommitPartSuccessDialog.isShowing()) {
            scanAnotherCommitPartSuccessDialog.dismiss();
            scanAnotherCommitPartSuccessDialog = null;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        int cbId = buttonView.getId();

        if (cbId == R.id.cb_check_title) {


            if (isChecked) {

                presenterScanAnother.doSetTopRightCbText(ConstHz.CANCEL);
                presenterScanAnother.doShowBottomSelectLayout();
                presenterScanAnother.doInvisibleImageViewOccupy();
                presenterScanAnother.doAdapterShowCheck(scanAnotherAdapter.getData());


                presenterScanAnother.doStopScanWhenCheckTrue(
                        currentScanStatues,
                        ConstLocalData.IS_CONTINUE_SCAN,
                        barcode2DWithSoft);

            } else {
                presenterScanAnother.doSetTopRightCbText(ConstHz.CLEAR);
                presenterScanAnother.doHideImageViewOccupy();

                presenterScanAnother.doAdapterHideCheck(scanAnotherAdapter.getData());
                presenterScanAnother.doHindBottomSelectLayout();
            }
        }


        if (cbId == R.id.cb_check_bottom) {


            if (isChecked) {
                presenterScanAnother.doSetBottomCheckTextAllUnSelect();
                presenterScanAnother.doSelectAll();

            } else {

                presenterScanAnother.doSetBottomCheckTextAllSelect();
                presenterScanAnother.doUnSelectAll();
            }

        }


    }

    @Override
    public void onItemDoSelect(Map map, int position) {


        map.put(position, true);
        List<AnotherScanInfo> list = scanAnotherAdapter.getData();
        presenterScanAnother.doDealUpdateBottomSelectUi(map, list);
    }

    @Override
    public void onItemDoUnSelect(Map map, int position) {
        map.put(position, false);
        List<AnotherScanInfo> list = scanAnotherAdapter.getData();
        presenterScanAnother.doDealUpdateBottomUnSelectUi(map, list);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        boolean isSelectTag = scanAnotherAdapter.isTag();

        if (isSelectTag) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_check);
            checkBox.toggle();
        }
    }
}
