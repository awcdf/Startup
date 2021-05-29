package com.br.wendelcabral.startupapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.wendelcabral.startupapi.domain.exception.EntidadeNaoEncontrataExeption;
import com.br.wendelcabral.startupapi.domain.model.HorarioDisponivel;
import com.br.wendelcabral.startupapi.domain.repository.HorarioDisponivelRepository;
import com.br.wendelcabral.startupapi.domain.service.HorarioDisponivelService;

@RestController
@RequestMapping("/horario")
public class HorarioController {

	@Autowired
	private HorarioDisponivelRepository horarioDisponivelRepository;

	@Autowired
	private HorarioDisponivelService horarioService;

	@PostMapping()
	public ResponseEntity<?> cadastrar(@RequestBody HorarioDisponivel horarios) {
		try {
			horarioService.cadastrar(horarios);
			return ResponseEntity.status(HttpStatus.CREATED).body(horarios);
		} catch (EntidadeNaoEncontrataExeption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping()
	public List<HorarioDisponivel> listarHorarios() {
		return horarioDisponivelRepository.findAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			horarioService.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntidadeNaoEncontrataExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/disponivel")
	public List<HorarioDisponivel> listarDisponiveis() {
		return horarioDisponivelRepository.listarDisponiveis();
	}

	@GetMapping("/porhorario")
	public List<HorarioDisponivel> buscarPorHorario(String horario) {
		return horarioDisponivelRepository.porHorario(horario);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody HorarioDisponivel horariosDisponiveis) {
		try {
			HorarioDisponivel horarioDisponiveisAtual = horarioService.buscar(id);
			if (horarioDisponiveisAtual != null) {
				BeanUtils.copyProperties(horariosDisponiveis, horarioDisponiveisAtual, "id");
				horariosDisponiveis = horarioService.cadastrar(horarioDisponiveisAtual);
				return ResponseEntity.status(HttpStatus.OK).body(horariosDisponiveis);
			}
		} catch (EntidadeNaoEncontrataExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}

}
