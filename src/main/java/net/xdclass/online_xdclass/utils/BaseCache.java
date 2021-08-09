package net.xdclass.online_xdclass.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/*
 * @description:基础缓存，使用Guava
 * @author: Felix_XHF
 * @create:2021-07-30 16:19
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCache {

    /*
    * @Description: 10分钟的Guava缓存，存储在本地
    * @Author: Mr.Felix
    * @Time: 2021/8/7
    **/
    private Cache<String,Object> tenMinuteCache = CacheBuilder.newBuilder()
            //设置缓存初始大小，应该合理设置，后续会扩容
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)
            //缓存过期时间，写⼊入后10分钟过期
            .expireAfterWrite(600,TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();


    /*
    * @Description: 1小时的Guava缓存，保存在本地
    * @Author: Mr.Felix
    * @Time: 2021/8/7
    **/
    private Cache<String,Object> oneHourCache = CacheBuilder.newBuilder()
            //设置缓存初始大小，应该合理设置，后续会扩容
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)
            //缓存过期时间，写⼊入后60分钟过期
            .expireAfterWrite(3600,TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();
}
