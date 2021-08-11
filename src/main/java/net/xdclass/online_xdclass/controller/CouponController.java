package net.xdclass.online_xdclass.controller;

/*
 * @description:手写原生式分布锁
 * @author: Felix_XHF
 * @create:2021-08-10 15:10
 */

import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /*
    * 假设有线程A和线程B同时对共享资源进行操作，例如优惠券
    * */
    @GetMapping("add")
    public JsonData saveCoupon(@RequestParam(value = "coupon_id",required = true)int couponId){
        //防止其他线程误删，要获取当前操作线程的uuid，方便加锁
        String uuid = UUID.randomUUID().toString();

        String lockKey = "lock:coupon:" + couponId;
        lock(couponId,uuid,lockKey);
        return JsonData.buildSuccess();
    }


    private void lock(int couponId,String uuid,String lockKey){
        //lua脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

        Boolean nativeLock = redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, Duration.ofSeconds(30));
        System.out.println(uuid + ":加锁状态:" + nativeLock);
        if (nativeLock){
            //加锁成功，该线程拿到了操作的权限，如果没有拿到就等待自旋操作
            try{
                //TODO:做相关业务逻辑，在try中做相关逻辑。模拟使用sleep代替
                try {
                    TimeUnit.SECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                //解锁，使用lua脚本进行解锁
                Long result = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class)
                        ,Arrays.asList(lockKey), uuid);
                System.out.println("解锁状态" + result);
            }
        }else{
            //加锁不成功，自选操作
            try {
                System.out.println("加锁失败，睡眠5秒 进行自旋");
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //睡眠一会再尝试获取锁
            lock(couponId,uuid,lockKey);
        }
    }

}
