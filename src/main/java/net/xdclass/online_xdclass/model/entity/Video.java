package net.xdclass.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/*
 * @description:Video视频类
 * @author: Felix_XHF
 * @create:2021-07-29 19:37
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {
    //视频id
    private Integer id;
    //视频标题
    private String title;
    //视频概述
    private String summary;

    //视频封面图
    @JsonProperty("cover_img")
    private String coverImg;
    //视频价格
    private Integer price;

    //视频创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //视频分数
    private Double point;


    //以下的部分不是映射数据库的，而是表示一个视频中有多少章，多少集等其他内容
    @JsonProperty("chapter_list")
    private List<Chapter>  chapterList;
}
