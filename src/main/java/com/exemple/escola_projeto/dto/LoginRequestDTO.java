package com.exemple.escola_projeto.dto;

public class LoginRequestDTO {
    private String login;
    private String senha;

    // Construtor padrão
    public LoginRequestDTO() {
    }

    // Construtor com campos
    public LoginRequestDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // Getters
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    // Setters (geralmente necessários para o framework desserializar o JSON)
    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
