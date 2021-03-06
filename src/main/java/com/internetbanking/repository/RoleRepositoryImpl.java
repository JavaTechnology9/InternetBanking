package com.internetbanking.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.internetbanking.model.Role;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository{
	@Autowired
	private HibernateTemplate template;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveRole(Role role) {
		 template.saveOrUpdate(role);
		
	}

}
