package com.pinchuk.tlc.service.converter;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.pinchuk.tlc.domain.Location;
import com.pinchuk.tlc.domain.Route;
import com.pinchuk.tlc.domain.dto.RouteDto;
import com.pinchuk.tlc.service.LocationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DtoToRouteConverter {
	
	private final LocationService locationService;
	
	public Route convert(RouteDto routeDto) {
		Location[] locations = null;
		if (routeDto.getLocations() != null) {
			locations = new Location[routeDto.getLocations().length];
			for (int i = 0; i < routeDto.getLocations().length; i++) {
				locations[i] = locationService.getById(routeDto.getLocations()[i]);
			}
		}
		return Route.builder()
				.name(routeDto.getName())
				.head(routeDto.getHead() == null ? null : locationService.getById(routeDto.getHead()))
				.locations(locations == null ? null : Arrays.asList(locations))
				.build();
	}

}
