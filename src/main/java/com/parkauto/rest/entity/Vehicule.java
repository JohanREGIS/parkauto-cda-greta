package com.parkauto.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="VEHICULE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IDVEHICULE")
	private Long matricule;
	
	@Column(name="ANNEEMODEL")
	private int anneeModel;
	
	@Column(name="PRIX")
	private double prix;
	
	@Column(name="imageVehicule")
	private String imageVehicule;
	
	@Column(name="descriptif")
	private String descriptif;
	
	public Vehicule() {
		super();
	}

	public Vehicule(Long matricule, int anneeModel, double prix) {
		super();
		this.matricule = matricule;
		this.anneeModel = anneeModel;
		this.prix = prix;
	}

	public Vehicule(Long matricule, int anneeModel, double prix, String imageVehicule, String descriptif) {
		super();
		this.matricule = matricule;
		this.anneeModel = anneeModel;
		this.prix = prix;
		this.imageVehicule = imageVehicule;
		this.descriptif = descriptif;
	}

	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public int getAnneeModel() {
		return anneeModel;
	}

	public void setAnneeModel(int anneeModel) {
		this.anneeModel = anneeModel;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public String getImageVehicule() {
		return imageVehicule;
	}

	public void setImageVehicule(String imageVehicule) {
		this.imageVehicule = imageVehicule;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	@Override
	public String toString() {
		return "Vehicule [matricule=" + matricule + ", anneeModel=" + anneeModel + ", prix=" + prix + "]";
	}

	public String demarrer() {
		return "Le véhicule a démarrer. Brouuumm !";
	}
	
	public String accelerer() {
		return "Le véhicule accelère. Vrouuumm !";
	}
}
