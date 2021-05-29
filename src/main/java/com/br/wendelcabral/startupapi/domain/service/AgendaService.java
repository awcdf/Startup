package com.br.wendelcabral.startupapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.wendelcabral.startupapi.domain.exception.EntidadeNaoEncontrataExeption;
import com.br.wendelcabral.startupapi.domain.model.Agenda;
import com.br.wendelcabral.startupapi.domain.repository.AgendaRepository;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;

	public Agenda cadastrar(Agenda agenda) {
			return agendaRepository.save(agenda);
	}

	public void remover(Long id) {
		agendaRepository.deleteById(id);
	}

	public Agenda buscar(Long id) {
		return agendaRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontrataExeption(String.format("Nao existe aula agenda com o id = %id", id)));
	}

	public List<Agenda> listar() {
		return agendaRepository.findAll();
	}
}
