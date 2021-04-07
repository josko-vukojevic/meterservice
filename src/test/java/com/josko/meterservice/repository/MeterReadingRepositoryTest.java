package com.josko.meterservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.josko.meterservice.custom.IMeterReadingAgg;
import com.josko.meterservice.domain.MeterReading;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MeterReadingRepositoryTest {

	@Autowired
    private MeterReadingRepository meterReadingRepository;
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_findAll() {
		List<MeterReading> meterReadings = meterReadingRepository.findAll();
		// Preloaded 10 meterReadings before test
		assertThat(meterReadings).hasSize(10);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_deleteById() {
		// Preloaded 10 meterReadings before test
		meterReadingRepository.deleteById(1);
		List<MeterReading> meterReadings = meterReadingRepository.findAll();
		assertThat(meterReadings).hasSize(9);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_delete() {
		/* Preloaded 10 meterReadings before test.
		 * 5 of meterReadings are for meter_id=1 before test
		 * One of them is (month:'February', year:2020, kwh:12, meter_id:1)
		 */
		meterReadingRepository.delete("February", 2020, 1);
		List<MeterReading> meterReadings = meterReadingRepository.getAllForMeterId(1);
		assertThat(meterReadings).hasSize(4);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_getAllForMeterId() {
		// Preloaded 5 meterReadings for meter_id=1 before test
		List<MeterReading> meterReadings = meterReadingRepository.getAllForMeterId(1);
		assertThat(meterReadings).hasSize(5);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_getAllForMeterIdAndYear() {
		// Preloaded 2 meterReadings for meter_id=1 AND year=2021 before test
		List<MeterReading> meterReadings = meterReadingRepository.getAllForMeterIdAndYear(1, 2021);
		assertThat(meterReadings).hasSize(2);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_add() {
		// Preloaded 10 meterReadings before test
		// Preloaded Meter (id:2, client_id:2)
		// Add meterReading and map to meter_id
		String testMonth = "November";
		int testYear = 2019;
		int testKwh = 555;
		int testMeterId = 2;
		meterReadingRepository.add(testMonth, testYear, testKwh, testMeterId);
		List<MeterReading> meterReadings = meterReadingRepository.findAll();
		assertThat(meterReadings).hasSize(11);
		assertTrue(meterReadings.get(meterReadings.size() - 1).getMonthOfConsuption().equals(testMonth));
		assertTrue(meterReadings.get(meterReadings.size() - 1).getYearOfConsuption() == testYear);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_getSpecific() {
		// One of preloaded meterReadings
		// (month:'February', year:2020, kwh:12, meter_id:1)
		String testMonth = "February";
		int testYear = 2020;
		int testMeterId = 1;
		MeterReading result = meterReadingRepository.getSpecific(testMonth, testYear, testMeterId);
		assertTrue(result.getMonthOfConsuption().equals(testMonth));
		assertTrue(result.getYearOfConsuption() == 2020);
		assertTrue(result.getMeter().getId() == 1);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_update() {
		// One of preloaded meterReadings
		// (month:'February', year:2020, kwh:12, meter_id:1)
		String testMonth = "February";
		int testYear = 2020;
		int testMeterId = 1;
		int newKwh = 555;
		meterReadingRepository.update(testMonth, testYear, newKwh, testMeterId);
		MeterReading result = meterReadingRepository.getSpecific(testMonth, testYear, testMeterId);
		assertTrue(result.getKwh() == newKwh);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_aggPerYear() {
		// For preloaded would get one of agg values
		// (meterId:1, year:2020, aggCount:42)
		// (meterId:2, year:2020, aggCount:24)
		int testYear = 2020;
		List<IMeterReadingAgg> aggMeterReadings = meterReadingRepository.aggPerYear(testYear);

		assertTrue(aggMeterReadings.get(0).getAggCount() == 42);
		assertTrue(aggMeterReadings.get(1).getAggCount() == 24);
	}
}
