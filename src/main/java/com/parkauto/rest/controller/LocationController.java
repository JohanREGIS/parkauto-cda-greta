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

import com.parkauto.rest.entity.Location;
import com.parkauto.rest.service.LocationService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class LocationController {

	@Autowired
	LocationService locationService;
	
	@PostMapping("/locations")
	public Location createLocation(@Validated @RequestBody(required = false) Location location) {
		return locationService.saveLocation(location);
	}
	
	@GetMapping("/locations")
	public List<Location> getAllLocations(@Validated @RequestBody(required = false) Location location) {
		return locationService.getLocations();
	}
	
	@GetMapping("/locations/{idLocation}")
	public ResponseEntity findLocationById(@PathVariable(name = "idLocation") Long idLocation) {
		if(idLocation == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve location with null ID");
		}
		
		Location location = locationService.getLocationById(idLocation);
		
		if(location == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(location);
	}
	
	@DeleteMapping("/locations/{id}")
	public ResponseEntity<Location> deleteLocation(@PathVariable(name = "id") Long idLocation) {
		
		Location location = locationService.getLocationById(idLocation);
		
		if(location == null) {
			return ResponseEntity.notFound().build();
		}
		
		locationService.deleteLocation(location);
		
		return ResponseEntity.ok().body(location);
	}
}
