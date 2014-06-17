package com.example.mobileinteracao;

import com.example.banco.GerenciadorLogin;
import com.example.model.Login;
import com.example.model.Notificacao;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class CadastroActivity extends Activity {
	GerenciadorLogin gerenciador;
	Cursor cursorLogin;
	EditText edtUsuario = null;
	EditText edtSenha = null;
	EditText edtMatricula = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		
		gerenciador = new GerenciadorLogin(this);
		edtUsuario = (EditText)findViewById(R.id.edtUsuario);
		edtSenha = (EditText)findViewById(R.id.edtSenha);
		edtMatricula = (EditText)findViewById(R.id.edtMatricula);
		
		cursorLogin = gerenciador.obterTodos();
		
		int count = cursorLogin.getCount();
		
		Log.i("DADOS BANCO","" + count);				
    	if (cursorLogin != null ) {    		
    		if  (cursorLogin.moveToFirst()) {
    			do {
    				//Get version from Cursor
    				String usuario = cursorLogin.getString(cursorLogin.getColumnIndex("usuario"));
    				String senha = cursorLogin.getString(cursorLogin.getColumnIndex("senha"));
    				String matricula = cursorLogin.getString(cursorLogin.getColumnIndex("matricula"));
    				
    				edtUsuario.setText(usuario);
    				edtSenha.setText(senha);
    				edtMatricula.setText(matricula);  				
    			}while (cursorLogin.moveToNext()); //Move to next row
    		} 
    	}
    			
	}
	
	@Override
	protected void onDestroy() {		
		super.onDestroy();
		gerenciador.close();
	}

	public void onClick(View v){
		Login login = new Login();
		login.setUsuario(edtUsuario.getText().toString());
		login.setSenha(edtSenha.getText().toString());
		login.setMatricula(edtSenha.getText().toString());
		
		gerenciador.inserir(login.getUsuario(), login.getSenha(), login.getMatricula());
		
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Login");			
		alerta.setMessage("Usuário cadastrado com sucesso!");			
		alerta.setIcon(R.drawable.ic_launcher);
		alerta.setPositiveButton("OK", null);						
		alerta.show();
		
	}

}
