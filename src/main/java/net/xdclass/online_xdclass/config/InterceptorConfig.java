package net.xdclass.online_xdclass.config;
import net.xdclass.online_xdclass.interceptor.CorsInterceptor;
import net.xdclass.online_xdclass.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @description:拦截器配置，
 * 不用权限可以访问url  /api/v1/pub
 * 要用权限才可以访问url /api/v1/pri
 * @author: Felix_XHF
 * @create:2021-07-30 10:43
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /*
    * @Description: 注入自己写的拦截器
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    /*
    * @Description: 添加拦截器
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截全部路径，这个跨域的需要放到最上面
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        //拦截全部
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}