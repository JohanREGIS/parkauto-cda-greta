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

import com.parkauto.rest.entity.Camion;
import com.parkauto.rest.service.CamionService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class CamionController {
	
	@Autowired
	CamionService camionService;
	
	@PostMapping("/camions")
	public Camion createCamion(@Validated @RequestBody(required = false) Camion camion) {
		return camionService.saveCamion(camion);
	}
	
	@GetMapping("/camions")
	public List<Camion> getAllCamions(@Validated @RequestBody(required = false) Camion camion) {
		return camionService.getCamions();
	}
	
	@GetMapping("/camions/{idCamion}")
	public ResponseEntity findCamionById(@PathVariable(name = "idCamion") Long idCamion) {
		if(idCamion == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve camion with null ID");
		}
		
		Camion cami = camionService.getCamionById(idCamion);
		
		if(cami == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(cami);
	}
	
	@DeleteMapping("/camions/{id}")
	public ResponseEntity<Camion> deleteCamion(@PathVariable(name = "id") Long idCamion) {
		
		Camion cami = camionService.getCamionById(idCamion);
		
		if(cami == null) {
			return ResponseEntity.notFound().build();
		}
		
		camionService.deleteCamion(cami);
		
		return ResponseEntity.ok().body(cami);
	}
}
