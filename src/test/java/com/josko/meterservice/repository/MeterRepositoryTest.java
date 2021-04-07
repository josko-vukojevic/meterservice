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

import com.josko.meterservice.domain.Meter;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MeterRepositoryTest {

	@Autowired
    private MeterReposytory meterReposytory;

	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_findAll() {
		List<Meter> meters = meterReposytory.findAll();
		// Preloaded 2 meters before test
		assertThat(meters).hasSize(2);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_getById() {
		// Preloaded meter = (id = 2, client_id: 2)
		int testMeterId = 2;
		Meter meter = meterReposytory.findById(testMeterId).get();
		assertTrue(meter.getClient().getId() == testMeterId);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_add() {
		// Preloaded 2 meters before test
		// Preloaded Client (id:3, name:'name3', addres_id:3)
		// Add meter and map to client_id
		int testClientId = 3;
		meterReposytory.add(testClientId);
		List<Meter> meters = meterReposytory.findAll();
		assertThat(meters).hasSize(3);
		assertTrue(meters.get(2).getClient().getId() == testClientId);
	}
}
