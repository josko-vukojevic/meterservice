package com.josko.meterservice.services.interfaces;

import java.util.List;

import com.josko.meterservice.custom.IMeterReadingAgg;
import com.josko.meterservice.domain.MeterReading;

public interface IMeterReadingService {

	List<MeterReading> getAllForMeterId(int id);
	
	List<MeterReading> getAllForMeterIdAndYear(int meter_id, int year);
	
	void add(String month, int year, int kwh, int meter_id);
	
	MeterReading getSpecific(String month, int year, int meter_id);
	
	void update(String month, int year, int kwh, int meter_id);
	
	void delete(String month, int year, int meter_id);
	
	List<IMeterReadingAgg> aggPerYear(int year);
}
