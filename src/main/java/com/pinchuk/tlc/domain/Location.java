package com.pinchuk.tlc.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "locations")
public class Location {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "x", nullable = false)
	private Double x;
	
	@Column(name = "y", nullable = false)
	private Double y;
	
	@Column(name = "head", nullable = false)
	private Boolean head;
	
}
