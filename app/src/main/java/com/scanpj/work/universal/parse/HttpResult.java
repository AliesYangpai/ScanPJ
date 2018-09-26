package com.scanpj.work.universal.parse;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/17 0017.
 * 类描述 服务器返回的基类，这个类中的所有字段，均要与服务器的整体json字段一一对应
 * 版本
 */
public class HttpResult<T> implements Serializable{

    public int code;       //需要修改，具体修改情况依照服务器接口返回

    public String msg; //需要修改，具体修改情况依照服务器接口返回

    public T data;         //需要修改，具体修改情况依照服务器接口返回


    /**
     * 扩展字段
     * 0:data为对象
     * 1:data为集合
     * 2:date为空或者null字段
     */
    public int dataType;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
