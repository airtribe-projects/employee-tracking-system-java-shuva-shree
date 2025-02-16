package com.airtribe.EmployeeTrackingSystem.webSecurityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           return http.authorizeHttpRequests(auth ->

                auth
                        .requestMatchers("/employees/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/departments/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/projects/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers("/employees/search/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/api/departments/{id}/projects").hasAnyRole("MANAGER", "ADMIN")
                        .anyRequest().authenticated()
                        )
                        .oauth2Login(oAuth2Login ->
                        oAuth2Login.successHandler(oAuth2AuthenticationSuccessHandler())
                        ).build();





        }
    @Bean
    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler();
    }


}
