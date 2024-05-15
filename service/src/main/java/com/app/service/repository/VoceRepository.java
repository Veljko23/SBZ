package com.app.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Voce;

@Repository
public interface VoceRepository extends JpaRepository<Voce, Integer>{
	
	Optional<Voce> findOneByName(String name);
	
	@Query(value = "select v.* from VOCE v where v.nadmorska_visina IS NOT NULL", nativeQuery = true)
	List<Voce> findAllVoce();
	
	List<Voce> findByName(String name);
}
