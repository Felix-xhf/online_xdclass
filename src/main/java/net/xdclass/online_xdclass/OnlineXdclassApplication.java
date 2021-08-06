package net.xdclass.online_xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //管理事务
@SpringBootApplication
@MapperScan("net.xdclass.online_xdclass.mapper")
public class OnlineXdclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineXdclassApplication.class, args);
    }

}
