package com.example.model;

public class Tipo {
	private long id;
	private String descricao;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Tipo(long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Tipo(String descricao) {
		super();
		this.descricao = descricao;
	}
	public Tipo() {
		super();
	}
	@Override
	public String toString() {
		return "Tipo [id=" + id + ", descricao=" + descricao + "]";
	}
	
}
