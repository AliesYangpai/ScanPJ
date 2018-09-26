package com.scanpj.work.method;

import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.listener.OnDataBackListener;

import java.util.List;

/**
 * Created by Alie on 2018/6/11.
 * 类描述   鸡场
 * 版本
 */

public interface IChicken {






    void doSetScanResault(String url, List<ChickenInfoScanAbout> list, OnDataBackListener onDataBackListener);
}
