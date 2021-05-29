package com.br.wendelcabral.startupapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.wendelcabral.startupapi.domain.model.HorarioDisponivel;

@Repository
public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Long>{
	
	@Query("from HorarioDisponivel where disponivel = true")
	List<HorarioDisponivel> listarDisponiveis();
	
	@Query("from HorarioDisponivel where horario like %:horario%")
	List<HorarioDisponivel> porHorario(String horario);

}
