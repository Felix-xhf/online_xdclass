package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import net.xdclass.online_xdclass.service.VideoService;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @description:
 * @author: Felix_XHF
 * @create:2021-07-29 20:23
 */
@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    /*
    * @Description: 查询所有的视频，返回列表
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @RequestMapping("list")
    public Object listVideo(){
        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }

    /*
    * @Description: 查询视频详情，包含章，集信息
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true) int videoId){


        Video video = videoService.finDetailById(videoId);
        return JsonData.buildSuccess(video);
    }


    /*
    * @Description: 轮播图列表
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @GetMapping("list_banner")
    public JsonData indexBanner(){
        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }
}