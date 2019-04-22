package com.pinchuk.tlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pinchuk.tlc.domain.Location;
import com.pinchuk.tlc.service.LocationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {
	
	private final LocationService locationService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Location createLocation(@RequestBody Location location) {
		return locationService.create(location);
	}
	
	@RequestMapping(value = "/{locationId}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Location getLocation(@PathVariable Long locationId) {
		return locationService.getById(locationId);
	}
	
	@RequestMapping(value = "/{locationId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateLocation(@PathVariable Long locationId, @RequestBody Location location) {
		location.setId(locationId);
		locationService.update(location);
	}
	
	@RequestMapping(value = "/{locationId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLocation(@PathVariable Long locationId) {
		locationService.delete(locationId);
	}

}
