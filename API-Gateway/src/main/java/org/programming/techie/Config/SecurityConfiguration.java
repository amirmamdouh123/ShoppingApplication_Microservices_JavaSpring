package org.programming.techie.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf(t->t.disable());
        http.authorizeExchange(request-> request
//                .pathMatchers("/eureka/**").permitAll()
                .anyExchange().authenticated()) ;
        http.oauth2ResourceServer(j-> j.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
