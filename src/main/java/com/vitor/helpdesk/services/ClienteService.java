package com.vitor.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.helpdesk.domain.Cliente;
import com.vitor.helpdesk.domain.repositories.ClienteRepository;
import com.vitor.helpdesk.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}


	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundExceptions("Cliente não encontrado ! - ID: " + id));
	}

}
