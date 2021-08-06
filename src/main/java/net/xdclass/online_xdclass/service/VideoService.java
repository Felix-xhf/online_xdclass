package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {
    /*
    * @Description: 查找所有的视频
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    List<Video> listVideo();

    /*
    * @Description: 查找轮播图的所有内容
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    List<VideoBanner> listBanner();

    /*
    * @Description: 查询视频详情，包含章，集信息
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    Video finDetailById(int videoId);
}
