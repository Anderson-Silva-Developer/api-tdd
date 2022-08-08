package com.anderson.apitdd;

import com.anderson.apitdd.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApiTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTddApplication.class, args);
    }

}
