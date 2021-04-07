package com.josko.meterservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.josko.meterservice.domain.Address;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(value = OrderAnnotation.class)
public class AddresRepositoryTest {
	
	@Autowired
    private AddressRepository addressRepository;
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Order(1)
	public void should_find_all_addresses() {
		List<Address> addresses = addressRepository.findAll();
		// Preloaded 4 addresses before test
		assertThat(addresses).hasSize(4);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Order(2)
	public void test_getById() {
		// Preloaded address = (id = 2, streetName: 'address2', houseNumber: 20)
		int testAddresId = 2;
		String preloadedStreetName = "address2";
		Address address = addressRepository.findById(testAddresId).get();
		assertTrue(address.getStreetName().equals(preloadedStreetName));
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Order(3)
	public void test3_add() {
		String streetName = "testStreetName";
		int houseNoumber = 128;
		
		List<Address> addresses = addressRepository.findAll();
		assertThat(addresses).hasSize(4);
		
		addressRepository.add(streetName, houseNoumber);
		addresses = addressRepository.findAll();
		assertThat(addresses).hasSize(5);
		assertTrue(addresses.get(addresses.size()-1).getStreetName().equals(streetName));
	}
}
