package com.josko.meterservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josko.meterservice.domain.Meter;
import com.josko.meterservice.repository.MeterReposytory;
import com.josko.meterservice.services.interfaces.IMeterService;

@Service
@Transactional
public class MeterService implements IMeterService {

	@Autowired
	private MeterReposytory meterReposytory;

	@Override
	public void add(int client_id) {
		List<Meter> meters = meterReposytory.findAll();
		if (meters.stream().anyMatch(meter -> meter.getClient().getId() == client_id)) {
			String result = "Meter is already mapped for client:" + client_id;
			System.out.println(result);
		} else {
			meterReposytory.add(client_id);
		}
	}

	public List<Meter> listAll() {
		return meterReposytory.findAll();
	}

	public Meter get(int id) {
		return meterReposytory.findById(id).get();
	}
}
