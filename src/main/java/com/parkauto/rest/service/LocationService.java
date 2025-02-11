package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.Location;
import com.parkauto.rest.repository.ILocationRepository;

@Service
public class LocationService {

	@Autowired
	ILocationRepository locationRepository;
	
	// Liste des locations
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}
	
	//Save a location
	public Location saveLocation(Location location) {
		return locationRepository.save(location);
	}
	
	//Get a location
	public Location getLocationById(Long idlocation) {
		return locationRepository.findById(idlocation).get();
	}
	
	//Delete a location
	public void deleteLocation(Location location) {
		locationRepository.delete(location);
	}
}
