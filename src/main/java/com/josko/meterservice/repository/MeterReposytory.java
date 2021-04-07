package com.josko.meterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.josko.meterservice.domain.Meter;

public interface MeterReposytory extends JpaRepository<Meter, Integer>{

	@Modifying
	@Query(value = "INSERT INTO meters(client_id) VALUES (:client_id)",
			nativeQuery = true)
	void add(@Param("client_id") int client_id);
}
