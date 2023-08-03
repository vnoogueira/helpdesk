package com.vitor.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vitor.helpdesk.domain.Tecnico;
import com.vitor.helpdesk.dtos.TecnicoDto;
import com.vitor.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDto(obj));
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDto>> findAll() {
		List<Tecnico> list = tecnicoService.findAll();
		List<TecnicoDto> listDto = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TecnicoDto> insertTecnico(@Valid @RequestBody TecnicoDto objDTO) {
		Tecnico newObj = tecnicoService.insertTecnico(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> updateTecnico(@PathVariable Integer id, @Valid @RequestBody TecnicoDto objDTO) {
		Tecnico obj = tecnicoService.updateTecnico(id, objDTO);
		return ResponseEntity.ok().body(new TecnicoDto(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> deleteTecnico(@PathVariable Integer id){
		tecnicoService.deleteTecnico(id);
		return ResponseEntity.noContent().build();
	}

}
