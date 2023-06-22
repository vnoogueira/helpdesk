package com.vitor.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vitor.helpdesk.domain.Chamado;
import com.vitor.helpdesk.domain.Cliente;
import com.vitor.helpdesk.domain.Tecnico;
import com.vitor.helpdesk.domain.enums.Perfil;
import com.vitor.helpdesk.domain.enums.Prioridade;
import com.vitor.helpdesk.domain.enums.Status;
import com.vitor.helpdesk.domain.repositories.ChamadoRepository;
import com.vitor.helpdesk.domain.repositories.ClienteRepository;
import com.vitor.helpdesk.domain.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpDeskApplication implements CommandLineRunner{

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cli1 = new Cliente(null, "Linus Torvals", "12345678910", "linus@gmail.com", "1234567890");
		cli1.addPerfil(Perfil.CLIENTE);
		
		Tecnico tec1 = new Tecnico(null, "Vitor Nogueira", "4563091685", "vitor@gamil.com", "12345");
		tec1.addPerfil(Perfil.ADMIN);
		
		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
	}

}
