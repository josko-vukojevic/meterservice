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

import com.josko.meterservice.domain.Meter;
import com.josko.meterservice.services.MeterService;

@RestController
@RequestMapping("/meter")
public class MeterController {

	@Autowired
	MeterService meterService;

	/**
	 * Retrieve all Meters objects.
	 */
	@GetMapping("")
	public List<Meter> list() {
		return meterService.listAll();
	}

	/**
	 * Retrieve Meter for given id.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Meter> get(@PathVariable int id) {
		try {
			Meter meter = meterService.get(id);
			return new ResponseEntity<Meter>(meter, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Meter>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Add Meter.
	 */
	@PostMapping("/add/")
	public void add(@Param(value = "client_id") int client_id) {
		meterService.add(client_id);
	}
}
