package com.internetbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internetbanking.model.Address;
import com.internetbanking.repository.AddressRepository;
@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressRepository repository;

	@Override
	public void saveAddress(Address address) {
		 repository.saveAddress(address);
		
		
	}

}
