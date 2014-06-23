package com.example.model;

import java.util.Date;

public class Notificacao 	{
	private Integer id;
 	private String data; 	
	private Remetente remetente;
	private String mensagem;	
	private Integer lida;	
	private Tipo tipo; //Aviso de avaliação, Notas e Frequencias, aviso de vencimentos de emprestimos biblioteca, comunicação geral
	
	public Notificacao() {
		super();
	}

	public Notificacao(String data, Remetente remetente, String mensagem,
			Integer lida, Tipo tipo) {
		super();
		this.data = data;
		this.remetente = remetente;
		this.mensagem = mensagem;
		this.lida = lida;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Remetente getRemetente() {
		return remetente;
	}

	public void setRemetente(Remetente remetente) {
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}




	
	

	
}
