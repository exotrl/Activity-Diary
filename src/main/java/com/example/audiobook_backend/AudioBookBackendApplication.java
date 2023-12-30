package com.example.audiobook_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.audiobook_backend.mapper")
public class AudioBookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AudioBookBackendApplication.class, args);
    }

}
