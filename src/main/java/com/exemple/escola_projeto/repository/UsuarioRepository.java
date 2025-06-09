package com.exemple.escola_projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemple.escola_projeto.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
