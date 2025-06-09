package com.exemple.escola_projeto.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.exemple.escola_projeto.model.Usuario;
import com.exemple.escola_projeto.repository.UsuarioRepository;
import com.exemple.escola_projeto.dto.UsuarioDTO;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarTodos() { // Agora retorna List<UsuarioDTO>
        List<Usuario> usuarios = usuarioRepository.findAll();
        // Mapear cada Usuario para um UsuarioDTO
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getLogin()))
                .collect(Collectors.toList());
    }
}
