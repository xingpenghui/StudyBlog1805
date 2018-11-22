package com.qfedu.studyblog1805;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.qfedu.studyblog1805.mapper") //标记Mybatis的接口
public class Studyblog1805Application {

    public static void main(String[] args) {
        SpringApplication.run(Studyblog1805Application.class, args);
    }
}
