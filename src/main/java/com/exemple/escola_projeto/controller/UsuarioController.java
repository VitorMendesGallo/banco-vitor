package com.exemple.escola_projeto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exemple.escola_projeto.service.UsuarioService;
import com.exemple.escola_projeto.dto.UsuarioDTO;
import com.exemple.escola_projeto.dto.LoginRequestDTO;
import com.exemple.escola_projeto.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthService authService;

    @Autowired // Adicionar Autowired no construtor se tiver mais de um serviço
    public UsuarioController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService; // Injetar AuthService
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getTodosUsuarios() { // Agora retorna List<UsuarioDTO>
        List<UsuarioDTO> usuariosDTO = usuarioService.listarTodos();
        return ResponseEntity.ok(usuariosDTO);
    }

    @PostMapping("/login") // Mapeado para POST /usuarios/login
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            UsuarioDTO usuarioAutenticado = authService.autenticar(
                    loginRequest.getLogin(),
                    loginRequest.getSenha()
            );
            // Se autenticado, retorna os dados do usuário (sem senha) e status OK
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            // Se usuário não encontrado ou senha inválida, retorna 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            // Para outros erros inesperados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao tentar autenticar.");
        }
    }

}
