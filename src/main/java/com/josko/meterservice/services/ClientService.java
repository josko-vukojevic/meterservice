package com.josko.meterservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josko.meterservice.domain.Client;
import com.josko.meterservice.repository.ClientRepository;
import com.josko.meterservice.services.interfaces.IClientService;

@Service
@Transactional
public class ClientService implements IClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void add(String name, int address_id) {
		List<Client> clients = clientRepository.findAll();
		if (clients.stream().anyMatch(client -> client.getAddress().getId() == address_id)) {
			String result = "Address ("+ address_id + ") is already mapped for another client";
			System.out.println(result);
		}else {
			clientRepository.add(name, address_id);
		}
	}

	public List<Client> listAll() {
		return clientRepository.findAll();
	}

	public Client get(int id) {
		return clientRepository.findById(id).get();
	}
}
