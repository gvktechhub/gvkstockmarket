package com.gvk.stockmarket.services;

import java.util.List;

import com.gvk.stockmarket.models.Role;

public interface IRoleService {
	
	List<Role> list();
	
	Role saveOrUpdate(Role role);
	
	Role get(String name);

}
