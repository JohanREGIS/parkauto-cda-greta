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

import com.parkauto.rest.entity.LigneCmd;
import com.parkauto.rest.service.LigneCmdService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class LigneCmdController {

	@Autowired
	LigneCmdService ligneCmdService;
	
	@PostMapping("/ligneCmds")
	public LigneCmd createLigneCmd(@Validated @RequestBody(required = false) LigneCmd ligneCmd) {
		return ligneCmdService.saveLigneCmd(ligneCmd);
	}
	
	@GetMapping("/ligneCmds")
	public List<LigneCmd> getAllLigneCmds(@Validated @RequestBody(required = false) LigneCmd ligneCmd) {
		return ligneCmdService.getLigneCmds();
	}
	
	@GetMapping("/ligneCmds/{idLigneCmd}")
	public ResponseEntity findLigneCmdById(@PathVariable(name = "idLigneCmd") Long idLigneCmd) {
		if(idLigneCmd == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve ligneCmd with null ID");
		}
		
		LigneCmd ligneCmd = ligneCmdService.getLigneCmdById(idLigneCmd);
		
		if(ligneCmd == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(ligneCmd);
	}
	
	@DeleteMapping("/ligneCmds/{id}")
	public ResponseEntity<LigneCmd> deleteLigneCmd(@PathVariable(name = "id") Long idLigneCmd) {
		
		LigneCmd ligneCmd = ligneCmdService.getLigneCmdById(idLigneCmd);
		
		if(ligneCmd == null) {
			return ResponseEntity.notFound().build();
		}
		
		ligneCmdService.deleteLigneCmd(ligneCmd);
		
		return ResponseEntity.ok().body(ligneCmd);
	}
}
