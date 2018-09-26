package com.scanpj.work.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scanpj.work.R;
import com.scanpj.work.constant.ConstEvent;
import com.scanpj.work.constant.ConstIntent;
import com.scanpj.work.entity.temp.EventEntity;
import com.scanpj.work.presenter.PresenterSetting;
import com.scanpj.work.ui.BaseActivity;
import com.scanpj.work.ui.iview.ISettingView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SettingActivity extends BaseActivity<ISettingView,PresenterSetting> implements
        ISettingView,
        View.OnClickListener,
        PermissionListener{

    private PresenterSetting presenterSetting;


    /**
     * title
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;

    private TextView tv_load;
    private TextView tv_scan_anoter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }





    @Override
    protected PresenterSetting creatPresenter() {
        if(null == presenterSetting) {
            presenterSetting = new PresenterSetting(this);
        }
        return presenterSetting;
    }

    @Override
    protected void initViews() {
        iv_common_back = findZETScanViewById(R.id.iv_common_back);
        tv_common_title = findZETScanViewById(R.id.tv_common_title);
        tv_common_title.setText(R.string.setting);


          tv_load = findZETScanViewById(R.id.tv_load);
          tv_scan_anoter = findZETScanViewById(R.id.tv_scan_anoter);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        tv_load.setOnClickListener(this);
        tv_scan_anoter.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_common_back:

                dofinishItself();
                break;


            case R.id.tv_load:

//                postEventCloseScan(ConstEvent.DO_CLOSE_SCAN);
                Bundle bundle = new Bundle();
                bundle.putString(ConstIntent.BundleKEY.DELIVER_TAG_RELOAD,ConstIntent.BundleValue.DO_RELOAD);
                openActivity(MainActivity.class,bundle);

                break;
            case R.id.tv_scan_anoter:
//                postEventCloseScan(ConstEvent.DO_CLOSE_SCAN);
                presenterSetting.doPermissionCheck(ConstIntent.RequestCode.APPLY_FOR_PERMISSION,
                        Manifest.permission.CAMERA);
                break;
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


                        AndPermission.rationaleDialog(SettingActivity.this, rationale).show();

                    }
                })
                .callback(this)
                .start();
    }

    @Override
    public void doShowPermissionDialog() {
        AndPermission.defaultSettingDialog(SettingActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(SettingActivity.this.getString(R.string.permission_title))
                .setMessage(SettingActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(SettingActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(SettingActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SettingActivity.this.finish();
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
                presenterSetting.doShowPermissionDialog();

                break;

        }
    }
}
