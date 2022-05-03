package br.com.cerc.holerite.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cargos")
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private double pagamentoHora;

	@OneToMany(mappedBy = "cargo")
	@JsonIgnoreProperties("funcionario")
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public Cargo() {
		
	}
	
	public Cargo(String nome, double pagamentoHora) {
		this.nome = nome;
		this.pagamentoHora = pagamentoHora;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPagamentoHora() {
		return pagamentoHora;
	}
	public void setPagamentoHora(double pagamentoHora) {
		this.pagamentoHora = pagamentoHora;
	}
	
	
}
