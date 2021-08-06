package net.xdclass.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * @description:集对象
 * @author: Felix_XHF
 * @create:2021-07-29 19:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Episode {
    //集主键
    private Integer id;
    //集标题
    private String title;
    //第几集，全局顺序
    private Integer num;
    //顺序，章里面的顺序
    private Integer ordered;
    //播放地址
    @JsonProperty("play_url")
    private String playUrl;
    //章节主键id，属于哪一章
    @JsonProperty("chapter_id")
    private Integer chapterId;
    //是否免费
    private Integer free;
    //视频主键，属于哪一个视频
    @JsonProperty("video_id")
    private Integer videoId;
    //集创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
