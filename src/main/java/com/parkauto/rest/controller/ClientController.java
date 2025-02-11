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

import com.parkauto.rest.entity.Client;
import com.parkauto.rest.service.ClientService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@PostMapping("/clients")
	public Client createClient(@Validated @RequestBody(required = false) Client client) {
		return clientService.saveClient(client);
	}
	
	@GetMapping("/clients")
	public List<Client> getAllClients(@Validated @RequestBody(required = false) Client client) {
		return clientService.getClients();
	}
	
	@GetMapping("/clients/{idClient}")
	public ResponseEntity findClientById(@PathVariable(name = "idClient") Long idClient) {
		if(idClient == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve client with null ID");
		}
		
		Client client = clientService.getClientById(idClient);
		
		if(client == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(client);
	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Client> deleteClient(@PathVariable(name = "id") Long idClient) {
		
		Client client = clientService.getClientById(idClient);
		
		if(client == null) {
			return ResponseEntity.notFound().build();
		}
		
		clientService.deleteClient(client);
		
		return ResponseEntity.ok().body(client);
	}
}
