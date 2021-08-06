package net.xdclass.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/*
 * @description:章节类对象
 * @author: Felix_XHF
 * @create:2021-07-29 19:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    //章节id
    private Integer id;
    //对应视频的id，视频主键
    @JsonProperty("video_id")
    private Integer videoId;
    //章节名称
    private String title;
    //章节顺序
    private Integer ordered;
    //创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //以下的部分不是映射数据库的，而是表示一个视频中有多少章，多少集等其他内容
    @JsonProperty("episode_list")
    private List<Episode> episodeList;
}
