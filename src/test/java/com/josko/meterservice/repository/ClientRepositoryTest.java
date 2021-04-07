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

import com.josko.meterservice.domain.Client;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClientRepositoryTest {

	@Autowired
    private ClientRepository clientRepository;
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
		    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_findAll() {
		List<Client> clients = clientRepository.findAll();
		// Preloaded 3 clients before test
		assertThat(clients).hasSize(3);
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_getById() {
		// Preloaded client = (id = 2, name: 'name2', addres_id: 2)
		int testClientId = 2;
		String testName = "name2";
		Client client = clientRepository.findById(testClientId).get();
		assertTrue(client.getName().equals(testName));
	}
	
	@Test
	@Sql(scripts = "classpath:prepareForTest.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void test_add() {
		// Preloaded 3 clients before test
		// Preloaded Address (id:4, streetName:'address4', houseNumber:40)
		// Add client and map to address_id
		String testName = "testName";
		int testAddresId = 4;
		clientRepository.add(testName, testAddresId);
		List<Client> clients = clientRepository.findAll();
		assertThat(clients).hasSize(4);
		assertTrue(clients.get(3).getName().equals(testName));
	}
}
