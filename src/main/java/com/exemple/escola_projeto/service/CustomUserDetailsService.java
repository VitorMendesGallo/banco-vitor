package com.exemple.escola_projeto.service;

import com.exemple.escola_projeto.model.Usuario;
import com.exemple.escola_projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User; // Importar User do Spring Security
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList; // Para lista de authorities vazia, por enquanto

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // No nosso caso, o "username" é o campo "login" da entidade Usuario
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o login: " + username));

        // Criar um objeto UserDetails do Spring Security
        // Por enquanto, não estamos lidando com papéis/autoridades (roles/authorities),
        // então passamos uma lista vazia. No futuro, você pode adicionar papéis aqui.
        return new User(
                usuario.getLogin(),
                usuario.getSenha(), // A senha JÁ DEVE ESTAR HASHEADA no banco
                new ArrayList<>() // Lista de authorities (papéis)
        );
    }
}
