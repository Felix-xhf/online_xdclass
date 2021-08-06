package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {
    /*
     * @Description:新增用户
     * @Author: Mr.Felix
     * @Time: 2021/7/29
     **/
    int save(Map<String,String> userInfo);

    /*
     * @Description: 根据手机号码查询用户
     * @Author: Mr.Felix
     * @Time: 2021/7/29
     **/
    User findByPhone(@Param("phone") String phone);

    
    /*
    * @Description: 用户登录功能
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    String findByPhoneAndPwd(String phone, String pwd);

    /*
    * @Description: 根据用户id查询用户信息
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    User findByUserId(Integer userId);
}
