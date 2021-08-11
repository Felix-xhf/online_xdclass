package net.xdclass.online_xdclass;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

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
}
