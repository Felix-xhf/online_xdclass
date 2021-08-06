package net.xdclass.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * @description:用户对象
 * @author: Felix_XHF
 * @create:2021-07-29 19:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户对象主键
    private Integer id;
    //昵称
    private String name;
    //密码
    private String pwd;
    //头像
    @JsonProperty("head_img")
    private String headImg;
    //手机号
    private String phone;
    //创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
