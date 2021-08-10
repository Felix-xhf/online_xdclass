package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import net.xdclass.online_xdclass.service.VideoService;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

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



    @Autowired
    private RedisTemplate redisTemplate;
    //缓存存在缓存的key
    private static final String VIDEO_BANNER_CACHE_KEY = "video:banner:key";
    /*
    * @Description: 使用Redis访问轮播图列表
    * @Author: Mr.Felix
    * @Time: 2021/8/9
    **/
    @GetMapping("list_banner_redis")
    public JsonData indexBannerRedis(){
        Object o = redisTemplate.opsForValue().get(VIDEO_BANNER_CACHE_KEY);
        if (o != null){
            List<VideoBanner> list = (List<VideoBanner>) o;
//            System.out.println("从Redis缓存中读取");
            return JsonData.buildSuccess(list);
        }else{
            List<VideoBanner> list = videoService.listBannerMySQL();
//            System.out.println("从MySQL中去查找数据");
            redisTemplate.opsForValue().set(VIDEO_BANNER_CACHE_KEY, list,10, TimeUnit.MINUTES);
            return JsonData.buildSuccess(list);
        }
    }
}