package com.vitor.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.helpdesk.domain.Chamado;
import com.vitor.helpdesk.dtos.ChamadoDTO;
import com.vitor.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService chamadoService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findChamadoById(@PathVariable Integer id){
		Chamado obj = chamadoService.findChamadoById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
}
