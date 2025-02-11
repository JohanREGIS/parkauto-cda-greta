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

import com.parkauto.rest.entity.Vehicule;
import com.parkauto.rest.service.VehiculeService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class VehiculeController {

	@Autowired
	VehiculeService vehiculeService;
	
	@PostMapping("/vehicules")
	public Vehicule createVehicule(@Validated @RequestBody(required = false) Vehicule vehicule) {
		return vehiculeService.saveVehicule(vehicule);
	}
	
	@GetMapping("/vehicules")
	public List<Vehicule> getAllVehicules(@Validated @RequestBody(required = false) Vehicule vehicule) {
		return vehiculeService.getVehicules();
	}
	
	@GetMapping("/vehicules/{idVehicule}")
	public ResponseEntity findVehiculeById(@PathVariable(name = "idVehicule") Long idVehicule) {
		if(idVehicule == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve vehicule with null ID");
		}
		
		Vehicule vehi = vehiculeService.getVehiculeById(idVehicule);
		
		if(vehi == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(vehi);
	}
	
	@DeleteMapping("/vehicules/{id}")
	public ResponseEntity<Vehicule> deleteVehicule(@PathVariable(name = "id") Long idVehicule) {
		
		Vehicule vehi = vehiculeService.getVehiculeById(idVehicule);
		
		if(vehi == null) {
			return ResponseEntity.notFound().build();
		}
		
		vehiculeService.deleteVehicule(vehi);
		
		return ResponseEntity.ok().body(vehi);
	}
}
