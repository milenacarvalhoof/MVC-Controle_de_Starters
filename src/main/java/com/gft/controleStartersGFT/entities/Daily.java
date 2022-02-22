package com.gft.controleStartersGFT.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Daily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Starter starter;
		
	@ManyToOne
	private Grupo grupo;
	
	@ManyToOne
	private Modulo modulo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	private String feito;

	private String fazendo;
	
	private String impedimentos;
	
	private int presenca;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getFeito() {
		return feito;
	}

	public void setFeito(String feito) {
		this.feito = feito;
	}

	public String getFazendo() {
		return fazendo;
	}

	public void setFazendo(String fazendo) {
		this.fazendo = fazendo;
	}

	public String getImpedimentos() {
		return impedimentos;
	}

	public void setImpedimentos(String impedimentos) {
		this.impedimentos = impedimentos;
	}

	public int getPresenca() {
		return presenca;
	}

	public void setPresenca(int presenca) {
		this.presenca = presenca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Starter getStarter() {
		return starter;
	}

	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	@Override
	public String toString() {
		return grupo + ", data: " + data;
	}
	
}
