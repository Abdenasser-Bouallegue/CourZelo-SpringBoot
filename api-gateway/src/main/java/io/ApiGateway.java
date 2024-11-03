package io;

import jakarta.ws.rs.HttpMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableDiscoveryClient
@SpringBootApplication
@CrossOrigin("*")
public class ApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("quiz-service",
                        r -> r.path("/quiz/all")
                                .and().method(HttpMethod.GET)
                                .uri("lb://quiz-service/quiz/all")
                )
                .route(
                        "quiz-service",
                        r -> r.path("/quiz/get/{quizId}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://quiz-service/quiz/get")
                )
                .route(
                        "quiz-service",
                        r -> r.path("/quiz/add")
                                .and().method(HttpMethod.POST)
                                .uri("lb://quiz-service/quiz/add")
                )
                .route(
                        "quiz-service",
                        r -> r.path("/quiz/update/{quizId}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://quiz-service/quiz/update")
                )
                .route(
                        "quiz-service",
                        r -> r.path("/quiz/delete/{quizId}")
                                .and().method(HttpMethod.DELETE)
                                .uri("lb://quiz-service/quiz/delete")
                )
                .build();
    }
}