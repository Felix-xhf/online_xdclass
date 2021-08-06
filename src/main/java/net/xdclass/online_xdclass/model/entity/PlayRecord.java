package net.xdclass.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * @description:用于记录已经看到第几集了，播放记录类型。
 * @author: Felix_XHF
 * @create:2021-07-30 14:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayRecord {
    //play record主键
    private Integer id;

    //play record 用户的id
    @JsonProperty("user_id")
    private Integer userId;

    //play record  视频video的id
    @JsonProperty("video_id")
    private Integer videoId;

    //play record 当前观看的数量
    @JsonProperty("current_num")
    private Integer currentNum;

    //play record 集id
    @JsonProperty("episode_id")
    private Integer episodeId;

    //play record 创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
