package com.gvk.stockmarket.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.models.Role;
import com.gvk.stockmarket.repos.IRoleRepo;
import com.gvk.stockmarket.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleRepo roleRepo;

	@Override
	public List<Role> list() {
		return roleRepo.findAll();
	}

	@Override
	public Role saveOrUpdate(Role role) {
		return roleRepo.saveAndFlush(role);
	}

	@Override
	public Role get(String name) {
		return roleRepo.findByName(name);
	}

}
