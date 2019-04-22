package com.pinchuk.tlc.service.converter;

import org.springframework.stereotype.Component;

import com.pinchuk.tlc.domain.Route;
import com.pinchuk.tlc.domain.dto.RouteDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RouteToDtoConverter {
	
	public RouteDto convert(Route route) {
		Long[] locationIds = new Long[route.getLocations().size()];
		for (int i = 0; i < route.getLocations().size(); i++) {
			locationIds[i] = route.getLocations().get(i).getId();
		}
		
		return RouteDto.builder().id(route.getId()).name(route.getName())
				.head(route.getHead().getId()).locations(locationIds).build();
	}

}
