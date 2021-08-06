package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    
    /*
    * @Description: 用户下单视频
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    int save(int userId,int videoId);

    
    /*
    * @Description: 查询指定用户的所有订单列表
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    List<VideoOrder> listOrderByUserId(Integer userId);
}
