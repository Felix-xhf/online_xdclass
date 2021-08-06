package net.xdclass.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * @description:视频轮播图
 * @author: Felix_XHF
 * @create:2021-07-29 19:34
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoBanner {
    //视频轮播图id
    private Integer id;

    //视频轮播图链接地址
    private String url;

    //视频轮播图图片
    private String img;

    //视频轮播图创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //视频轮播图
    private Integer weight;
}
