package com.exemple.escola_projeto.service;

import com.exemple.escola_projeto.dto.UsuarioDTO;
import com.exemple.escola_projeto.model.Usuario;
import com.exemple.escola_projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException; // Importar
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Importar
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO autenticar(String login, String senhaRecebida) {
        // 1. Buscar o usuário pelo login
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o login: " + login));

        // 2. Comparar a senha recebida com a senha hasheada do banco
        if (passwordEncoder.matches(senhaRecebida, usuario.getSenha())) {
            // Senhas coincidem, autenticação bem-sucedida
            // Retornar um DTO para não expor a senha ou outros dados sensíveis da entidade
            return new UsuarioDTO(usuario.getId(), usuario.getLogin());
        } else {
            // Senhas não coincidem
            throw new BadCredentialsException("Senha inválida");
        }
    }
}
