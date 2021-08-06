package net.xdclass.online_xdclass.config;

/*
 * @description:缓存Key管理类
 * @author: Felix_XHF
 * @create:2021-07-30 16:29
 */
public class CacheKeyManager {

    //首页轮播图key
    public static final String INDEX_BANNER_KEY = "index:banner:list";

    //首页List缓存 的Key
    public static final String INDEX_VIDEO_LIST = "index:video:list";


    /*
    * @Description: 视频详情缓存key，%s是视频id
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    public static final String VIDEO_DETAIL = "video:detail:%s";
}