package com.vitor.helpdesk.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
