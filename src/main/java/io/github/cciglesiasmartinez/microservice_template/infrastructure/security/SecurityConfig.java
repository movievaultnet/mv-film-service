package io.github.cciglesiasmartinez.microservice_template.infrastructure.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("roles");
        authoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }

    // TODO: Very weak approach. Consider a better strategy later (RSA).
    @Bean
    JwtDecoder jwtDecoder(@Value("${jwt.secret}") String secret) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        SecretKey key = new SecretKeySpec(
                decodedKey,
                "HmacSHA256"
        );
        return NimbusJwtDecoder.withSecretKey(key).build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Unauthorized\"}"); // TODO: Fix this mess.
                        })
                ) // Handle authentication == null on requests (controller level)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/actuator/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.POST).permitAll()
//                        .anyRequest().permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.jwt(jwt ->
                                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );
        return http.build();
    }

}
