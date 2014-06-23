package com.example.model;

public class Remetente {
	private long id;
	private String nome;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Remetente(String nome) {
		super();
		this.nome = nome;
	}
	public Remetente() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
