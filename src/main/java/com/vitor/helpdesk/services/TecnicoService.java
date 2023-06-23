package com.vitor.helpdesk.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.helpdesk.domain.Tecnico;
import com.vitor.helpdesk.domain.repositories.TecnicoRepository;
import com.vitor.helpdesk.services.exceptions.ObjectNotFoundExceptions;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado - ID: " + id));
	}
}
