package com.pinchuk.tlc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pinchuk.tlc.domain.Location;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long>{

}
