package net.xdclass.online_xdclass.controller;

import com.google.code.kaptcha.Producer;
import net.xdclass.online_xdclass.utils.CommonUtils;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 * @description:
 * @author: Felix_XHF
 * @create:2021-08-09 19:03
 */
@RestController
@RequestMapping("/api/v1/captcha")
public class CaptchaController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //根据@Qualifier的名称进行注入
    @Autowired
    private Producer captchaProducer;

    @GetMapping("get_captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        //获取随机的验证码
        String captchaText = captchaProducer.createText();

        //先获取用户的key信息
        String key = getCaptchaKey(request);

        //将获得的验证码存到redis中，10分钟过期
        redisTemplate.opsForValue().set(key,captchaText,10, TimeUnit.MINUTES);

        //文字转图片
        BufferedImage image = captchaProducer.createImage(captchaText);

        //拿到输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出到网页
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * @Description: 获取 存放到redis服务器中的 key
    * @Author: Mr.Felix
    * @Time: 2021/8/9
    **/
    private String getCaptchaKey(HttpServletRequest request){
        //获取用户的ip信息
        String ip = CommonUtils.getIpAddr(request);
        //获取浏览器的请求头，方便更好的标记用户
        String header = request.getHeader("User-Agent");
        //拼接ip+请求头生成对应的验证码
        String key = "user-service:captcha:" + CommonUtils.MD5(ip+header);
        return key;
    }



    /*
    * @Description: 发送验证码
    * @Author: Mr.Felix
    * @Time: 2021/8/9
    **/
    @GetMapping("send_code")
    public JsonData sendCode(@RequestParam(value = "to",required = true) String to ,
                             @RequestParam(value = "captcha" ,required = true)String captcha,
                             HttpServletRequest request){
        String key = getCaptchaKey(request);
        String cacheCaptcha = redisTemplate.opsForValue().get(key);
        if (captcha != null && cacheCaptcha != null && cacheCaptcha.equalsIgnoreCase(captcha)){
            redisTemplate.delete(key);
            //TODO:发送验证码给前台
            return  JsonData.buildSuccess();
        }else{
            return JsonData.buildError("验证码错误");
        }
    }
}
