package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.LigneCmd;
import com.parkauto.rest.repository.ILigneCmdRepository;

@Service
public class LigneCmdService {

	@Autowired
	ILigneCmdRepository ligneCmdRepository;
	
	// Liste des ligneCmds
	public List<LigneCmd> getLigneCmds() {
		return ligneCmdRepository.findAll();
	}
	
	//Save a ligneCmd
	public LigneCmd saveLigneCmd(LigneCmd ligneCmd) {
		return ligneCmdRepository.save(ligneCmd);
	}
	
	//Get a ligneCmd
	public LigneCmd getLigneCmdById(Long idlignecmd) {
		return ligneCmdRepository.findById(idlignecmd).get();
	}
	
	//Delete a ligneCmd
	public void deleteLigneCmd(LigneCmd ligneCmd) {
		ligneCmdRepository.delete(ligneCmd);
	}
}
