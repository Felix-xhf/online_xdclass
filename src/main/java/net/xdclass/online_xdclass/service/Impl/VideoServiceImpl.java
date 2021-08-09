package net.xdclass.online_xdclass.service.Impl;

import net.xdclass.online_xdclass.config.CacheKeyManager;
import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import net.xdclass.online_xdclass.mapper.VideoMapper;
import net.xdclass.online_xdclass.service.VideoService;
import net.xdclass.online_xdclass.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    * @Description: 查找轮播图 + 本地Guava缓存
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @Override
    public List<VideoBanner> listBanner() {
        try{
            /*
            * getTenMinuteCache,到本地TenMinuteCache寻找缓存数据
            * 每个存到本地Guava缓存的都有一个key与之对应，所以需要一个config来管理key
            * 会使用到回调函数，回调函数使用的是Lambda表达式来写的，格式  ()->{方法体}
            * */
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, ()->{
                        List<VideoBanner> bannerList = videoMapper.listVideoBanner();
                        System.out.println("从数据库⾥里里⾯面找轮播图列列表");
                        return bannerList;
                    });

            //缓存一般取出来都是object类，这个时候需要强转成我们需要的类型，强转前需要先判断一下是不是我们需要的类型，防止误取出其他的缓存信息
            if(cacheObj instanceof List){
                return (List<VideoBanner>)cacheObj;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

//        //不使用Guava本地缓存
//        return videoMapper.listVideoBanner();
    }

    /*
    * @Description: 不使用本地缓存，直接查询数据库
    * @Author: Mr.Felix
    * @Time: 2021/8/7
    **/
    @Override
    public List<VideoBanner> listBannerMySQL() {
        return videoMapper.listVideoBanner();
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
