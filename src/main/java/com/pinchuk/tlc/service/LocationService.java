package com.pinchuk.tlc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pinchuk.tlc.exception.ResourceNotFoundException;
import com.pinchuk.tlc.domain.Location;
import com.pinchuk.tlc.repository.LocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {
	
	private final LocationRepository locationRepository;
	
	public Location create(Location location) {
		location = locationRepository.save(location);
		return location;
	}
	
	public Location getById(Long locationId) {
		Optional<Location> location = locationRepository.findById(locationId);
		if (!location.isPresent()) {
			throw new ResourceNotFoundException();
		}
		return location.get();
		
	}
	
	public void update(Location location) {
		Location oldLocation = getById(location.getId());
		if (location.getName() != null){
			oldLocation.setName(location.getName());
		}
		if (location.getHead() != null){
			oldLocation.setHead(location.getHead());
		}
		if (location.getX() != null){
			oldLocation.setX(location.getX());
		}
		if (location.getY() != null){
			oldLocation.setY(location.getY());
		}
		locationRepository.save(oldLocation);
	}
	
	public void delete(Long locationId) {
		locationRepository.deleteById(locationId);
	}

}
