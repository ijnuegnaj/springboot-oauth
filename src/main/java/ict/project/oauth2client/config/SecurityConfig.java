package ict.project.oauth2client.config;

import lombok.RequiredArgsConstructor;
import ict.project.oauth2client.service.CustomAuthExceptionHandler;
import ict.project.oauth2client.service.CustomOAuth2SuccessHandler;
import ict.project.oauth2client.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                .anyRequest().permitAll());
        http.oauth2Login(config -> config
                .authorizationEndpoint(endpointConfig -> endpointConfig
                        .baseUri("/oauth2/authorize/custom"))
                .redirectionEndpoint(redirectConfig -> redirectConfig
                        .baseUri("/oauth2/redirect/custom"))

                .successHandler(customOAuth2SuccessHandler)
                .failureHandler(customAuthExceptionHandler)
                .userInfoEndpoint(endpointConfig -> endpointConfig
                        .userService(customOAuth2UserService)));

        return http.build();
    }
}
