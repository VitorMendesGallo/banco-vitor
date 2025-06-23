package com.exemple.escola_projeto.controller;

import com.exemple.escola_projeto.dto.UsuarioDTO;
import com.exemple.escola_projeto.service.AuthService;
import com.exemple.escola_projeto.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://novohorizonteteresopolis.com.br")
@RestController
@RequestMapping({ "/usuarios" })
public class UsuarioController {
    private final UsuarioService usuarioService;

    private final AuthService authService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getTodosUsuarios() {
        List<UsuarioDTO> usuariosDTO = this.usuarioService.listarTodos();
        return ResponseEntity.ok(usuariosDTO);
    }
}
