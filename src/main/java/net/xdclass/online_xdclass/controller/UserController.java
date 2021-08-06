package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.model.entity.User;
import net.xdclass.online_xdclass.model.request.LoginRequest;
import net.xdclass.online_xdclass.service.UserService;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
 * @description:用户注册登录控制
 * @author: Felix_XHF
 * @create:2021-07-29 22:11
 */
@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    * @Description: @RequestBody 是因为前端传来的数据时Json的格式，所以需要添加这个注释
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo){
        int row = userService.save(userInfo);
        return row == 1?JsonData.buildSuccess():JsonData.buildError("注册失败，请重试");
    }

    /*
    * @Description: 登录接口
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
        return token == null ? JsonData.buildError("登录失败，账号或者密码错误"):JsonData.buildSuccess(token);
    }

    /*
    * @Description: 根据用户id查询用户信息
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){
        Integer userId = (Integer)request.getAttribute("user_id");
        if (userId == null){
            return JsonData.buildError("查询失败");
        }
        User user = userService.findByUserId(userId);
        return JsonData.buildSuccess(user);
    }
}