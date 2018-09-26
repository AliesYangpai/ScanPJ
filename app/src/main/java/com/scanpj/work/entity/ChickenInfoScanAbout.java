package com.scanpj.work.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Alie on 2018/6/11.
 * 类描述   鸡的信息
 * 版本
 */

public class ChickenInfoScanAbout extends DataSupport implements Serializable {


    private int id;
    private String ringid;//脚环id
    private String houseid;//鸡场id
    private String batchid;//批次id

    private int total;//总步数
    private String shortUrl;//短网址
    private boolean isscaned;
    private int flag;//验证标记
    private boolean isSync;//是否已经被同步


    private String name;//批次名称

    public ChickenInfoScanAbout() {
    }

    public String getRingid() {
        return ringid;
    }

    public void setRingid(String ringid) {
        this.ringid = ringid;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isIsscaned() {
        return isscaned;
    }

    public void setIsscaned(boolean isscaned) {
        this.isscaned = isscaned;
    }


    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChickenInfoScanAbout{" +
                "id=" + id +
                ", ringid='" + ringid + '\'' +
                ", houseid='" + houseid + '\'' +
                ", batchid='" + batchid + '\'' +
                ", total=" + total +
                ", shortUrl='" + shortUrl + '\'' +
                ", isscaned=" + isscaned +
                ", flag=" + flag +
                ", isSync=" + isSync +
                ", name='" + name + '\'' +
                '}';
    }
}
