package com.exemple.escola_projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                                .antMatchers(HttpMethod.GET, "/alunos", "/maes",
                                                                "/pais", "/observacoes", "/usuarios")
                                                .authenticated()
                                                .antMatchers(HttpMethod.DELETE, "/alunos", "/maes",
                                                                "/pais", "/observacoes", "/usuarios")
                                                .authenticated()
                                                .antMatchers(HttpMethod.PUT, "/alunos", "/maes", "/pais",
                                                                "/observacoes", "/usuarios")
                                                .authenticated()
                                                .antMatchers(HttpMethod.POST, "/usuarios")
                                                .authenticated()
                                                .anyRequest().permitAll())
                                .httpBasic(Customizer.withDefaults());
                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }
}
