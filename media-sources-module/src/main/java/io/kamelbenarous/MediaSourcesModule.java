package io.kamelbenarous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MediaSourcesModule {
    public static void main(String[] args) {
        SpringApplication.run(MediaSourcesModule.class, args);
    }
}