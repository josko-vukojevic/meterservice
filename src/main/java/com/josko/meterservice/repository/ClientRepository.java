package com.josko.meterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.josko.meterservice.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Modifying
	@Query(value = "INSERT INTO clients(name, address_id) VALUES (:name, :address_id)",
			nativeQuery = true)
	void add(@Param("name") String name, @Param("address_id") int address_id);
}
