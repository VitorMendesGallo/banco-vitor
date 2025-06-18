package com.exemple.escola_projeto.config;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// Para quando formos configurar um usuário em memória para teste (PASSO 4)
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // Habilita a configuração de segurança web do Spring
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilitar CSRF. Para APIs REST, especialmente aquelas que não usam cookies
                // para autenticação,
                // é comum desabilitar. Para um formulário de login tradicional com Thymeleaf,
                // você poderia manter.
                // Para testes iniciais, desabilitar simplifica.
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
                // Habilita a autenticação HTTP Basic.
                // Isso permitirá que, no futuro (PASSO 4), você possa se autenticar
                // enviando um header "Authorization" com usuário e senha.
                // Por enquanto, como /usuarios/senhas está "permitAll()", não será exigido para
                // ele.
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Este Bean será usado para criptografar as senhas quando você for cadastrar
    // e verificar usuários (PASSO 4). É bom já tê-lo aqui. [cite: 19]
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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

    // EXEMPLO para o PASSO 4: Como configurar um usuário em memória para testar o
    // login.
    // Você descomentaria isso e os imports relacionados quando chegar lá.
    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsService() {
     * UserDetails user = User.builder()
     * .username("admin") // Defina o nome de usuário
     * .password(passwordEncoder().encode("senha123")) // Use o passwordEncoder para
     * a senha
     * .roles("ADMIN") // Papel do usuário
     * .build();
     * return new InMemoryUserDetailsManager(user);
     * }
     */

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite requisições da sua aplicação React
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8081"));
        // Define os métodos HTTP permitidos (GET, POST, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Permite todos os cabeçalhos na requisição
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Permite o envio de credenciais (como cookies), se necessário
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuração de CORS a todos os caminhos da sua API ("/**")
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}