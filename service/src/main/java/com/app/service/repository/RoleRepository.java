package com.app.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value = "select r.* from ROLE r where r.name = :name", nativeQuery = true)
	List<Role> findByName(String name);
}
