package com.pinchuk.tlc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pinchuk.tlc.exception.ResourceNotFoundException;
import com.pinchuk.tlc.domain.Location;
import com.pinchuk.tlc.domain.Route;
import com.pinchuk.tlc.domain.dto.DistanceDto;
import com.pinchuk.tlc.domain.dto.RouteDto;
import com.pinchuk.tlc.domain.dto.RoutePlanDto;
import com.pinchuk.tlc.repository.RouteRepository;
import com.pinchuk.tlc.service.converter.DtoToRouteConverter;
import com.pinchuk.tlc.service.converter.RouteToDtoConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteService {
	
	private final RouteRepository routeRepository;
	
	private final RouteToDtoConverter routeToDtoConverter;
	private final DtoToRouteConverter dtoToRouteConverter;
	
	public RouteDto create(RouteDto routeDto) {
		Route route = dtoToRouteConverter.convert(routeDto);
		route = routeRepository.save(route);
		routeDto.setId(route.getId());
		return routeDto;
	}
	
	public RouteDto getById(Long routeId) {
		Optional<Route> route = routeRepository.findById(routeId);
		if (!route.isPresent()) {
			throw new ResourceNotFoundException();
		}
		
		return routeToDtoConverter.convert(route.get());
	}
	
	public void update(RouteDto routeDto) {
		Optional<Route> oldRoute = routeRepository.findById(routeDto.getId());
		if (!oldRoute.isPresent()) {
			throw new ResourceNotFoundException();
		}
		
		Route route = dtoToRouteConverter.convert(routeDto);
		
		if (route.getName() != null){
			oldRoute.get().setName(route.getName());
		}
		if (route.getHead() != null){
			oldRoute.get().setHead(route.getHead());
		}
		if (route.getLocations() != null){
			oldRoute.get().setLocations(route.getLocations());
		}
		
		routeRepository.save(oldRoute.get());
	}
	
	public void delete(Long routeId) {
		routeRepository.deleteById(routeId);
	}
	
	public RoutePlanDto getRoutePlan(Long routeId) {
		Optional<Route> route = routeRepository.findById(routeId);
		if (!route.isPresent()) {
			throw new ResourceNotFoundException();
		}
		return generateRoutePlanDto(route.get());
	}
	
	//Greedy algorithm
	private RoutePlanDto generateRoutePlanDto(Route route) {
		Integer idStart = 0;
		while (!route.getLocations().get(idStart).getHead()) {
			idStart++;
		}
		
		List<Location> locationsLast = new ArrayList<>();
		locationsLast.addAll(route.getLocations());
		
		List<DistanceDto> distances = new ArrayList<>();
		Location previousLocation = locationsLast.get(idStart);
		locationsLast.remove(previousLocation);
		
		Double totalDistance = 0d;
		while(!locationsLast.isEmpty()) {
			Integer nearestLocationIndex = 0;
			Double nearestLocationDistance = calculateDistance(previousLocation, locationsLast.get(nearestLocationIndex));
			for (int i = 1; i < locationsLast.size(); i++) {
				Double distance = calculateDistance(previousLocation, locationsLast.get(i));
				if (distance < nearestLocationDistance) {
					nearestLocationIndex = i;
					nearestLocationDistance = distance;
				}
			}
			distances.add(DistanceDto.builder().from(previousLocation.getId())
					.to(locationsLast.get(nearestLocationIndex).getId()).distance(nearestLocationDistance).build());
			previousLocation = locationsLast.get(nearestLocationIndex);
			locationsLast.remove(previousLocation);
			totalDistance += nearestLocationDistance;
		}
		return RoutePlanDto.builder().route(distances).totalDistance(totalDistance).build();
	}
	
	private Double calculateDistance(Location l1, Location l2) {
		return Math.sqrt(Math.pow((l1.getX() - l2.getX()), 2) + Math.pow((l1.getY() - l2.getY()), 2));
	}

}
