package net.xdclass.online_xdclass;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private VideoService videoService;
    private static final String DAILY_RANK_KEY = "video:rank:daily";
    @Test
    public void saveRank(){
        List<Video> list = videoService.listVideo();
        for (Video video : list) {
            redisTemplate.opsForList().rightPush(DAILY_RANK_KEY,video);
        }
    }


    /*
    测试去重的功能 redis  set集合
    */
    @Test
    public void userProfile(){
        BoundSetOperations operations = redisTemplate.boundSetOps("user:tags:1");
        operations.add("car","student","rich","dog","rich");
        Set<String> set = operations.members();
        System.out.println(set);
        operations.remove("dog");
        Set<String> set2 = operations.members();
        System.out.println(set2);
    }
}
