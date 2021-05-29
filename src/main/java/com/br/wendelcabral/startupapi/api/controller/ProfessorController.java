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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.wendelcabral.startupapi.domain.exception.EntidadeNaoEncontrataExeption;
import com.br.wendelcabral.startupapi.domain.model.Professor;
import com.br.wendelcabral.startupapi.domain.repository.ProfessorRepository;
import com.br.wendelcabral.startupapi.domain.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private ProfessorService professorService;

	@PostMapping
	public ResponseEntity<Professor> cadastrar(@RequestBody Professor professor) {
		professor = professorService.salvar(professor);
		return ResponseEntity.status(HttpStatus.CREATED).body(professor);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<Professor> listar() {
		return professorRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		try {
			Professor professor = professorService.buscar(id);
			return ResponseEntity.status(HttpStatus.OK).body(professor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			professorService.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Professor professor) {
		try {
			Professor professorAtual = professorService.buscar(id);
			if (professorAtual != null) {
				BeanUtils.copyProperties(professor, professorAtual, "id");
				professor = professorService.salvar(professorAtual);
				return ResponseEntity.status(HttpStatus.OK).body(professor);
			}
		} catch (EntidadeNaoEncontrataExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
