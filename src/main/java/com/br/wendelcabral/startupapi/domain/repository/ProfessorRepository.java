package com.br.wendelcabral.startupapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.wendelcabral.startupapi.domain.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
