package com.exemple.escola_projeto.service;

import com.exemple.escola_projeto.model.Usuario;
import com.exemple.escola_projeto.repository.UsuarioRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = (Usuario) this.usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usunencontrado com o login: " + username));
        return (UserDetails) new User(usuario
                .getLogin(),
                usuario
                        .getSenha(),
                new ArrayList());
    }
}
