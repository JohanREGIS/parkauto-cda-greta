package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.Client;
import com.parkauto.rest.repository.IClientRepository;

@Service
public class ClientService {
	
	@Autowired
	IClientRepository clientRepository;
	
	// Liste des clients
	public List<Client> getClients() {
		return clientRepository.findAll();
	}
	
	//Save a client
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}
	
	//Get a client
	public Client getClientById(Long idclient) {
		return clientRepository.findById(idclient).get();
	}
	
	//Delete a client
	public void deleteClient(Client client) {
		clientRepository.delete(client);
	}
}
