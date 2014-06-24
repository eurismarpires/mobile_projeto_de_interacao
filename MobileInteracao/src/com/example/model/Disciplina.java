package com.example.model;

public class Disciplina {
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
	
	public Disciplina(long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Disciplina(String descricao) {
		super();
		this.descricao = descricao;
	}
	public Disciplina() {
		super();
	}
	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", descricao=" + descricao + "]";
	}
	
}
