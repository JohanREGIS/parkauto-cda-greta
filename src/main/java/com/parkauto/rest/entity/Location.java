package com.parkauto.rest.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IDLOCATION")
	private Long id;
	
	@Column(name="DATELOCATION")
	private String dateLocation;
	
	@Column(name="DATEDEBUT")
	private String dateDebut;
	
	@Column(name="DATERETOUR")
	private String dateRetour;
	
	@ManyToMany
	@JoinTable(name="LOCATION_CLIENT")
	private List<Client> clientList;

	public Location() {
		super();
	}

	public Location(Long id, String dateLocation, String dateDebut, String dateRetour, List<Client> clientList) {
		super();
		this.id = id;
		this.dateLocation = dateLocation;
		this.dateDebut = dateDebut;
		this.dateRetour = dateRetour;
		this.clientList = clientList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateLocation() {
		return dateLocation;
	}

	public void setDateLocation(String dateLocation) {
		this.dateLocation = dateLocation;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(String dateRetour) {
		this.dateRetour = dateRetour;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	
}
