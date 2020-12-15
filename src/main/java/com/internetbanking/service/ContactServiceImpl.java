package com.internetbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internetbanking.model.Contact;
import com.internetbanking.repository.ContactRepository;
@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactRepository repository;

	@Override
	public void saveContact(Contact contact) {
		 repository.saveContact(contact);
		
		
	}

}
