package com.gft.controleStartersGFT.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany
	private List<Starter> starters;
	
	@ManyToOne
	private Tecnologia tecnologia;
	
	@ManyToOne
	private ScrumMaster scrumMaster;
	
	@OneToMany(mappedBy = "grupo")
	private List<Daily> dailys;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Starter> getStarters() {
		return starters;
	}

	public void setStarters(List<Starter> starters) {
		this.starters = starters;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public ScrumMaster getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(ScrumMaster scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

	public List<Daily> getDailys() {
		return dailys;
	}

	public void setDailys(List<Daily> dailys) {
		this.dailys = dailys;
	}

	@Override
	public String toString() {
		return "Grupo: " + id;
	}
	
}
