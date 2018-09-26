package com.scanpj.work.universal.logic;

/**
 * Created by admin on 2018/5/31.
 * 类描述  判断名名称长度
 * 版本
 */
public class LogicName {



    /**
     * 1个字的名字
     * @param str
     * @return
     */
    public boolean is1WordName(String str) {

        return str.length() == 1;
    }


    /**
     * 2个字的名字
     * @param str
     * @return
     */
    public boolean is2WordName(String str) {

        return str.length() == 2;
    }


    /**
     * 3个字的名字
     * @param str
     * @return
     */
    public boolean is3WordName(String str) {
        return str.length() == 3;
    }


    /**
     * 4个字的名字
     * @param str
     * @return
     */
    public boolean is4WordName(String str) {

        return str.length() == 4;
    }


    /**
     * 超过个字的名字
     * @param str
     * @return
     */
    public boolean is4BeyondWordName(String str) {
        return str.length() > 4;
    }

}