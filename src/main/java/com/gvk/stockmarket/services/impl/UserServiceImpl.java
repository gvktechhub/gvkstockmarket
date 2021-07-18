package com.gvk.stockmarket.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.models.User;
import com.gvk.stockmarket.repos.IUserRepo;
import com.gvk.stockmarket.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepo userRepo;

	@Override
	public List<User> list() {
		return userRepo.findAll();
	}

	@Override
	public User saveOrUpdaate(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
