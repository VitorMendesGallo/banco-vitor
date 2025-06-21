package com.exemple.escola_projeto.config;

import com.exemple.escola_projeto.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity // Habilita a configuração de segurança web do Spring
public class SecurityConfig {
        @Autowired
        private CustomUserDetailsService customUserDetailsService;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Usando AntPathRequestMatcher para especificar o método POST
                        .requestMatchers(new org.springframework.security.web.util.matcher.AntPathRequestMatcher(
                                "/login", "POST"))
                        .permitAll()
                        .requestMatchers(new org.springframework.security.web.util.matcher.AntPathRequestMatcher(
                                "/alunos", "POST"))
                        .permitAll()
                        .requestMatchers(new org.springframework.security.web.util.matcher.AntPathRequestMatcher(
                                "/maes", "POST"))
                        .permitAll()
                        .requestMatchers(new org.springframework.security.web.util.matcher.AntPathRequestMatcher(
                                "/pais", "POST"))
                        .permitAll()
                        .requestMatchers(new org.springframework.security.web.util.matcher.AntPathRequestMatcher(
                                "/observacoes", "POST"))
                        .permitAll()
                        .anyRequest().authenticated())
                
                                .httpBasic(Customizer.withDefaults());
                return http.build();
        }


        @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

        @Bean
        public CorsFilter corsFilter() {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowCredentials(true);
                config.addAllowedOriginPattern("*"); // Libera todos os origins
                config.addAllowedHeader("*");
                config.addAllowedMethod("*");

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);
                return new CorsFilter(source);
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return customUserDetailsService;
        }

}