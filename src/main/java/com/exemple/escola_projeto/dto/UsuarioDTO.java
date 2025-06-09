package com.exemple.escola_projeto.dto;

public class UsuarioDTO {
    private Long id;
    private String login;

    // Construtor padrão (opcional, mas boa prática se você tiver outros construtores)
    public UsuarioDTO() {
        
    }

    // Construtor que recebe os dados da entidade Usuario (útil para mapeamento)
    public UsuarioDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
