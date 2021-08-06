package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VideoMapper {

    /*
    * @Description: 查询视频列表
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    List<Video> listVideo();

    /*
    * @Description: 查找所有的轮播图
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    List<VideoBanner> listVideoBanner();

    /*
    * @Description: 查询视频详情
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    Video findDetailById(@Param("video_id") int videoId);


    /*
    * @Description: 根据video id 查询视频的基本情况
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    Video findById(@Param("video_id") int videoId);
}
