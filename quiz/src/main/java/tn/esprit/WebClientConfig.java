package tn.esprit;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {


        @Bean
        @LoadBalanced
        public WebClient.Builder webClient() {
            return WebClient.builder();
        }

    }

