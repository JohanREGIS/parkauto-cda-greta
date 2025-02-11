package com.parkauto.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="LIGNECMD")
public class LigneCmd {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IDLIGNECMD")
	private Long id;
	
	@Column(name="QTE")
	private int qte;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@Column(name="PRIX")
	private double prix;
	
	@ManyToOne
	@JoinColumn(name="commande_id")
	private Commande commande;

	public LigneCmd() {
		super();
	}

	public LigneCmd(Long id, int qte, String libelle, double prix, Commande commande) {
		super();
		this.id = id;
		this.qte = qte;
		this.libelle = libelle;
		this.prix = prix;
		this.commande = commande;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
}
