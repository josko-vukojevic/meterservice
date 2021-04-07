package com.josko.meterservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josko.meterservice.custom.IMeterReadingAgg;
import com.josko.meterservice.domain.MeterReading;
import com.josko.meterservice.repository.MeterReadingRepository;
import com.josko.meterservice.services.interfaces.IMeterReadingService;

@Service
@Transactional
public class MeterReadingService implements IMeterReadingService {

	@Autowired
	private MeterReadingRepository meterReadingRepository;

	@Override
	public List<MeterReading> getAllForMeterId(int meter_id) {
		return meterReadingRepository.getAllForMeterId(meter_id);
	}
	
	@Override
	public List<MeterReading> getAllForMeterIdAndYear(int meter_id, int year) {
		return meterReadingRepository.getAllForMeterIdAndYear(meter_id, year);
	}

	@Override
	public void add(String month, int year, int kwh, int meter_id) {
		if (meterReadingRepository.getSpecific(month, year, meter_id) != null) {
			String result = "MeterReading for " + year + "-" + month + " (meter_id: " + meter_id + ") already exists";
			System.out.println(result);
		} else {
			meterReadingRepository.add(month, year, kwh, meter_id);
		}
	}
	
	@Override
	public MeterReading getSpecific(String month, int year, int meter_id) {
		return meterReadingRepository.getSpecific(month, year, meter_id);
	}
	
	@Override
	public void update(String month, int year, int kwh, int meter_id) {
		meterReadingRepository.update(month, year, kwh, meter_id);
	}
	
	@Override
	public void delete(String month, int year, int meter_id) {
		meterReadingRepository.delete(month, year, meter_id);
	}
	
	@Override
	public List<IMeterReadingAgg> aggPerYear(int year) {
		return meterReadingRepository.aggPerYear(year);
	}

	public List<MeterReading> listAll() {
		return meterReadingRepository.findAll();
	}

	public void delete(int id) {
		meterReadingRepository.deleteById(id);
	}
}
