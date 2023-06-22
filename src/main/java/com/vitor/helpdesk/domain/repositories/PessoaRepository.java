package com.vitor.helpdesk.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
