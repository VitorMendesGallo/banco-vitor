package com.exemple.escola_projeto.controller;

import com.exemple.escola_projeto.dto.LoginRequestDTO; // DTO que você criou para receber login/senha
import com.exemple.escola_projeto.dto.UsuarioDTO;      // DTO que o AuthService retorna em caso de sucesso
import com.exemple.escola_projeto.service.AuthService;   // Serviço que você criou com a lógica de autenticação

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController; // Importante: Usar @RestController diretamente

@RestController // Não usaremos @RequestMapping no nível da classe aqui,
                // para que o @PostMapping("/login") fique na raiz da aplicação.
public class AuthController {

    @Autowired
    private AuthService authService; // Injetar o serviço de autenticação

    @PostMapping("/login") // Este método responderá a requisições POST para http://localhost:8080/login
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            // Chama o serviço para autenticar
            UsuarioDTO usuarioAutenticado = authService.autenticar(
                    loginRequest.getLogin(),
                    loginRequest.getSenha()
            );

            // Se a autenticação for bem-sucedida, retorna os dados do usuário (sem a senha)
            // e um status HTTP 200 OK.
            return ResponseEntity.ok(usuarioAutenticado);

        } catch (UsernameNotFoundException e) {
            // Se o usuário não for encontrado, retorna HTTP 401 Unauthorized com a mensagem do erro.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (BadCredentialsException e) {
            // Se a senha estiver incorreta, retorna HTTP 401 Unauthorized com a mensagem do erro.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            // Para qualquer outro erro inesperado durante a tentativa de login.
            // É uma boa prática logar este erro no servidor.
            System.err.println("Erro inesperado durante a autenticação: " + e.getMessage());
            e.printStackTrace(); // Para vermos o stack trace no console durante o desenvolvimento
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar a solicitação de login.");
        }
    }
}