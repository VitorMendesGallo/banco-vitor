package com.exemple.escola_projeto.dto;

public class LoginRequestDTO {
    private String login;

    private String senha;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}