package com.ptk.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ptk.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByEmail(String email);
	
	@EntityGraph(attributePaths = "role") 
	Optional<User> findByUsername(String username);
	 
	 boolean existsByRole_Name(String roleName);
}
