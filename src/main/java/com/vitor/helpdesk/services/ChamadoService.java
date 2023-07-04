package com.vitor.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.helpdesk.domain.Chamado;
import com.vitor.helpdesk.domain.repositories.ChamadoRepository;
import com.vitor.helpdesk.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public Chamado findChamadoById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new ObjectNotFoundExceptions("Chamado não encontrado ! ID: " + id));		
	}
}
