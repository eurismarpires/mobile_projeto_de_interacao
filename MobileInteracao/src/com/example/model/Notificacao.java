package com.example.model;

import java.util.Date;

public class Notificacao 	{
	private Long id;
 	private String data;
	private String remetente;
	private String mensagem;	
	private Integer lida;
	private String tipo; //Aviso de avaliação, Notas e Frequencias, aviso de vencimentos de emprestimos biblioteca, comunicação geral
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	} 
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getLida() {
		return lida;
	}
	public void setLida(Integer lida) {
		this.lida = lida;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Notificacao [id=" + id + ", data=" + data + ", remetente="
				+ remetente + ", mensagem=" + mensagem + ", lida=" + lida
				+ ", tipo=" + tipo + "]";
	}
	
	
	

	
}
