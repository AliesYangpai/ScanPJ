package com.scanpj.work.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scanpj.work.R;
import com.scanpj.work.constant.ConstError;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.AnotherScanInfo;
import com.scanpj.work.presenter.PresenterScanAnotherScanRecords;
import com.scanpj.work.ui.BaseActivity;
import com.scanpj.work.ui.adapter.ScanAnotherScanRecordsAdapter;
import com.scanpj.work.ui.iview.IScanAnotherScanRecordsView;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;

import java.util.List;

public class ScanAnotherScanRecordsActivity extends BaseActivity<IScanAnotherScanRecordsView, PresenterScanAnotherScanRecords> implements
        IScanAnotherScanRecordsView,
        View.OnClickListener {


    private PresenterScanAnotherScanRecords presenterScanAnotherScanRecords;

    private ImageView iv_common_back;
    private TextView tv_common_title;

    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private ScanAnotherScanRecordsAdapter scanAnotherScanRecordsAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_another_scan_records);


        presenterScanAnotherScanRecords.doGetAnotherScanRecords(
                HttpConst.URL.GET_SCAN_RESULT+HttpConst.ENCRIYTION);
    }

    @Override
    protected PresenterScanAnotherScanRecords creatPresenter() {

        if (null == presenterScanAnotherScanRecords) {
            presenterScanAnotherScanRecords = new PresenterScanAnotherScanRecords(this);
        }
        return presenterScanAnotherScanRecords;
    }

    @Override
    protected void initViews() {
        iv_common_back = findZETScanViewById(R.id.iv_common_back);
        tv_common_title = findZETScanViewById(R.id.tv_common_title);
        tv_common_title.setText(R.string.has_been_scan);


        rv_list = findZETScanViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(this);
        scanAnotherScanRecordsAdapter = new ScanAnotherScanRecordsAdapter(R.layout.item_scan_another_record);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(scanAnotherScanRecordsAdapter);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

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
    public void onDataBackSuccessForGetScanRecords(List<AnotherScanInfo> list) {
        scanAnotherScanRecordsAdapter.setNewData(list);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_common_back:
                dofinishItself();
                break;
        }
    }

}
