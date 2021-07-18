package com.gvk.stockmarket.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvk.stockmarket.models.User;

public interface IUserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
