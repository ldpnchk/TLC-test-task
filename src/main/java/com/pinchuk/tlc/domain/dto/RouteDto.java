package com.pinchuk.tlc.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDto {
	
	private Long id;
	private String name;
	private Long head;
	private Long[] locations;

}
