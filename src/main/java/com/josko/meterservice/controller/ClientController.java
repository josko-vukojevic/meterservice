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

import com.josko.meterservice.domain.Client;
import com.josko.meterservice.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	/**
	 * Retrieve all Clients objects.
	 */
	@GetMapping("")
	public List<Client> list() {
		return clientService.listAll();
	}
	
	/**
	 * Retrieve Client for given id.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Client> get(@PathVariable int id) {
		try {
			Client client = clientService.get(id);
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Add Client.
	 */
	@PostMapping("/add/")
	public void add(@Param(value = "name") String name, @Param(value = "address_id") int address_id) {
		clientService.add(name, address_id);
	}
}
