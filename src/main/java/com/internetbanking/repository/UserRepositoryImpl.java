package com.internetbanking.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.internetbanking.model.User;
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
	@Autowired
	private HibernateTemplate template;

	@SuppressWarnings("deprecation")
	@Override
	public User findByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> find = (List<User>) template.find("from User where userName=?0", username);
		return find!=null?find.get(0):null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveUser(User user) {
		template.saveOrUpdate(user);
		
		
	}

	@Override
	public User updateLoginPassword(String username,String oldPassword, String newPassword, String retryPassword) {
		// TODO Auto-generated method stub
		return null;
	}

}
