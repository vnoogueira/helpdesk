package com.vitor.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.helpdesk.domain.Cliente;
import com.vitor.helpdesk.domain.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

}
