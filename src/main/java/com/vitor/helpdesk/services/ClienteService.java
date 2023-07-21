package com.vitor.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitor.helpdesk.domain.Cliente;
import com.vitor.helpdesk.domain.Pessoa;
import com.vitor.helpdesk.domain.repositories.ClienteRepository;
import com.vitor.helpdesk.domain.repositories.PessoaRepository;
import com.vitor.helpdesk.dtos.ClienteDTO;
import com.vitor.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.vitor.helpdesk.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder enconder;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundExceptions("Cliente não encontrado ! - ID: " + id));
	}

	public Cliente insertCliente(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(enconder.encode(objDTO.getSenha()));
		validaCpfeEmail(objDTO);
		Cliente newCliente = new Cliente(objDTO);
		return clienteRepository.save(newCliente);
	}

	public Cliente updateCliente(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldCliente = findById(id);
		oldCliente = new Cliente(objDTO);
		return clienteRepository.save(oldCliente);
	}

	public void deleteClienteById(Integer id) {
		Cliente cliente = findById(id);
		if (cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente não pode ser deletado, pois possue chamado!");
		}
		clienteRepository.deleteById(id);
	}

	public void validaCpfeEmail(ClienteDTO objDTO) {
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
