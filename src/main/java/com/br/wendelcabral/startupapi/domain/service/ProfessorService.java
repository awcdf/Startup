package com.br.wendelcabral.startupapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.wendelcabral.startupapi.domain.exception.EntidadeNaoEncontrataExeption;
import com.br.wendelcabral.startupapi.domain.model.Professor;
import com.br.wendelcabral.startupapi.domain.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public Professor salvar(Professor professor) {
		return professorRepository.save(professor);

	}

	public Professor buscar(Long id) {
		Professor professor = professorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrataExeption(
				String.format("Não existe cadastro de professor com o codigo %d", id)));

		return professor;

	}

	public void remover(Long id) {
		try {
			professorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrataExeption(
					String.format("Não existe cadastro de professor com o codigo %d", id));
		}
	}
}
