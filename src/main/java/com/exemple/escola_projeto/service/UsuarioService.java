package com.exemple.escola_projeto.service;

import com.exemple.escola_projeto.dto.UsuarioDTO;
import com.exemple.escola_projeto.model.Usuario;
import com.exemple.escola_projeto.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return (List<UsuarioDTO>) usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getLogin()))
                .collect(Collectors.toList());
    }
}
