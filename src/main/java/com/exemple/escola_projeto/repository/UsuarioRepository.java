package com.exemple.escola_projeto.repository;

import com.exemple.escola_projeto.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String paramString);
}
