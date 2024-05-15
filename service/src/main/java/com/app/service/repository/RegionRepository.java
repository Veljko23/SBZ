package com.app.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>{
	
	Optional<Region> findOneByNaziv(String naziv);

}
