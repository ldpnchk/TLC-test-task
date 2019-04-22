package com.pinchuk.tlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pinchuk.tlc.domain.dto.RouteDto;
import com.pinchuk.tlc.domain.dto.RoutePlanDto;
import com.pinchuk.tlc.service.RouteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteController {
	
	private final RouteService routeService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public RouteDto createRoute(@RequestBody RouteDto routeDto) {
		return routeService.create(routeDto);
	}
	
	@RequestMapping(value = "/{routeId}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public RouteDto getRoute(@PathVariable Long routeId) {
		return routeService.getById(routeId);
	}
	
	@RequestMapping(value = "/{routeId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateRoute(@PathVariable Long routeId, @RequestBody RouteDto routeDto) {
		routeDto.setId(routeId);
		routeService.update(routeDto);
	}
	
	@RequestMapping(value = "/{routeId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRoute(@PathVariable Long routeId) {
		routeService.delete(routeId);
	}
	
	@RequestMapping(value = "/{routeId}/plan", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public RoutePlanDto getRoutePlan(@PathVariable Long routeId) {
		return routeService.getRoutePlan(routeId);
	}

}
