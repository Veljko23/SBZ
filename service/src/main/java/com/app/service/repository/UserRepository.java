package com.app.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	@Query(value = "select u.* from USER u where u.type IS NOT 'A'", nativeQuery = true)
	List<User> findByRole();
	
	@Query(value = "select u.* from USER u where u.username IS NOT NULL", nativeQuery = true)
	List<User> findAllUsers();

}
