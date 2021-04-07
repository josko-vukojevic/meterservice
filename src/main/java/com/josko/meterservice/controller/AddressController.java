package com.josko.meterservice.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josko.meterservice.domain.Address;
import com.josko.meterservice.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	/**
	 * Retrieve all Address objects.
	 */
	@GetMapping("")
	public List<Address> list() {
		return addressService.listAll();
	}

	/**
	 * Retrieve Address for given id.
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<Address> get(@PathVariable int id) {
		try {
			Address address = addressService.get(id);
			return new ResponseEntity<Address>(address, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Add Address.
	 */
	@PostMapping("/add/")
	public void add(@Param(value = "streetName") String streetName, @Param(value = "houseNumber") int houseNumber) {
		addressService.add(streetName, houseNumber);
	}
}
