package com.josko.meterservice.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josko.meterservice.custom.IMeterReadingAgg;
import com.josko.meterservice.domain.MeterReading;
import com.josko.meterservice.services.MeterReadingService;

@RestController
@RequestMapping("/meterReading")
public class MeterReadingController {

	@Autowired
	MeterReadingService meterReadingsService;

	/**
	 * Retrieve all MeterReading objects.
	 */
	@GetMapping("")
	public List<MeterReading> list() {
		return meterReadingsService.listAll();
	}

	/**
	 * Retrieve specific MeterReading by given month, year and meter_id.
	 */
	@GetMapping("/getSpecific/")
	public ResponseEntity<MeterReading> getSpecific(@Param(value = "month") String month, 
			@Param(value = "year") int year, @Param(value = "meter_id") int meter_id) {
		try {
			MeterReading meterReading = meterReadingsService.getSpecific(month, year, meter_id);
			return new ResponseEntity<MeterReading>(meterReading, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MeterReading>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Retrieve all MeterReading objects for given Meter.
	 */
	@GetMapping("/get/{meter_id}")
	public ResponseEntity<List<MeterReading>> getAllForMeterId(@PathVariable int meter_id) {
		try {
			List<MeterReading> meterReadings = meterReadingsService.getAllForMeterId(meter_id);
			return new ResponseEntity<List<MeterReading>>(meterReadings, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<MeterReading>>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Retrieve all MeterReading for given meter_id and year.
	 */
	@GetMapping("/get/")
	public ResponseEntity<String> getAllForMeterIdAndYear(@Param(value = "meter_id") int meter_id,
			@Param(value = "year") int year) {
		try {
			List<MeterReading> meterReadings = meterReadingsService.getAllForMeterIdAndYear(meter_id, year);
			String result = "meter_id: " + meter_id + " | year: " + year + " => ";
			for (MeterReading meterReading : meterReadings) {
				result += meterReading.getMonthOfConsuption() + " = " + meterReading.getKwh() + " kWh | ";
			}
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Add MeterReading.
	 */
	@PostMapping("/add/")
	public void add(@Param(value = "month") String month, @Param(value = "year") int year,
			@Param(value = "kwh") int kwh, @Param(value = "meter_id") int meter_id) {
		meterReadingsService.add(month, year, kwh, meter_id);
	}
	
	/**
	 * Update single MeterReading.
	 */
	@PostMapping("/update/")
	public void update(@Param(value = "month") String month, @Param(value = "year") int year,
			@Param(value = "kwh") int kwh, @Param(value = "meter_id") int meter_id) {
		meterReadingsService.update(month, year, kwh, meter_id);
	}
	
	/**
	 * Delete MeterReading for specific entry.
	 */
	@DeleteMapping("/delete/")
	public void delete(@Param(value = "month") String month, @Param(value = "year") int year,
			@Param(value = "meter_id") int meter_id) {
		meterReadingsService.delete(month, year, meter_id);
	}
	
	/**
	 * Delete MeterReading by id.
	 */
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		meterReadingsService.delete(id);
	}
	
	/**
	 * Aggregate consumption per year.
	 */
	@GetMapping("/aggPerYear/")
	public ResponseEntity<List<IMeterReadingAgg>> aggPerYear(@Param(value = "year") int year) {
		try {
			List<IMeterReadingAgg> aggMeterReadings = meterReadingsService.aggPerYear(year);
			return new ResponseEntity<List<IMeterReadingAgg>>(aggMeterReadings, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<IMeterReadingAgg>>(HttpStatus.NOT_FOUND);
		}
	}
}
