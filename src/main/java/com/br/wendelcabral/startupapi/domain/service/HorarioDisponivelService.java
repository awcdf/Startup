package com.br.wendelcabral.startupapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.wendelcabral.startupapi.domain.exception.EntidadeNaoEncontrataExeption;
import com.br.wendelcabral.startupapi.domain.model.HorarioDisponivel;
import com.br.wendelcabral.startupapi.domain.model.Professor;
import com.br.wendelcabral.startupapi.domain.repository.HorarioDisponivelRepository;

@Service
public class HorarioDisponivelService {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private HorarioDisponivelRepository horarioDisponiveisRepository;

	public HorarioDisponivel cadastrar(HorarioDisponivel horariodiDisponiveis) {
		try {
			Professor professor = professorService.buscar(horariodiDisponiveis.getProfessor().getId());
			if (professor != null) {
				return horarioDisponiveisRepository.save(horariodiDisponiveis);
			}
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontrataExeption(String.format("Não existe cadastro de professor com o codigo %d",
					horariodiDisponiveis.getProfessor().getId()));
		}
		return null;
	}

	public void remover(Long id) {
		try {
			horarioDisponiveisRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrataExeption(
					String.format("Não existe cadastro de horario com o codigo %d", id));
		}
	}

	public HorarioDisponivel buscar(Long id) {
		return horarioDisponiveisRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontrataExeption(String.format("Nao existe horario cadastro com esse %id", id)));

	}

}
