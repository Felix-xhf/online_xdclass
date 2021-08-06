package net.xdclass.online_xdclass.service.Impl;

import net.xdclass.online_xdclass.config.CacheKeyManager;
import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import net.xdclass.online_xdclass.mapper.VideoMapper;
import net.xdclass.online_xdclass.service.VideoService;
import net.xdclass.online_xdclass.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
 * @description:
 * @author: Felix_XHF
 * @create:2021-07-29 20:22
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;


    /*
    * @Description: 查找所有的视频
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @Override
    public List<Video> listVideo() {
        try{
            Object cacheObj =
                    baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST,()->{
                        List<Video> videoList = videoMapper.listVideo();
                        return videoList;
                    });
            if(cacheObj instanceof List){
                List<Video> videoList = (List<Video>)cacheObj;
                return videoList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //可以返回兜底数据，业务系统降级-》SpringCloud专题课程
        return null;
    }

    /*
    * @Description: 查找轮播图
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @Override
    public List<VideoBanner> listBanner() {
        try{
            Object cacheObj =baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, ()->{
                        List<VideoBanner> bannerList = videoMapper.listVideoBanner();
                        System.out.println("从数据库⾥里里⾯面找轮播图列列表");
                        return bannerList;
                    });
            if(cacheObj instanceof List){
                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
                return bannerList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

//        //不使用缓存
//        return videoMapper.listVideoBanner();
    }

    @Override
    /*
    * @Description: 查询视频详情，包含章，集信息
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public Video finDetailById(int videoId) {
        String videoCacheKey =
                String.format(CacheKeyManager.VIDEO_DETAIL,videoId);
        try {
            Object cacheObject = baseCache.getOneHourCache().get(videoCacheKey, ()->{
                // 需要使⽤用mybaits关联复杂查询
                Video video = videoMapper.findDetailById(videoId);
                return video;
            });
            if(cacheObject instanceof Video){
                Video video = (Video)cacheObject;
                return video;
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
