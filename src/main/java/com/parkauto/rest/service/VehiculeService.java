package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.Vehicule;
import com.parkauto.rest.repository.IVehiculeRepository;

@Service
public class VehiculeService {
	
	@Autowired
	IVehiculeRepository vehiculeRepository;
	
	// Liste des vehicules
	public List<Vehicule> getVehicules() {
		return vehiculeRepository.findAll();
	}
	
	//Save a vehicule
	public Vehicule saveVehicule(Vehicule vehicule) {
		return vehiculeRepository.save(vehicule);
	}
	
	//Get a vehicule
	public Vehicule getVehiculeById(Long idvehi) {
		return vehiculeRepository.findById(idvehi).get();
	}
	
	//Delete a vehicule
	public void deleteVehicule(Vehicule vehicule) {
		vehiculeRepository.delete(vehicule);
	}
}
