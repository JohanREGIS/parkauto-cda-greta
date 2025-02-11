package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.Camion;
import com.parkauto.rest.repository.ICamionRepository;

@Service
public class CamionService {

	@Autowired
	ICamionRepository camionRepository;
	
	// Liste des camions
	public List<Camion> getCamions() {
		return camionRepository.findAll();
	}
	
	//Save a camion
	public Camion saveCamion(Camion camion) {
		return camionRepository.save(camion);
	}
	
	//Get a camion
	public Camion getCamionById(Long idcam) {
		return camionRepository.findById(idcam).get();
	}
	
	//Delete a camion
	public void deleteCamion(Camion camion) {
		camionRepository.delete(camion);
	}
}
