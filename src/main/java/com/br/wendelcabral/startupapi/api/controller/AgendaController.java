package com.br.wendelcabral.startupapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.wendelcabral.startupapi.domain.exception.EntidadeNaoEncontrataExeption;
import com.br.wendelcabral.startupapi.domain.model.Agenda;
import com.br.wendelcabral.startupapi.domain.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Agenda> listar() {
		return agendaService.listar();
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Agenda agenda) {
		return ResponseEntity.status(HttpStatus.OK).body(agendaService.cadastrar(agenda));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Agenda agenda) {
		try {
			Agenda agendaAtual = agendaService.buscar(id);
			if (agendaAtual != null) {
				BeanUtils.copyProperties(agenda, agendaAtual, "id");
				agenda = agendaService.cadastrar(agendaAtual);
				return ResponseEntity.status(HttpStatus.OK).body(agenda);
			}
		} catch (EntidadeNaoEncontrataExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}

}
