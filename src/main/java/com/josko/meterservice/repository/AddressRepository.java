package com.josko.meterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.josko.meterservice.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	@Modifying
	@Query(value = "INSERT INTO addresses(street_name, house_number) VALUES (:street_name, :house_number)",
			nativeQuery = true)
	void add(@Param("street_name") String stretName, @Param("house_number") int houseNumber);
}
