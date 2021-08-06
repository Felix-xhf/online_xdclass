package net.xdclass.online_xdclass.service.Impl;

import net.xdclass.online_xdclass.model.entity.User;
import net.xdclass.online_xdclass.mapper.UserMapper;
import net.xdclass.online_xdclass.service.UserService;
import net.xdclass.online_xdclass.utils.CommonUtils;
import net.xdclass.online_xdclass.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/*
 * @description:
 * @author: Felix_XHF
 * @create:2021-07-29 22:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /*
    * @Description: 注册一个新用户
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user != null){
            return userMapper.save(user);
        }else {
            return -1;
        }
    }

    /*
    * @Description: 解析User对象，数据是前端通过Map<String,String>的形式传来的
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    private User parseToUser(Map<String, String> userInfo) {
        //从前端传过来的数据，必须填写一下的三个数据，phone，pwd和name
        if (userInfo.containsKey("phone") && userInfo.containsKey("pwd")&& userInfo.containsKey("name")){
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());
            user.setPhone(userInfo.get("phone"));
            String pwd = userInfo.get("pwd");
            //MD5加密
            user.setPwd(CommonUtils.MD5(pwd));
            return user;
        }else{
            return null;
        }
    }


    //放在CDN的随机头像
    // TODO：过期了，到时候需要自己替换掉
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };
    //getRandomImg随机获取头像方法
    private String getRandomImg(){
        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }


    /*
    * @Description: 根据phone查询用户
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @Override
    public User findByPhone(String phone) {
        //TODO:
        return null;
    }

    /*
    * @Description: 用户登录操作,通过密码和phone查询 用户
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));
        if (user == null){
            return null;
        }else{
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }
    }

    /*
    * @Description: 根据用户id 查询用户信息
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @Override
    public User findByUserId(Integer userId) {
        User user = userMapper.findByUserId(userId);
        //查询的时候，不能把密码告诉别人
        user.setPwd("");
        return user;
    }
}
