package com.br.wendelcabral.startupapi.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private int idade;
	private String areaEnsino;
	private String descricao;
	private double valorAula;

	@JsonIgnore
	@OneToMany(mappedBy = "professor")
	private List<HorarioDisponivel> horarioDisponivel = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getAreaEnsino() {
		return areaEnsino;
	}

	public void setAreaEnsino(String areaEnsino) {
		this.areaEnsino = areaEnsino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorAula() {
		return valorAula;
	}

	public void setValorAula(double valorAula) {
		this.valorAula = valorAula;
	}

	public List<HorarioDisponivel> getHorarioDisponivel() {
		return horarioDisponivel;
	}

	public void setHorarioDisponivel(List<HorarioDisponivel> horarioDisponivel) {
		this.horarioDisponivel = horarioDisponivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
