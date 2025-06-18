package com.exemple.escola_projeto;

import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EscolaProjetoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscolaProjetoApplication.class, args);
    }

    // @Bean
    // public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder) {
    //     return args -> {
    //         String senhaOriginal = "senha123"; // A senha que você quer hashear (ex: a do seu usuário 'admin')
    //         String senhaHasheada = passwordEncoder.encode(senhaOriginal);
    //         System.out.println("====================================================================");
    //         System.out.println("SENHA ORIGINAL: " + senhaOriginal);
    //         System.out.println("SENHA HASHEADA (BCrypt): " + senhaHasheada);
    //         System.out.println("COPIE A SENHA HASHEADA ACIMA PARA ATUALIZAR NO BANCO.");
    //         System.out.println("====================================================================");
    //     };
    // }
}
