package com.gvk.stockmarket.services;

import java.util.List;
import java.util.Optional;

import com.gvk.stockmarket.models.User;

public interface IUserService {
	
	List<User> list();
	
	User saveOrUpdaate(User user);
	
	Optional<User> findByUsername(String username);

}
