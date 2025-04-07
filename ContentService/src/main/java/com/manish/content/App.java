package com.manish.content;

import com.manish.common.Base;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        System.out.println(Base.isRunning());
        SpringApplication.run(App.class, args);
    }
}
