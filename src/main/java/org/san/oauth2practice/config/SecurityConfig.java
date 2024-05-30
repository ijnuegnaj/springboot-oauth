package org.san.oauth2practice.config;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated());
        http.oauth2Login(withDefaults());
        return http.build();
    }

}

// http.oauth2Login(//Customizer.withDefaults()
//     config -> {
//     config
//     .loginPage("/login/oauth2")
//                     .authorizationEndpoint(authorization -> authorization
//     .baseUri("/login/oauth2/authorization"))
//     .redirectionEndpoint(redirect -> redirect
//     .baseUri("/login/oauth2/redirect"));
//
//     }
//     );
