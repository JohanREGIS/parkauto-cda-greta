package com.parkauto.rest.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IDCLIENT")
	private Long id;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PRENOM")
	private String prenom;
	
	@Column(name="ADRESSE")
	private String adresse;
	
	@Column(name="CP")
	private String cp;
	
	@Column(name="VILLE")
	private String ville;
	
	@Column(name="PAYS")
	private String pays;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="vehicule_id")
	private Vehicule vehicule;
	
	@OneToMany(mappedBy="client")
	private List<Commande> commandeList;
	
	@ManyToMany(mappedBy="clientList")
	private List<Location> locationList;

	public Client() {
		super();
	}

	public Client(Long id, String nom, String prenom, String adresse, String cp, String ville, String pays,
			Vehicule vehicule) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.vehicule = vehicule;
	}
	
	public Client(Long id, String nom, String prenom, String adresse, String cp, String ville, String pays,
			Vehicule vehicule, List<Commande> commandeList) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.vehicule = vehicule;
		this.commandeList = commandeList;
	}

	public Client(Long id, String nom, String prenom, String adresse, String cp, String ville, String pays,
			Vehicule vehicule, List<Commande> commandeList, List<Location> locationList) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.vehicule = vehicule;
		this.commandeList = commandeList;
		this.locationList = locationList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public List<Commande> getCommandeList() {
		return commandeList;
	}

	public void setCommandeList(List<Commande> commandeList) {
		this.commandeList = commandeList;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}
	
	
	
}
