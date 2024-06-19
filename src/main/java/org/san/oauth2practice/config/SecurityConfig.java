package org.san.oauth2practice.config;

import org.san.oauth2practice.service.CustomAuthExceptionHandler;
import org.san.oauth2practice.service.CustomOAuth2SuccessHandler;
import org.san.oauth2practice.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CustomAuthExceptionHandler customAuthExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated());
        http.oauth2Login(config -> config
            .successHandler(customOAuth2SuccessHandler)
            .failureHandler(customAuthExceptionHandler)
            .userInfoEndpoint(endpointConfig -> endpointConfig
                .userService(customOAuth2UserService)));

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
