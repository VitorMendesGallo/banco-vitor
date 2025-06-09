package com.exemple.escola_projeto.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteCriptografia {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode("123456789bd");

        System.out.println("Senha criptografada: " + senhaCriptografada);
    }
}
