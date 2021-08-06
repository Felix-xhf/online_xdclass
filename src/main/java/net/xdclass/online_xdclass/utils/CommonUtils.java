package net.xdclass.online_xdclass.utils;

import java.security.MessageDigest;

/*
 * @description:通用工具类
 * @author: Felix_XHF
 * @create:2021-07-29 22:42
 */
public class CommonUtils {
    /*
    * @Description: MD5通用工具类
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public static String MD5(String data) {
        try {
            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;
    }
}
