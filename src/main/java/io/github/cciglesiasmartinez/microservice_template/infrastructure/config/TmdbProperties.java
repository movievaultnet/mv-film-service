package io.github.cciglesiasmartinez.microservice_template.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "tmdb")
public record TmdbProperties(
        @DefaultValue("https://api.themoviedb.org/3") String baseUrl,
        String token
) {
}