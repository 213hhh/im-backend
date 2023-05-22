package com.wxy.imback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
@Slf4j
class ImBackApplicationTests {


    @Test
    void contextLoads() {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String test = date.format(new Date(1684682187179L * 1000));
        System.out.println(test);
    }



}
