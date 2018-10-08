package cn.txm.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    //系统中用户在Session中的表示符
    public static String USER="SYS_USER";

    /*--------- 系统权限集合 ----------*/
    public static String PRIVILEGE_XZGL="xzgl";//行政管理
    public static String PRIVILEGE_HQFW="hqfw";//后勤服务
    public static String PRIVILEGE_ZXXX="zxxx";//在线学习
    public static String PRIVILEGE_NSFW="nsfw";//纳税服务
    public static String PRIVILEGE_SPACE="space";//我的空间

    public static Map<String,String> PRIVILEGE_MAP;
    static {
        PRIVILEGE_MAP=new HashMap<String,String>();
        PRIVILEGE_MAP.put(PRIVILEGE_XZGL,"行政管理");
        PRIVILEGE_MAP.put(PRIVILEGE_HQFW,"后勤服务");
        PRIVILEGE_MAP.put(PRIVILEGE_ZXXX,"在线学习");
        PRIVILEGE_MAP.put(PRIVILEGE_NSFW,"纳税服务");
        PRIVILEGE_MAP.put(PRIVILEGE_SPACE,"我的空间");
    }
}