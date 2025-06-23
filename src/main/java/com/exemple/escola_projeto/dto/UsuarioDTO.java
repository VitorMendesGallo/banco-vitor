package com.exemple.escola_projeto.dto;

public class UsuarioDTO {
    private Long id;

    private String login;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
