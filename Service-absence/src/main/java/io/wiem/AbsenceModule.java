package io.wiem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AbsenceModule {
    public static void main(String[] args) {
        SpringApplication.run(AbsenceModule.class, args);
    }
}