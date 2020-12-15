package com.internetbanking.repository;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.internetbanking.model.Person;

@Repository
@Transactional
public class PersonRepositoryImpl implements PersonRepository{
	@Autowired
	private HibernateTemplate template;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void savePerson(Person person) {
		template.saveOrUpdate(person);
		
	}

}
