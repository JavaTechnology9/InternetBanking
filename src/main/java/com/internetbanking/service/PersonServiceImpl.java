package com.internetbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internetbanking.model.Person;
import com.internetbanking.repository.PersonRepository;
@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonRepository repository;

	@Override
	public void savePerson(Person person) {
		  repository.savePerson(person);
		
		
	}

}
