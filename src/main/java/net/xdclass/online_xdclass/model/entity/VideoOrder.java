package net.xdclass.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * @description:订单对象
 * @author: Felix_XHF
 * @create:2021-07-29 19:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoOrder {
    //订单主键
    private Integer id;

    //订单唯一标识,流水号
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    //订单状态，0未支付，1已支付
    private Integer state;

    //订单生成时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //订单支付金额
    @JsonProperty("total_fee")
    private Integer totalFee;

    //视频主键
    @JsonProperty("video_id")
    private Integer videoId;

    //视频标题
    @JsonProperty("video_title")
    private String videoTitle;

    //视频图片
    @JsonProperty("video_img")
    private String videoImg;

    //用户id
    @JsonProperty("user_id")
    private Integer userId;
}
