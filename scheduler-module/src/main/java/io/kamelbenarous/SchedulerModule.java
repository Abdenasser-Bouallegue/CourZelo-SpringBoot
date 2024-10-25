package io.kamelbenarous;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SchedulerModule {
    public static void main(String[] args) {
        SpringApplication.run(SchedulerModule.class, args);
    }
}