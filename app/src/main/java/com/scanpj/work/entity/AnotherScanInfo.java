package com.scanpj.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/6/12.
 * 类描述
 * 版本
 */

public class AnotherScanInfo implements Serializable{

    private String ringid;
    private String code;
    private String inserttime;


    public AnotherScanInfo() {
    }

    public String getRingid() {
        return ringid;
    }

    public void setRingid(String ringid) {
        this.ringid = ringid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }
}
