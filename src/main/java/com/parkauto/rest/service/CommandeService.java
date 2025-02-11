package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.Commande;
import com.parkauto.rest.repository.ICommandeRepository;

@Service
public class CommandeService {

	@Autowired
	ICommandeRepository commandeRepository;
	
	// Liste des commandes
	public List<Commande> getCommandes() {
		return commandeRepository.findAll();
	}
	
	//Save a commande
	public Commande saveCommande(Commande commande) {
		return commandeRepository.save(commande);
	}
	
	//Get a commande
	public Commande getCommandeById(Long idcommande) {
		return commandeRepository.findById(idcommande).get();
	}
	
	//Delete a commande
	public void deleteCommande(Commande commande) {
		commandeRepository.delete(commande);
	}
}
