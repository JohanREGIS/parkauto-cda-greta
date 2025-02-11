package com.parkauto.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="voiture")
public class Voiture extends Vehicule {
	
	@Column(name="immat")
	private String immatriculation;
	@Column(name="pwfiscale")
	private String puissanceFiscale;
	@Column(name="categorie")
	private String categorie;
	@Column(name="nbporte")
	private int nbPorte;
	@Column(name="poidstotal")
	private int poidsTotal;
	@Column(name="media")
	private String media;
	
	public Voiture() {
		super();
	}
	public Voiture(Long matricule, int anneeModel, double prix) {
		super(matricule, anneeModel, prix);
	}
	public Voiture(String immatriculation, String puissanceFiscale, String categorie, int nbPorte, int poidsTotal,
			String media) {
		super();
		this.immatriculation = immatriculation;
		this.puissanceFiscale = puissanceFiscale;
		this.categorie = categorie;
		this.nbPorte = nbPorte;
		this.poidsTotal = poidsTotal;
		this.media = media;
	}
	
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getPuissanceFiscale() {
		return puissanceFiscale;
	}
	public void setPuissanceFiscale(String puissanceFiscale) {
		this.puissanceFiscale = puissanceFiscale;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public int getNbPorte() {
		return nbPorte;
	}
	public void setNbPorte(int nbPorte) {
		this.nbPorte = nbPorte;
	}
	public int getPoidsTotal() {
		return poidsTotal;
	}
	public void setPoidsTotal(int poidsTotal) {
		this.poidsTotal = poidsTotal;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
}
