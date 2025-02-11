package com.parkauto.rest.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="COMMANDE")
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IDCMD")
	private Long id;
	
	@Column(name="QTE")
	private int qte;
	
	@Column(name="DATECMD")
	private String dateCmd;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PRIX")
	private double prix;
	
	@Column(name="PRIXTOTAL")
	private double prixTotal;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@OneToMany(mappedBy="commande")
	private List<LigneCmd> ligneCmdList;

	public Commande() {
		super();
	}

	public Commande(Long id, int qte, String dateCmd, String description, double prix, double prixTotal, Client client,
			List<LigneCmd> ligneCmdList) {
		super();
		this.id = id;
		this.qte = qte;
		this.dateCmd = dateCmd;
		this.description = description;
		this.prix = prix;
		this.prixTotal = prixTotal;
		this.client = client;
		this.ligneCmdList = ligneCmdList;
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

	public String getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(String dateCmd) {
		this.dateCmd = dateCmd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCmd> getLigneCmdList() {
		return ligneCmdList;
	}

	public void setLigneCmdList(List<LigneCmd> ligneCmdList) {
		this.ligneCmdList = ligneCmdList;
	}

}
