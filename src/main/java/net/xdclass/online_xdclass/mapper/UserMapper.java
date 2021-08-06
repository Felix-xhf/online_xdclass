package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /*
    * @Description:新增用户
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    int save(User user);

    /*
    * @Description: 根据手机号码查询用户
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    User findByPhone(@Param("phone") String phone);

    /*
    * @Description: 根据号码和密码查询用户
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String md5);

    /*
    * @Description: 根据用户id查询用户信息
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    User findByUserId(@Param("user_id") Integer userId);
}
