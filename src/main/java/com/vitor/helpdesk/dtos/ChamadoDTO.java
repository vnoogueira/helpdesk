package com.vitor.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitor.helpdesk.domain.Chamado;

public class ChamadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	@NotNull(message = "O campo PRIORIDADE é obrigatório !")
	private Integer prioridades;
	@NotNull(message = "O campo STATUS é obrigatório !")
	private Integer status;
	@NotNull(message = "O campo TITULO é obrigatório !")
	private String titulo;
	@NotNull(message = "O campo OBSERVACOES é obrigatório !")
	private String observacoes;
	@NotNull(message = "O campo TECNICO é obrigatório !")
	private Integer tecnico;
	@NotNull(message = "O campo CLIENTE é obrigatório !")
	private Integer cliente;
	private String nomeTecnico;
	private String nomeCliente;
	
	
	public ChamadoDTO() {
		super();
	}


	public ChamadoDTO(Chamado obj) {
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridades = obj.getPrioridades().getCodigo();
		this.status = obj.getStatus().getCodigo();
		this.titulo = obj.getTitulo();
		this.observacoes = obj.getObservacoes();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeTecnico = obj.getTecnico().getNome();
		this.nomeCliente = obj.getCliente().getNome();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDataAbertura() {
		return dataAbertura;
	}


	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}


	public LocalDate getDataFechamento() {
		return dataFechamento;
	}


	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}


	public Integer getPrioridades() {
		return prioridades;
	}


	public void setPrioridades(Integer prioridades) {
		this.prioridades = prioridades;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public Integer getTecnico() {
		return tecnico;
	}


	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}


	public Integer getCliente() {
		return cliente;
	}


	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}


	public String getNomeTecnico() {
		return nomeTecnico;
	}


	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
}
