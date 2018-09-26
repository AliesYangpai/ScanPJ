package com.scanpj.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/6/13.
 * 类描述
 * 版本
 */

public class BatchInfo implements Serializable {

    private int id;
    private String name;
    private String note;
    private String date;
    private int house_id;

    public BatchInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    @Override
    public String toString() {
        return "BatchInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                ", house_id=" + house_id +
                '}';
    }
}
