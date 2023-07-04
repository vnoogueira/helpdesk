package com.vitor.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vitor.helpdesk.domain.Chamado;
import com.vitor.helpdesk.dtos.ChamadoDTO;
import com.vitor.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findChamadoById(@PathVariable Integer id) {
		Chamado chamadoObj = chamadoService.findChamadoById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamadoObj));
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findChamadoAll() {
		List<Chamado> list = chamadoService.findAll();
		List<ChamadoDTO> chamadoDto = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(chamadoDto);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> insertChamado(@Valid @RequestBody ChamadoDTO objDto){
		Chamado chamado = chamadoService.insertChamado(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDto){
		Chamado chamado = chamadoService.updateChamado(id, objDto);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}
	
}
