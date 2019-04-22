package com.pinchuk.tlc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pinchuk.tlc.domain.Route;

@Repository
public interface RouteRepository extends PagingAndSortingRepository<Route, Long>{

}
