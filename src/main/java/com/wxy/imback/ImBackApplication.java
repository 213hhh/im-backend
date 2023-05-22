package com.wxy.imback;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxy.imback.mapper")
public class ImBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImBackApplication.class, args);
    }

}
