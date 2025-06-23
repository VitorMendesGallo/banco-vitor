package com.exemple.escola_projeto.controller;

import com.exemple.escola_projeto.dto.LoginRequestDTO;
import com.exemple.escola_projeto.dto.UsuarioDTO;
import com.exemple.escola_projeto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://novohorizonteteresopolis.com.br")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping({ "/login" })
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            UsuarioDTO usuarioAutenticado = this.authService.autenticar(loginRequest
                    .getLogin(),
                    loginRequest
                            .getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado durante a autentica" + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar a solicitade login.");
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginStatus() {
        return ResponseEntity.ok("Login endpoint disponível. Use POST para autenticação.");
    }
}
