package com.pinchuk.tlc;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pinchuk.tlc.domain.Location;
import com.pinchuk.tlc.domain.Route;
import com.pinchuk.tlc.repository.LocationRepository;
import com.pinchuk.tlc.repository.RouteRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TlcApplication implements CommandLineRunner{
	
	private final LocationRepository locationRepository;
	
	private final RouteRepository routeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TlcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		List<Location> locations = Arrays.asList(Location.builder().name("Kyiv").x(0.0).y(0.0).head(false).build(),
													Location.builder().name("Lviv").x(-400.0).y(200.0).head(true).build(),
													Location.builder().name("Odessa").x(-100.0).y(600.0).head(true).build());
		locationRepository.saveAll(locations);
		List<Route> routes = Arrays.asList(Route.builder().name("big route").head(locations.get(1)).locations(locations).build(),
											Route.builder().name("small route").head(locations.get(2)).locations(locations.subList(1, 3)).build());
		routeRepository.saveAll(routes);
		*/
	}

}
