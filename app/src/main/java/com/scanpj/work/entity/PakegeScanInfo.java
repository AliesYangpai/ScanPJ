package com.scanpj.work.entity;

import java.util.List;

/**
 * Created by Alie on 2018/6/14.
 * 类描述
 * 版本
 */

public class PakegeScanInfo {

    private List<AnotherScanInfo> rows;


    public PakegeScanInfo() {
    }

    public List<AnotherScanInfo> getRows() {
        return rows;
    }

    public void setRows(List<AnotherScanInfo> rows) {
        this.rows = rows;
    }
}
