package com.exemple.escola_projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.escola_projeto.model.Mae;

public interface MaeRepository extends JpaRepository<Mae, Long> {
    List<Mae> findByNomeMaeContainingIgnoreCase(String nomeMae);

}
