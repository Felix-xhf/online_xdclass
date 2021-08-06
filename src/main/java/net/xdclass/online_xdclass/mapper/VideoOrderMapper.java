package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderMapper {

    /*
    * @Description: 查询用户是否购买过此商品
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") int userId, @Param("video_id") int videoId, @Param("state") int state);

    /*
    * @Description: 下单
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    int saveOrder(VideoOrder videoOrder);

    /*
    * @Description: 视频列表
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    List<VideoOrder> listOrderByUserId(@Param("user_id") Integer userId);
}
