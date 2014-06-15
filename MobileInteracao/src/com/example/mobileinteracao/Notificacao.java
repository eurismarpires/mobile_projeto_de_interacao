package com.example.mobileinteracao;

import java.util.Date;

public class Notificacao 	{
 	private Date data;
	private String remetente;
	private String mensagem;	
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

	@Override
	public String toString() {
		return "Notificacao [data=" + data + ", remetente=" + remetente
				+ ", mensagem=" + mensagem + "]";
	}
	

	
}
