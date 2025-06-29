package com.exemple.escola_projeto.service;

import com.exemple.escola_projeto.dto.UsuarioDTO;
import com.exemple.escola_projeto.model.Usuario;
import com.exemple.escola_projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO autenticar(String login, String senhaRecebida) {
        Usuario usuario = (Usuario) this.usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usunencontrado com o login: " + login));
        if (this.passwordEncoder.matches(senhaRecebida, usuario.getSenha()))
            return new UsuarioDTO(usuario.getId(), usuario.getLogin());
        throw new BadCredentialsException("Senha invalida");
    }
}
