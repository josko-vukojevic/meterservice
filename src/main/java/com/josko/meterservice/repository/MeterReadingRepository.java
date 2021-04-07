package com.josko.meterservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.josko.meterservice.custom.IMeterReadingAgg;
import com.josko.meterservice.domain.MeterReading;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Integer> {

	@Query("SELECT mr FROM MeterReading mr WHERE mr.meter2.id = :meter_id")
	List<MeterReading> getAllForMeterId(@Param("meter_id") int meter_id);

	@Query("SELECT mr FROM MeterReading mr WHERE mr.meter2.id = :meter_id AND mr.yearOfConsuption = :year")
	List<MeterReading> getAllForMeterIdAndYear(@Param("meter_id") int meter_id, @Param("year") int year);
	
	@Modifying
	@Query(value = "INSERT INTO meter_readings(month_of_consuption, year_of_consuption, kwh, meter_id) VALUES (:month, :year, :kwh, :meter_id)",
			nativeQuery = true)
	void add(@Param("month") String month, @Param("year") int year, @Param("kwh") int kwh, @Param("meter_id") int meter_id);
	
	@Query("SELECT mr FROM MeterReading mr WHERE mr.monthOfConsuption = :month AND mr.yearOfConsuption = :year AND mr.meter2.id = :meter_id")
	MeterReading getSpecific(@Param("month") String month, @Param("year") int year, @Param("meter_id") int meter_id);
	
	@Modifying
	@Query(value = "UPDATE meter_readings SET kwh = :kwh WHERE month_of_consuption = :month AND year_of_consuption = :year AND meter_id = :meter_id",
			nativeQuery = true)
	void update(@Param("month") String month, @Param("year") int year, @Param("kwh") int kwh, @Param("meter_id") int meter_id);
	
	@Modifying
	@Query(value = "DELETE FROM meter_readings WHERE month_of_consuption = :month AND year_of_consuption = :year AND meter_id = :meter_id",
			nativeQuery = true)
	void delete(@Param("month") String month, @Param("year") int year, @Param("meter_id") int meter_id);
	
	@Query(value = "SELECT meter_id 'meterId', SUM(kwh) AS 'aggCount' FROM meter_readings WHERE year_of_consuption = :year GROUP BY meter_id",
			nativeQuery = true)
	List<IMeterReadingAgg> aggPerYear(@Param("year") int year);
}
