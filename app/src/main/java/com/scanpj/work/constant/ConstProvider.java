package com.scanpj.work.constant;

import android.provider.ContactsContract;

/**
 * Created by admin on 2018/5/16.
 * 类描述   用于 手机contentProvider的常量条件类
 * 版本
 */
public class ConstProvider {



    /** 联系人的ID **/
    public static final int PHONES_CONTACT_ID = 0;
    /** 联系人显示名称 **/
    public static final int PHONES_DISPLAY_NAME = 1;
//    /** 头像ID **/
//    public static final int PHONES_PHOTO_ID = 2;


    /** 头像ID **/
    public static final int PHONES_PHOTO_URL = 2;

    /** 电话号码 **/
    public static final int PHONES_NUMBER = 3;

    /**
     * 在contentProvider中获取
     * 顺序为 联系人id，名称，头像id 电话号码
     **/
//    public static final String[] PROJECTION_CONTACTS = new String[]{
//            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
//            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//            ContactsContract.Contacts.Photo.PHOTO_ID,
//            ContactsContract.CommonDataKinds.Phone.NUMBER};


    public static final String[] PROJECTION_CONTACTS = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.Contacts.Photo.PHOTO_THUMBNAIL_URI,
            ContactsContract.CommonDataKinds.Phone.NUMBER};


//    /**
//     * 在contentProvider中获取详情
//     * 顺序为 联系人id，名称，电话号码 头像id
//     **/
//    public static final String[] PROJECTION_CONTACT_INFO = new String[]{
//            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
//            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//            ContactsContract.Contacts.Photo.PHOTO_ID,
//            ContactsContract.CommonDataKinds.Phone.NUMBER};


}