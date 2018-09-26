package com.scanpj.work.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scanpj.work.R;
import com.scanpj.work.callback.adapter.BatchItemSelectCallBack;
import com.scanpj.work.callback.dialogcallback.ReloadAlertDialogCallBack;
import com.scanpj.work.constant.ConstHz;
import com.scanpj.work.constant.ConstIntent;
import com.scanpj.work.constant.ConstDbRemote;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.ConstSign;
import com.scanpj.work.constant.ConstSp;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.BatchInfo;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.presenter.PresenterConfig;
import com.scanpj.work.test.TestContent;
import com.scanpj.work.ui.BaseActivity;
import com.scanpj.work.ui.adapter.BatchAdapter;
import com.scanpj.work.ui.iview.IConfigView;
import com.scanpj.work.ui.widget.dialog.ReloadAlertDialog;
import com.scanpj.work.universal.cache.sp.SpUtil;
import com.scanpj.work.universal.connectremote.RemoteDbConnect;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ConfigActivity extends BaseActivity<IConfigView, PresenterConfig> implements
        IConfigView,
        OnClickListener,
        PermissionListener,
        BaseQuickAdapter.OnItemClickListener,
        ReloadAlertDialogCallBack,
        CompoundButton.OnCheckedChangeListener,
        TextView.OnEditorActionListener,
        BatchItemSelectCallBack {


    private PresenterConfig presenterConfig;


    /**
     * title
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private CheckBox cb_check_title;


    private TextView tv_chicken_house_name;
    private TextView tv_chicken_house_address;
    private EditText et_feet_step;

    /**
     * 底部
     *
     * @param savedInstanceState
     */

    private TextView tv_bottom;

    private NestedScrollView nsv_list;

    private LinearLayout ll_content;
    private RecyclerView rv_list;
    private BatchAdapter batchAdapter;


    /**
     * dialog相关
     *
     * @param savedInstanceState
     */
    private ProgressDialog progressDialog;
    private ReloadAlertDialog reloadAlertDialog;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private ChickenHouseInfo currentChickenHouseInfo;
    private String currentTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        tv_chicken_house_name.setText(currentChickenHouseInfo.getName());
        tv_chicken_house_address.setText(currentChickenHouseInfo.getAddress());


        String url = HttpConst.URL.GET_BATCH_INFO_LIST + HttpConst.ENCRIYTION
                + ConstSign.AND +
                HttpConst.REQUEST_KET_HOUSEID + ConstSign.EQUAL_SIGN + currentChickenHouseInfo.getId();


        presenterConfig.doGetChickenBatchRecords(url);
    }


    @Override
    protected void onDestroy() {
        if (null != reloadAlertDialog && reloadAlertDialog.isShowing()) {
            reloadAlertDialog.dismiss();
            reloadAlertDialog = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected PresenterConfig creatPresenter() {
        if (null == presenterConfig) {
            presenterConfig = new PresenterConfig(this);
        }
        return presenterConfig;
    }

    @Override
    protected void initViews() {


        iv_common_back = findZETScanViewById(R.id.iv_common_back);
        tv_common_title = findZETScanViewById(R.id.tv_common_title);

        cb_check_title = findZETScanViewById(R.id.cb_check_title);
        cb_check_title.setText(R.string.select_all);
        cb_check_title.setOnCheckedChangeListener(this);


        tv_common_title.setText(R.string.chicken_house_detial);


        tv_chicken_house_name = findZETScanViewById(R.id.tv_chicken_house_name);
        tv_chicken_house_address = findZETScanViewById(R.id.tv_chicken_house_address);

        et_feet_step = findZETScanViewById(R.id.et_feet_step);

        nsv_list = findZETScanViewById(R.id.nsv_list);
        ll_content = findZETScanViewById(R.id.ll_content);
        rv_list = findZETScanViewById(R.id.rv_list);
        batchAdapter = new BatchAdapter(R.layout.item_batch);
        batchAdapter.setBatchItemSelectCallBack(this);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(batchAdapter);


        tv_bottom = findZETScanViewById(R.id.tv_bottom);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        tv_bottom.setOnClickListener(this);

        et_feet_step.setOnEditorActionListener(this);
        batchAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();
        if (null != intent) {

            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                currentChickenHouseInfo = (ChickenHouseInfo) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_CHICKEN_HOUSE_DETIAL);
                currentTag = bundle.getString(ConstIntent.BundleKEY.DELIVER_TAG_RELOAD, ConstIntent.BundleValue.DEFAULT_STRING);
            }
        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.tv_bottom:

                String step = et_feet_step.getText().toString();
                Map mapSelect = batchAdapter.getMap();
                Log.i("select_data_", "选中个数：" + batchAdapter.getMap().size());
                presenterConfig.doDealLoadOrReload(step, mapSelect, currentTag);
                break;


        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Map map = batchAdapter.getMap();
        List<BatchInfo> list = batchAdapter.getData();
        presenterConfig.doDealSelect(isChecked, list, map);


    }


    @Override
    public void doTitleCbVisible() {
        cb_check_title.setVisibility(View.VISIBLE);
    }

    @Override
    public void doTitleCbGone() {
        cb_check_title.setVisibility(View.GONE);
    }

    @Override
    public void onDataBackSuccessForChickenBatchDetail(List<BatchInfo> list) {
        batchAdapter.setNewData(list);
    }


    @Override
    public void doAllSelect() {
//        batchAdapter.notifyDataSetChanged();

        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                batchAdapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    @Override
    public void doAllUnSelect() {
//        batchAdapter.notifyDataSetChanged();

        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                batchAdapter.notifyDataSetChanged();
            }
        };
        handler.post(r);


    }

    @Override
    public void doRightCbTextAllSelect() {
        cb_check_title.setText(R.string.select_all);
    }

    @Override
    public void doRightTextAllCancel() {
        cb_check_title.setText(R.string.select_cancel);
    }


    @Override
    public void doRxRemoteDbConnect(final RemoteDbConnect remoteDbConnect) {

        doShowRemoteDbDialog();

        Observable.create(new Observable.OnSubscribe<Connection>() {
            @Override
            public void call(Subscriber<? super Connection> subscriber) {

                Connection connection = remoteDbConnect.openConnection(ConstDbRemote.URL, ConstDbRemote.USER_NAME, ConstDbRemote.PASS_WORD);
                subscriber.onNext(connection);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Connection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        doDestroyRemoteDbDialog();
                    }

                    @Override
                    public void onNext(Connection connection) {


                        Map map = batchAdapter.getMap();
                        presenterConfig.doQueryRemoteDb(connection, remoteDbConnect, map);

                    }
                });
    }

    @Override
    public void doRxRemoteDbQuery(final Connection connection, final RemoteDbConnect remoteDbConnect, final String sql) {

        Observable.create(new Observable.OnSubscribe<List<ChickenInfoRaw>>() {
            @Override
            public void call(Subscriber<? super List<ChickenInfoRaw>> subscriber) {

                List<ChickenInfoRaw> list = remoteDbConnect.query(connection, sql);

                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ChickenInfoRaw>>() {
                    @Override
                    public void onCompleted() {
                        doDestroyRemoteDbDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ChickenInfoRaw> list) {
                        presenterConfig.doSaveRemoteDataToLocal(list);

                    }
                });

    }

    @Override
    public void doShowRemoteDbDialog() {
        if (!isFinishing()) {
            if (null == progressDialog) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage(ConstHz.LOADING_DATA);
                progressDialog.setCanceledOnTouchOutside(false);
            }
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    @Override
    public void doDestroyRemoteDbDialog() {

        if (null != progressDialog) {

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }

    @Override
    public void onVertifyErrorConnectRemoteDbFail() {
        doDestroyRemoteDbDialog();

        CustomToastUtil.showToast(getApplicationContext(), R.string.remotedb_connect_fail, ConstLocalData.TOAST_DURATION);
    }


    @Override
    public void onVertifyErrorNullStep() {

        CustomToastUtil.showToast(getApplicationContext(), R.string.enter_compare_step, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onVertifyError0Step() {

        CustomToastUtil.showToast(getApplicationContext(), R.string.compare_step_not_0, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onVertifyErrorNoSelectBatch() {


        CustomToastUtil.showToast(getApplicationContext(), R.string.select_batch, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onVertifyErrorNoSelectData() {

        CustomToastUtil.showToast(getApplicationContext(), R.string.no_select_data, ConstLocalData.TOAST_DURATION);
    }

    @Override
    public void onDataBackSuccessGoToOperate() {


        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_LOAD_OR_NOT, true);
        SpUtil.getInstance().saveLongToSp(ConstSp.SP_KEY_ENTER_STEP, Integer.valueOf(et_feet_step.getText().toString()));


        presenterConfig.doDealClearScannedOrNot(currentTag);

//        this.setResult(ConstIntent.ResponseCode.BATCH_LOAD_SUCCESS);
//        dofinishItself();
//        openActivity(ScanOperateActivity.class, null);
    }

    @Override
    public void doShowReloadAlertDialog() {
        if (reloadAlertDialog == null) {
            reloadAlertDialog = new ReloadAlertDialog(this);
            reloadAlertDialog.setReloadAlertDialogCallBack(this);
            reloadAlertDialog.setCancelable(false);
        }
        reloadAlertDialog.show();
    }

    @Override
    public void doLoadData() {
        presenterConfig.doConnectRemoteDb();
    }

    @Override
    public void doTitleRightCbAllCheckedOrNotInItemSelect(boolean isCheck) {
        cb_check_title.setChecked(isCheck);
    }

    @Override
    public void doScrollToUiBottom() {


        nsv_list.post(new MyRunnable(this));
    }

    @Override
    public void doGotoOpeatePageFromFirstLoad() {
        this.setResult(ConstIntent.ResponseCode.BATCH_LOAD_SUCCESS);
        dofinishItself();
        openActivity(ScanOperateActivity.class, null);
    }

    @Override
    public void doGotoOpeatePageFromReload() {

        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVER_TAG_RELOAD,currentTag);
        this.setResult(ConstIntent.ResponseCode.BATCH_LOAD_SUCCESS);
        dofinishItself();
        openActivity(ScanOperateActivity.class, bundle);
    }


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


            tv_bottom.setFocusable(true);
            tv_bottom.setFocusableInTouchMode(true);
            tv_bottom.requestFocus();
        }

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


                        AndPermission.rationaleDialog(ConfigActivity.this, rationale).show();

                    }
                })
                .callback(this)
                .start();
    }

    @Override
    public void doShowPermissionDialog() {
        AndPermission.defaultSettingDialog(ConfigActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(ConfigActivity.this.getString(R.string.permission_title))
                .setMessage(ConfigActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(ConfigActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(ConfigActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConfigActivity.this.finish();
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


                openActivity(ScanAnotherActivity.class, null);

                break;

        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。
                presenterConfig.doShowPermissionDialog();

                break;

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CheckBox cb_check = (CheckBox) view.findViewById(R.id.cb_check);
        cb_check.toggle();
    }

    @Override
    public void onReloadAlertSure() {

        if (null != reloadAlertDialog && reloadAlertDialog.isShowing()) {
            reloadAlertDialog.dismiss();
        }

        presenterConfig.doConnectRemoteDb();
    }

    @Override
    public void onReloadAlertCancel() {
        if (null != reloadAlertDialog && reloadAlertDialog.isShowing()) {
            reloadAlertDialog.dismiss();
        }
    }


    @Override
    public void onItemDoSelect(Map map, BatchInfo item) {
        map.put(String.valueOf(item.getId()), item.getId());
        List<BatchInfo> list = batchAdapter.getData();
        presenterConfig.doChangeTitleRightCbUi(map, list);
}

    @Override
    public void onItemDoUnSelect(Map map, BatchInfo item) {
        map.remove(String.valueOf(item.getId()));
        List<BatchInfo> list = batchAdapter.getData();
        presenterConfig.doChangeTitleRightCbUi(map, list);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //判断是否是“完成”键
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            //隐藏软键盘
            InputMethodManager imm = (InputMethodManager) v
                    .getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(
                        v.getApplicationWindowToken(), 0);
            }


            String step = et_feet_step.getText().toString();
            Map mapSelect = batchAdapter.getMap();
            Log.i("select_data_", "选中个数：" + batchAdapter.getMap().size());
            presenterConfig.doDealInSoftKey(step, mapSelect);

            return true;
        }
        return false;
    }
}
