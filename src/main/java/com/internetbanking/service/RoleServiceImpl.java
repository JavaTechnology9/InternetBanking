package com.internetbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internetbanking.model.Role;
import com.internetbanking.repository.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository repository;

	@Override
	public void roleSave(Role role) {
		repository.saveRole(role);
		
	}

}
