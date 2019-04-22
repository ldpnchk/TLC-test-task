package com.pinchuk.tlc.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoutePlanDto {
	
	private List<DistanceDto> route;
	
	private Double totalDistance;

}
