package com.dk.searchexpress.constants;

/**
 * @ClassName: Constant
 * @Author: dongke
 * @Date: 2020/4/10 18:38
 * @Description:
 */
public class Constant {

    public static class KDNiao {
        //电商ID
        public static final String EBusinessID = "1416364";
        //电商加密私钥，快递鸟提供，注意保管，不要泄漏
        public static final String AppKey = "9baaad1c-123b-47b7-8c52-8e92fc37312d";
        //请求url
        public static final String ReqURL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    }

    public static class ERROR_CODE {
        public static final int ERROR_OKHTTP = -2001;
        public static final int ERROR_PARAM_CONVERT= -2002;
    }
}
