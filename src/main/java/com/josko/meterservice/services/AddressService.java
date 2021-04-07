package com.josko.meterservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josko.meterservice.domain.Address;
import com.josko.meterservice.repository.AddressRepository;
import com.josko.meterservice.services.interfaces.IAddresService;

@Service
@Transactional
public class AddressService implements IAddresService{
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void add(String streetName, int houseNumber) {
		addressRepository.add(streetName, houseNumber);
	}

	public List<Address> listAll() {
		return addressRepository.findAll();
	}
	
	public Address get(int id) {
		return addressRepository.findById(id).get();
	}
}
