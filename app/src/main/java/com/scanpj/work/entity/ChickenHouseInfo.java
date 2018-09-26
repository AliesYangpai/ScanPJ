package com.scanpj.work.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Alie on 2018/6/11.
 * 类描述
 * 版本
 */

public class ChickenHouseInfo  implements Serializable {

    private int id;//
    private String name;
    private String address;
    private String contacts;

    public ChickenHouseInfo() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "ChickenHouseInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contacts='" + contacts + '\'' +
                '}';
    }
}
