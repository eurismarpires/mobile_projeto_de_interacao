package com.example.model;

import java.io.Serializable;

public class Login implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String usuario = "";
	private String senha = "";
	private String matricula = "";
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	
	public Login() {
		super();		
	}
	public Login(int id, String usuario, String senha, String matricula) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.matricula = matricula;		
	}
	@Override
	public String toString() {		
		return super.toString();
	}
	

	
}
