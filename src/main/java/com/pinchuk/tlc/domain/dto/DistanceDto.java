package com.pinchuk.tlc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DistanceDto {
	
	private Long from;
	private Long to;
	
	private Double distance;
	
}
