package com.scanpj.work.universal.verification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述 规则验证
 * 版本
 */

public class VertifyRule {


    /**
     * 字母、数字（无字母开头限制）8-16个字符之间
     */

    public boolean isLegalPass(String pass) {

        String strPattern = "(?!^\\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{8,16}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(pass);
        return m.matches();
    }





}
