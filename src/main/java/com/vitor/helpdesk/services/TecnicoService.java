package com.vitor.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitor.helpdesk.domain.Pessoa;
import com.vitor.helpdesk.domain.Tecnico;
import com.vitor.helpdesk.domain.repositories.PessoaRepository;
import com.vitor.helpdesk.domain.repositories.TecnicoRepository;
import com.vitor.helpdesk.dtos.TecnicoDto;
import com.vitor.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.vitor.helpdesk.services.exceptions.ObjectNotFoundExceptions;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder enconder;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado - ID: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico insertTecnico(TecnicoDto objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(enconder.encode(objDTO.getSenha()));
		validaCpfeEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}

	public Tecnico updateTecnico(Integer id, @Valid TecnicoDto objDTO) {
		objDTO.setId(id);
		Tecnico oldTecnico = findById(id);
		if (!objDTO.getSenha().equals(oldTecnico.getSenha())) {
			objDTO.setSenha(enconder.encode(objDTO.getSenha()));
		}
		validaCpfeEmail(objDTO);
		oldTecnico = new Tecnico(objDTO);
		return tecnicoRepository.save(oldTecnico);
	}

	public void deleteTecnico(Integer id) {
		Tecnico obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico não pode ser deletado, pois possue chamado!");
		}
		tecnicoRepository.deleteById(id);
	}

	public void validaCpfeEmail(TecnicoDto objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}

	}

}
