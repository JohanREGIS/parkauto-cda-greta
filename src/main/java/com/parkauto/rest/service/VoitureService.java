package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.Voiture;
import com.parkauto.rest.repository.IVoitureRepository;

@Service
public class VoitureService {
	
	@Autowired
	IVoitureRepository voitureRepository;
	
	// Liste des voitures
	public List<Voiture> getVoitures() {
		return voitureRepository.findAll();
	}
	
	//Save a voiture
	public Voiture saveVoiture(Voiture voiture) {
		return voitureRepository.save(voiture);
	}
	
	//Get a voiture
	public Voiture getVoitureById(Long idvoi) {
		return voitureRepository.findById(idvoi).get();
	}
	
	//Update a voiture
	public Voiture updateVoiture(Long idVoiture, Voiture voiture) {
		
		Voiture voit = voitureRepository.findById(idVoiture).get();
		
		voit.setAnneeModel(voiture.getAnneeModel());
		voit.setCategorie(voiture.getCategorie());
		voit.setImmatriculation(voiture.getImmatriculation());

		voit.setMedia(voiture.getMedia());
		voit.setNbPorte(voiture.getNbPorte());
		voit.setPoidsTotal(voiture.getPoidsTotal());
		voit.setPrix(voiture.getPrix());
		voit.setPuissanceFiscale(voiture.getPuissanceFiscale());
		
		return voitureRepository.save(voit);
	}
	
	//Delete a voiture
	public void deleteVoiture(Voiture voiture) {
		voitureRepository.delete(voiture);
	}
	
	
}
