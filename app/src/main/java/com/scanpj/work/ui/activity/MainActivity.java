package com.scanpj.work.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scanpj.work.R;

import com.scanpj.work.constant.ConstIntent;
import com.scanpj.work.constant.ConstLocalData;
import com.scanpj.work.constant.HttpConst;
import com.scanpj.work.entity.ChickenHouseInfo;
import com.scanpj.work.presenter.PresenterMain;
import com.scanpj.work.test.TestContent;
import com.scanpj.work.ui.BaseActivity;
import com.scanpj.work.ui.adapter.ChickenHouseAdapter;
import com.scanpj.work.ui.iview.IMainView;
import com.scanpj.work.ui.widget.GridSpacingItemDecoration;
import com.scanpj.work.util.CustomToastUtil;
import com.scanpj.work.util.ToastUtil;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

public class MainActivity extends BaseActivity<IMainView, PresenterMain> implements
        IMainView,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        OnClickListener {


    private PresenterMain presenterMain;
    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;


    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_list;
    private ChickenHouseAdapter chickenHouseAdapter;


    /**
     * 数据相关
     * @param savedInstanceState
     */

    private String currentTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenterMain.doGetChickenHouseList(HttpConst.URL.GET_ALL_CHICK_HOUSE+HttpConst.ENCRIYTION);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {


            case ConstIntent.ResponseCode.BATCH_LOAD_SUCCESS:
                dofinishItself();
                break;
        }
    }

    @Override
    protected PresenterMain creatPresenter() {
        if (null == presenterMain) {

            presenterMain = new PresenterMain(this);
        }

        return presenterMain;
    }

    @Override
    protected void initViews() {

        /**
         * title
         */

        tv_common_title = findZETScanViewById(R.id.tv_common_title);
        tv_common_title.setText(R.string.chicken_house_list);
        iv_common_back = findZETScanViewById(R.id.iv_common_back);
        if(TextUtil.isEmpty(currentTag)) {
            iv_common_back.setVisibility(View.GONE);
        }

        srefresh_layout = findZETScanViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        rv_list = findZETScanViewById(R.id.rv_list);
        chickenHouseAdapter = new ChickenHouseAdapter(R.layout.item_chicken_house);
        rv_list.setLayoutManager(new GridLayoutManager(this, ConstLocalData.DEFUALT_DYNAMIC_OPERATE_SINGLE_COUNT));
        rv_list.addItemDecoration(new GridSpacingItemDecoration(ConstLocalData.DEFUALT_DYNAMIC_OPERATE_SINGLE_COUNT, 10, true));
        rv_list.setAdapter(chickenHouseAdapter);

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        srefresh_layout.setOnRefreshListener(this);
        chickenHouseAdapter.setOnItemClickListener(this);


    }

    @Override
    protected void processIntent() {


        Intent intent = this.getIntent();
        if(null != intent) {
            Bundle extras = intent.getExtras();
            if(null != extras) {
                currentTag = extras.getString(ConstIntent.BundleKEY.DELIVER_TAG_RELOAD,ConstIntent.BundleValue.DEFAULT_STRING);
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


        CustomToastUtil.showToast(getApplicationContext(),errorMsg, ConstLocalData.TOAST_DURATION);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;
        }
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);
    }

    @Override
    public void onDataBackSuccessForGetChickenHouseRecords(List<ChickenHouseInfo> list) {
        chickenHouseAdapter.setNewData(list);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ChickenHouseInfo chickenHouseInfo = (ChickenHouseInfo) adapter.getData().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_CHICKEN_HOUSE_DETIAL,chickenHouseInfo);
        bundle.putString(ConstIntent.BundleKEY.DELIVER_TAG_RELOAD,currentTag);
        Intent intent = new Intent(this,ConfigActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,ConstIntent.RequestCode.GO_TO_BATCH_RECODS);

    }

    @Override
    public void onRefresh() {
        presenterMain.doGetChickenHouseListInFresh(HttpConst.URL.GET_ALL_CHICK_HOUSE+HttpConst.ENCRIYTION);
    }
}
