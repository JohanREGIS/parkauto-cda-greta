package com.parkauto.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkauto.rest.entity.Commande;
import com.parkauto.rest.service.CommandeService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class CommandeController {

	@Autowired
	CommandeService commandeService;
	
	@PostMapping("/commandes")
	public Commande createCommande(@Validated @RequestBody(required = false) Commande commande) {
		return commandeService.saveCommande(commande);
	}
	
	@GetMapping("/commandes")
	public List<Commande> getAllCommandes(@Validated @RequestBody(required = false) Commande commande) {
		return commandeService.getCommandes();
	}
	
	@GetMapping("/commandes/{idCommande}")
	public ResponseEntity findCommandeById(@PathVariable(name = "idCommande") Long idCommande) {
		if(idCommande == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve commande with null ID");
		}
		
		Commande commande = commandeService.getCommandeById(idCommande);
		
		if(commande == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(commande);
	}
	
	@DeleteMapping("/commandes/{id}")
	public ResponseEntity<Commande> deleteCommande(@PathVariable(name = "id") Long idCommande) {
		
		Commande commande = commandeService.getCommandeById(idCommande);
		
		if(commande == null) {
			return ResponseEntity.notFound().build();
		}
		
		commandeService.deleteCommande(commande);
		
		return ResponseEntity.ok().body(commande);
	}
}
