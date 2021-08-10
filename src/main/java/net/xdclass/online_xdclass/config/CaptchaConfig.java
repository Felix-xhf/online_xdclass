package net.xdclass.online_xdclass.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
/*
* @Description: 验证码配置
* @Author: Mr.Felix
* @Time: 2021/8/6
**/
public class CaptchaConfig {
    @Bean
    @Qualifier("captchaProducer") //取一个名称
    /*
    * 有些场景下，仅仅靠@Autowired是不足以让Spring知道到底要注入哪个Bean
    * 所以需要给Bean一个标志
    * */
    public DefaultKaptcha kaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //验证码个数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        //字体间隔
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE,"8");
        //⼲扰线颜⾊

        //⼲扰实现类
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        //图⽚样式
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.WaterRipple");
        //⽂字来源
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
