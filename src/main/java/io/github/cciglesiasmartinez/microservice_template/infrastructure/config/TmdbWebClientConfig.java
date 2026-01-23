package io.github.cciglesiasmartinez.microservice_template.infrastructure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(TmdbProperties.class)
public class TmdbWebClientConfig {

    @Bean
    public WebClient tmdbWebClient(TmdbProperties properties) {
        WebClient.Builder builder = WebClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        if (properties.token() != null && !properties.token().isBlank()) {
            builder.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.token());
        }

        return builder.build();
    }
}