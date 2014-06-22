package com.example.model;

import java.util.Date;

public class Notificacao 	{
 	private Date data;
	private String remetente;
	private String mensagem;	
	private Boolean lida;
	private String tipo; //Aviso de avaliação, Notas e Frequencias, aviso de vencimentos de emprestimos biblioteca, comunicação geral
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
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

	public Boolean getLida() {
		return lida;
	}
	public void setLida(Boolean lida) {
		this.lida = lida;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Notificacao [data=" + data + ", remetente=" + remetente
				+ ", mensagem=" + mensagem + ", lida=" + lida + ", tipo="
				+ tipo + "]";
	}

	

	
}
