package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.model.entity.Episode;
import org.apache.ibatis.annotations.Param;

public interface EpisodeMapper {
    /*
    * @Description: 根据视频id查找到该视频下第一集的详情
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    Episode findFirstEpisodeById(@Param("video_id") int videoId);
}
