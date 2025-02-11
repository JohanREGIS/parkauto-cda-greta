package com.parkauto.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkauto.rest.entity.Voiture;
import com.parkauto.rest.service.VoitureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping
@CrossOrigin("*")
public class VoitureController {
	
	@Autowired
	VoitureService voitureService;
	
	@PostMapping("/voitures")
	public Voiture createVoiture(@Validated @RequestBody(required = false) Voiture voiture) {
		return voitureService.saveVoiture(voiture);
	}
	
	@GetMapping("/voitures")
	public List<Voiture> getAllVoitures(@Validated @RequestBody(required = false) Voiture voiture) {
		return voitureService.getVoitures();
	}
	
	@GetMapping("/voitures/{idVoiture}")
	public ResponseEntity findVoitureById(@PathVariable(name = "idVoiture") Long idVoiture) {
		if(idVoiture == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve voiture with null ID");
		}
		
		Voiture voit = voitureService.getVoitureById(idVoiture);
		
		if(voit == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(voit);
	}
	
	@PutMapping("/voitures/{idVoiture}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable(name = "idVoiture") Long idVoiture, @RequestBody Voiture voiture){

        if(voiture == null) {
            return ResponseEntity.notFound().build();
        }
        
        Voiture voit = voitureService.updateVoiture(idVoiture, voiture);
        
        return ResponseEntity.ok().body(voit);
    }
	
	@DeleteMapping("/voitures/{idVoiture}")
	public ResponseEntity<Voiture> deleteVoiture(@PathVariable(name = "idVoiture") Long idVoiture) {
		
		Voiture voit = voitureService.getVoitureById(idVoiture);
		
		if(voit == null) {
			return ResponseEntity.notFound().build();
		}
		
		voitureService.deleteVoiture(voit);
		
		return ResponseEntity.ok().body(voit);
	}
}
