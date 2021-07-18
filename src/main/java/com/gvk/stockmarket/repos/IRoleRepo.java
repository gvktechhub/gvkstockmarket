package com.gvk.stockmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvk.stockmarket.models.Role;

public interface IRoleRepo extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
