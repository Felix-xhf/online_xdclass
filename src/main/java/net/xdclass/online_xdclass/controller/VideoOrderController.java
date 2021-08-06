package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.model.entity.VideoOrder;
import net.xdclass.online_xdclass.model.request.VideoOrderRequest;
import net.xdclass.online_xdclass.service.VideoOrderService;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * @description:视频下单控制
 * @author: Felix_XHF
 * @create:2021-07-30 11:29
 */
@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {
    @Autowired
    private VideoOrderService videoOrderService;



    /*
    * @Description: 下单接口
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @RequestMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        int rows = videoOrderService.save(userId, videoOrderRequest.getVideoId());
        return  rows==0? JsonData.buildError("下单失败"):JsonData.buildSuccess();
    }

    /*
    * @Description: 查询指定用户的所有订单列表
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrders = videoOrderService.listOrderByUserId(userId);
        return JsonData.buildSuccess(videoOrders);
    }
}