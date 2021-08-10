package net.xdclass.online_xdclass;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class OnlineXdclassApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads(){

    }

    @Test
    void testStringSet(){
        redisTemplate.opsForValue().set("name","xdclass");

    }

    @Test
    void testStringGet(){
        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println(name);

        String name1 = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name1);

    }

}
