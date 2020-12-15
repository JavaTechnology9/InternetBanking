package com.internetbanking.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.internetbanking.token.UserToken;
@Repository
@Transactional
public class UserTokenRepositoryImpl implements UserTokenRepository{
	@Autowired
	private HibernateTemplate template;

	@SuppressWarnings("deprecation")
	@Override
	public UserToken validateToken(String token) {
		@SuppressWarnings("unchecked")
		List<UserToken> findByNamedQuery = (List<UserToken>) template.find("select ut from UserToken ut where ut.tokenInfo=?0",token);
		return findByNamedQuery!=null?findByNamedQuery.get(0):null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveToken(UserToken token) {
		template.saveOrUpdate(token);
		
	}

}
