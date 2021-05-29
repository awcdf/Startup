package com.br.wendelcabral.startupapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.wendelcabral.startupapi.domain.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
	

}
