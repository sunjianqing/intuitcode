package com.jianqing.intuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by jianqingsun on 12/12/17.
 */
@SpringBootApplication
@ComponentScan("com.jianqing.intuit")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}