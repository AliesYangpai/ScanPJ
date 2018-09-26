
package com.scanpj.work.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.scanpj.work.R;
import com.scanpj.work.constant.ConstDbLocal;
import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.ConstEvent;
import com.scanpj.work.constant.ConstHz;
import com.scanpj.work.constant.ConstIntent;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.ConstLog;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.entity.temp.EventEntity;
import com.scanpj.work.presenter.PresenterScanOperate;
import com.scanpj.work.ui.BaseActivity;
import com.scanpj.work.ui.adapter.ScanCurrentAdapter;
import com.scanpj.work.ui.fragment.FragmentScanCurrent;
import com.scanpj.work.ui.iview.IScanOperateView;
import com.scanpj.work.ui.widget.AdhDialog;
import com.scanpj.work.universal.sound.SoundManage;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zebra.adc.decoder.Barcode2DWithSoft;
import com.zebra.adc.decoder.Barcode2DWithSoft.ScanCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import crossoverone.statuslib.StatusUtil;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScanOperateActivity extends BaseActivity<IScanOperateView, PresenterScanOperate> implements
        IScanOperateView,
        TabLayout.OnTabSelectedListener,
        PermissionListener,
        OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        ScanCallback {


    private PresenterScanOperate presenterScanOperate;


    /**
     * title
     */

    private TextView tv_common_title;
    private TextView tv_right;

    /**
     * 中间
     */
    private RadioGroup rg_scan_type;
    private RadioButton rb_scan_single;
    private RadioButton rb_scan_continue;
    private CheckBox cb_scan_operate;

    private TabLayout tl_scan_type;


    /**
     * 底部
     */
    private TextView tv_all;
    private TextView tv_has_scan;
    private TextView tv_deny;
    private TextView tv_access;
    private TextView tv_sync;


    /**
     * 数据相关
     */
    private Barcode2DWithSoft barcode2DWithSoft = null;
    private String currentScanType = ConstLocalData.IS_SINGLE_SCAN;
    private String currentDeliverTag;//同于判断是否s是重新导入数据跳转

    /**
     * dialog相关
     *
     * @param savedInstanceState
     */
    private ProgressDialog progressDialog;
    private ProgressDialog progressUploadDialog;

    /**
     * 数据同步相关
     *
     * @param savedInstanceState
     */

    private int currentLimit = ConstDbLocal.UPLOAD_LIMITE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentOffset = 0;//用于上拉加载更多；

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_operate);


        tl_scan_type.getTabAt(ConstLocalData.TAB_INDEX_0).select();
//        presenterScanOperate.doPermissionCheck(ConstIntent.RequestCode.APPLY_FOR_PERMISSION,
//                Manifest.permission.CAMERA);


        presenterScanOperate.doSetInitData();
        rb_scan_single.setChecked(true);
        Log.i(ConstLog.ATY_LIFE_CIRCLE, "==========onCreate");
    }


    @Override
    protected void onResume() {
        super.onResume();


//        presenterScanOperate.doInitConfigScan();

        presenterScanOperate.doPermissionCheck(ConstIntent.RequestCode.APPLY_FOR_PERMISSION,
                Manifest.permission.CAMERA);
        Log.i(ConstLog.ATY_LIFE_CIRCLE, "==========onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();


        presenterScanOperate.doDealCloseScan(currentScanType, barcode2DWithSoft);
        Log.i(ConstLog.ATY_LIFE_CIRCLE, "==========onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 139) {
            if (event.getRepeatCount() == 0) {


                if (null != barcode2DWithSoft &&
                        null != cb_scan_operate &&
                        null != presenterScanOperate) {

                    cb_scan_operate.toggle();

                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 处理微信充值返回结果
     *
     * @param eventEntity
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventInActivty(EventEntity eventEntity) {


        if (null != eventEntity) {


            switch (eventEntity.getTag()) {


                case ConstEvent.DO_CLOSE_SCAN:  //微信支付成功 返回
                    presenterScanOperate.doCloseScan(barcode2DWithSoft);
                    break;

            }

        }

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


        if (null != intent) {

            Bundle extras = intent.getExtras();
            if (null != extras) {
                currentDeliverTag = extras.getString(ConstIntent.BundleKEY.DELIVER_TAG_RELOAD, ConstIntent.BundleValue.DEFAULT_STRING);
            }
        }

        if (null != barcode2DWithSoft) {


//            barcode2DWithSoft.open(this);
            tl_scan_type.getTabAt(ConstLocalData.TAB_INDEX_0).select();

//            if(!TextUtils.isEmpty(currentDeliverTag) && currentDeliverTag.equals(ConstIntent.BundleValue.DO_RELOAD)) {
//                FragmentScanCurrent fragmentScanCurrent = (FragmentScanCurrent) presenterScanOperate.getFgScanMgr().getFragmentByTag(FragmentScanCurrent.TAG);
//                fragmentScanCurrent.doSetClearData();
//            }


            presenterScanOperate.doClearFgCurrentScanDataWhenReload(currentDeliverTag);

            presenterScanOperate.doSetInitData();
            rb_scan_single.setChecked(true);

        } else {

            presenterScanOperate.doPermissionCheck(ConstIntent.RequestCode.APPLY_FOR_PERMISSION,
                    Manifest.permission.CAMERA);
            presenterScanOperate.doSetInitData();

            presenterScanOperate.doClearFgCurrentScanDataWhenReload(currentDeliverTag);

        }

//        if(null != barcode2DWithSoft) {
//            barcode2DWithSoft.open(this);
//            tl_scan_type.getTabAt(ConstLocalData.TAB_INDEX_0).select();
//            presenterScanOperate.doSetInitData();
//            rb_scan_single.setChecked(true);
//
//        }else {
//
//            presenterScanOperate.doPermissionCheck(ConstIntent.RequestCode.APPLY_FOR_PERMISSION,
//                    Manifest.permission.CAMERA);
//            presenterScanOperate.doSetInitData();
//
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case ConstIntent.RequestCode.SYSYEM_SETTING:

                if (AndPermission.hasPermission(ScanOperateActivity.this, Manifest.permission.CAMERA)) {

                    presenterScanOperate.doInitConfigScan();
                } else {
                    presenterScanOperate.doShowPermissionDialog();
                }

                Log.i("quanxianxxxx", "onActivityResult " + requestCode + "   ");

                break;

        }
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
//        presenterScanOperate.doDealCloseScan(currentScanType, barcode2DWithSoft);

        presenterScanOperate.doCloseScan(barcode2DWithSoft);
        super.onDestroy();
    }


    @Override
    protected PresenterScanOperate creatPresenter() {
        if (null == presenterScanOperate) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            presenterScanOperate =
                    new PresenterScanOperate(this, fragmentManager, fragmentManager.beginTransaction());
        }
        return presenterScanOperate;
    }

    @Override
    protected void initViews() {
        /**
         * title
         */

        tv_common_title = findZETScanViewById(R.id.tv_common_title);
        tv_common_title.setText(R.string.scan_center);
        tv_right = findZETScanViewById(R.id.tv_right);
        tv_right.setText(R.string.setting);
        /**
         * 中间
         */
        rg_scan_type = findZETScanViewById(R.id.rg_scan_type);
        rb_scan_single = findZETScanViewById(R.id.rb_scan_single);
        rb_scan_continue = findZETScanViewById(R.id.rb_scan_continue);
        cb_scan_operate = findZETScanViewById(R.id.cb_scan_operate);
        tl_scan_type = findZETScanViewById(R.id.tl_scan_type);

        /**
         * 这里需要注意下，当我们添加table的时候，就会直接调用addOnTabSelectedListener
         * 所以addOnTabSelectedListener 要写在addTab前面
         */
        tl_scan_type.addOnTabSelectedListener(this);

        tl_scan_type.addTab(tl_scan_type.newTab().setText(R.string.current_scan));
        tl_scan_type.addTab(tl_scan_type.newTab().setText(R.string.all_data));
        tl_scan_type.addTab(tl_scan_type.newTab().setText(R.string.has_scan));
//        tl_scan_type.addTab(tl_scan_type.newTab().setText(R.string.not_scan));
        /**
         * 底部
         */
        tv_all = findZETScanViewById(R.id.tv_all);
        tv_has_scan = findZETScanViewById(R.id.tv_has_scan);
        tv_access = findZETScanViewById(R.id.tv_access);
        tv_deny = findZETScanViewById(R.id.tv_deny);


        tv_sync = findZETScanViewById(R.id.tv_sync);
        //默认第一次加载会进入回调，如果不需要可以配置
    }

    @Override
    protected void initListener() {
        tv_right.setOnClickListener(this);
        rb_scan_single.setOnCheckedChangeListener(this);
        rb_scan_continue.setOnCheckedChangeListener(this);
        cb_scan_operate.setOnCheckedChangeListener(this);
        tv_sync.setOnClickListener(this);
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
    public void doPermissionCheck(int requestCode, String... permissions) {
        AndPermission
                .with(this)
                .requestCode(requestCode)
                .permission(permissions)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {


                        AndPermission.rationaleDialog(ScanOperateActivity.this, rationale).show();

                    }
                })
                .callback(this)
                .start();
    }

    @Override
    public void doShowPermissionDialog() {
        AndPermission.defaultSettingDialog(ScanOperateActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(ScanOperateActivity.this.getString(R.string.permission_title))
                .setMessage(ScanOperateActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(ScanOperateActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(ScanOperateActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ScanOperateActivity.this.finish();
                    }
                })
                .show();
    }

    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
        /**
         * 申请权限全部允许之前不会回调该方法
         */
        Log.i("quanxianxxxx", "onSucceed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                presenterScanOperate.doInitConfigScan();

                break;

        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。
                presenterScanOperate.doShowPermissionDialog();

                break;

        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {


        Log.i("select_Tab", " 选中" + tab.getText());

        presenterScanOperate.doGetDifferentFragmentByPosition(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_right:


                presenterScanOperate.doDealStopScan(currentScanType, barcode2DWithSoft);
                openActivity(SettingActivity.class, null);
                break;

            case R.id.tv_sync:


                presenterScanOperate.doDealStopScan(currentScanType, barcode2DWithSoft);
                presenterScanOperate.doUpload(
                        HttpConst.URL.SET_RING_INFO + HttpConst.ENCRIYTION,
                        currentLimit,
                        currentOffset,
                        ConstDbLocal.ScanAbout.IS_SYNC, ConstDbLocal.DB_FAULSE);

                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {


            switch (buttonView.getId()) {

                case R.id.rb_scan_single:
                    currentScanType = ConstLocalData.IS_SINGLE_SCAN;
                    break;
                case R.id.rb_scan_continue:

                    currentScanType = ConstLocalData.IS_CONTINUE_SCAN;
                    break;

                case R.id.cb_scan_operate:
                    presenterScanOperate.doDealStartScan(currentScanType, barcode2DWithSoft);
                    break;
            }

        } else {

            switch (buttonView.getId()) {

                case R.id.cb_scan_operate:
                    presenterScanOperate.doDealStopScan(currentScanType, barcode2DWithSoft);

                    break;
            }

        }
    }


    @Override
    public void onVertifyErrorScanInitError() {
        CustomToastUtil.showToast(getApplicationContext(), ConstError.ERROR_DEVICE_SCAN_INIT_MSG, ConstLocalData.TOAST_DURATION);
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

                            boolean result = b2DWithSoft.open(ScanOperateActivity.this);

                            if (result) {
//                                b2DWithSoft.setParameter(324, 1);
//                                b2DWithSoft.setParameter(300, 0); // Snapshot Aiming
//                                b2DWithSoft.setParameter(361, 0); // Image Capture Illumination


                                // interleaved 2 of 5
                                b2DWithSoft.setParameter(6, 1);
                                b2DWithSoft.setParameter(22, 0);
                                b2DWithSoft.setParameter(23, 55);
                                b2DWithSoft.setParameter(764, 10);
                                b2DWithSoft.setScanCallback(ScanOperateActivity.this);

                                barcode2DWithSoft = b2DWithSoft;

                            } else {
                                presenterScanOperate.doShowDeviceInitError();
                            }

                        }

                    }
                });
    }

    @Override
    public void doUnableScanTypeCheck() {
        rb_scan_continue.setEnabled(false);
        rb_scan_single.setEnabled(false);
    }

    @Override
    public void doEnableScanTypeCheck() {
        rb_scan_continue.setEnabled(true);
        rb_scan_single.setEnabled(true);
    }

    @Override
    public void doSetScanButtonTextStart() {
        cb_scan_operate.setText(R.string.start);
    }

    @Override
    public void doSetScanButtonTextStop() {
        cb_scan_operate.setText(R.string.stop);
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
    public void doCbUnChecked() {
        cb_scan_operate.setChecked(false);
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
    public void onDataBackSuccessScan(String target) {


        String hasScan = tv_has_scan.getText().toString();
        String denyScan = tv_deny.getText().toString();
        String accessScan = tv_access.getText().toString();


        presenterScanOperate.doDealCheckScanResultToLocal(target,
                Integer.valueOf(hasScan),
                Integer.valueOf(denyScan),
                Integer.valueOf(accessScan), ConstDbLocal.ScanAbout.RING_ID, target);

    }

    @Override
    public void onVertifyErrorNoSuchData() {


        CustomToastUtil.showToast(getApplicationContext(), R.string.no_select_data, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doSaveData(ChickenInfoRaw chickenInfoRaw) {

        presenterScanOperate.doSaveScanResultData(chickenInfoRaw);
    }

    @Override
    public void doSetScanDataToFgUi(ChickenInfoRaw chickenInfoRaw) {


        FragmentScanCurrent fragmentScanCurrent = (FragmentScanCurrent) presenterScanOperate.getFgScanMgr().getFragmentByTag(FragmentScanCurrent.TAG);

        fragmentScanCurrent.doSetScanDataToUi(chickenInfoRaw);
    }

    @Override
    public void doPlaySoundAccess() {
        SoundManage.PlaySound(this, SoundManage.SoundType.SUCCESS);
    }

    @Override
    public void doPlaySoundDeny() {
        SoundManage.PlaySound(this, SoundManage.SoundType.FAILURE);
    }


    @Override
    public void doVertifyErrorHasScanned() {


        CustomToastUtil.showToast(getApplicationContext(), R.string.has_scannd, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onDataBackSuccessAndContinue(int uploadLimite, int uploadOffset) {

        currentLimit = uploadLimite;
        currentOffset = uploadOffset + ConstDbLocal.UPLOAD_OFFSET;
        presenterScanOperate.doUploadContinue(
                HttpConst.URL.SET_RING_INFO + HttpConst.ENCRIYTION,
                uploadLimite,
                currentOffset,
                ConstDbLocal.ScanAbout.IS_SYNC, ConstDbLocal.DB_FAULSE);
    }

    @Override
    public void onDataBackSuccessAndStop() {

    }


    @Override
    public void doSetBottomTextAll(int count) {
        tv_all.setText(String.valueOf(count));
    }

    @Override
    public void doSetBottomTextHasScanned(int count) {
        tv_has_scan.setText(String.valueOf(count));
    }

    @Override
    public void doSetBottomTextDeny(int count) {
        tv_deny.setText(String.valueOf(count));
    }

    @Override
    public void doSetBottomTextAccess(int count) {
        tv_access.setText(String.valueOf(count));
    }

    @Override
    public void doVertifyErrorNoScanBeforeSync() {

        CustomToastUtil.showToast(getApplicationContext(), R.string.upload_before_no_scan, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doVertifySyncFinish() {
        currentLimit = ConstDbLocal.UPLOAD_LIMITE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
        currentOffset = 0;//用于上拉加载更多；
        presenterScanOperate.doUpdateLocalChickenScanData(ConstDbLocal.ScanAbout.IS_SYNC, ConstDbLocal.DB_FAULSE);


        CustomToastUtil.showToast(getApplicationContext(), R.string.sync_finish, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void doShowUploadDialog() {
        if (!isFinishing()) {
            if (null == progressUploadDialog) {
                progressUploadDialog = new ProgressDialog(this);
                progressUploadDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressUploadDialog.setMessage(ConstHz.SYNC_DATA);
                progressUploadDialog.setCanceledOnTouchOutside(false);
            }
            if (!progressUploadDialog.isShowing()) {
                progressUploadDialog.show();
            }
        }
    }

    @Override
    public void doDestroyUploadDialog() {
        if (null != progressUploadDialog) {

            if (progressUploadDialog.isShowing()) {
                progressUploadDialog.dismiss();
            }
            progressDialog = null;
        }
    }

    @Override
    public void doClearFgCurrentScanDataWhenReload() {
        FragmentScanCurrent fragmentScanCurrent = (FragmentScanCurrent) presenterScanOperate.getFgScanMgr().getFragmentByTag(FragmentScanCurrent.TAG);
        fragmentScanCurrent.doSetClearData();
    }


    @Override
    public void onScanComplete(int i, int length, byte[] bytes) {

        presenterScanOperate.doDealScanResult(currentScanType, length, bytes);

    }


}
