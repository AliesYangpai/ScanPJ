package com.scanpj.work.entity.temp;


/**
 * Created by Administrator on 2017/5/17.
 * 类描述   用于eventBus对象传递
 * 版本
 */

public class EventEntity<T> {

    private int tag;
    private String notifyMsg;


    private T t;


    public EventEntity() {
    }


    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "tag=" + tag +
                ", notifyMsg='" + notifyMsg + '\'' +
                ", t=" + t +
                '}';
    }
}
