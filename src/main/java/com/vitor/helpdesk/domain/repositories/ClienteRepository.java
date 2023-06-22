package com.vitor.helpdesk.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
