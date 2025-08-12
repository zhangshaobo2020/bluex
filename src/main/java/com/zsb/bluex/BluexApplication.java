package com.zsb.bluex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.zsb.bluex.**.mapper"})
public class BluexApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluexApplication.class, args);
    }

}
