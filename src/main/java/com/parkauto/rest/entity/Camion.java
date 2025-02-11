package com.parkauto.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="camion")
public class Camion extends Vehicule {
	
	@Column(name="hauteur")
	private String hauteur;
	
	public Camion() {
		super();
	}
	
	public Camion(String hauteur) {
		super();
		this.hauteur = hauteur;
	}
	
	public Camion(Long matricule, int anneeModel, double prix) {
		super(matricule, anneeModel, prix);
	}
	
	public String getHauteur() {
		return hauteur;
	}

	public void setHauteur(String hauteur) {
		this.hauteur = hauteur;
	}

	@Override
	public String demarrer() {
		return "BRRRRRR !";
	}
	
	@Override
	public String accelerer() {
		return "BROOUUMM !";
	}
	
}
