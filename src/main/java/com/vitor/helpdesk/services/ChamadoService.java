package com.vitor.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.helpdesk.domain.Chamado;
import com.vitor.helpdesk.domain.Cliente;
import com.vitor.helpdesk.domain.Tecnico;
import com.vitor.helpdesk.domain.enums.Prioridade;
import com.vitor.helpdesk.domain.enums.Status;
import com.vitor.helpdesk.domain.repositories.ChamadoRepository;
import com.vitor.helpdesk.dtos.ChamadoDTO;
import com.vitor.helpdesk.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
	
	public Chamado findChamadoById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new ObjectNotFoundExceptions("Chamado não encontrado ! ID: " + id));		
	}


	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}


	public Chamado insertChamado(@Valid ChamadoDTO obj) {
		return chamadoRepository.save(newChamado(obj));
	}
	
	public Chamado updateChamado(Integer id, @Valid ChamadoDTO objDto) {
		objDto.setId(id);
		Chamado oldChamado = findChamadoById(id);
		oldChamado = newChamado(objDto);
		return chamadoRepository.save(oldChamado);
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridades(Prioridade.toEnum(obj.getPrioridades()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;		
	}
}
