package io.kamelbenarous.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClient() {
        // Create a custom HttpClient without a timeout
        HttpClient httpClient = HttpClient.create();

        // Create a ReactorClientHttpConnector with the custom HttpClient
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        // Set up WebClient.Builder with the custom connector
        return WebClient.builder().clientConnector(connector);
    }
}
