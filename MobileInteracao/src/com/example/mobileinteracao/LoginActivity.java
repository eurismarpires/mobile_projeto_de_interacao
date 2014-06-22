package com.example.mobileinteracao;

import com.example.banco.GerenciadorLogin;
import com.example.model.Login;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("Academy News - UFG");
	}

	public void onClickEntrar(View v) {
		EditText edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		EditText edtSenha = (EditText) findViewById(R.id.edtLoginSenha);

		String usuario = edtUsuario.getText().toString();
		String senha = edtSenha.getText().toString();

		GerenciadorLogin gerenciador;
		gerenciador = new GerenciadorLogin(this);
		Login loginBusca = new Login();
		
		loginBusca = gerenciador.query(1);
		
		if (loginBusca == null) {
			mensagem("Usuário não cadastrado");
		} else {			 
			if (usuario.equals(loginBusca.getUsuario()) && senha.equals(loginBusca.getSenha())) {
				Intent intent = new Intent(this, ListaActivity.class);
				startActivity(intent);
			} else {
				mensagem("Usuário ou senha incorretos");

			}
		}
	}

	public void mensagem(String msg) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Atenção");
		alerta.setMessage(msg);
		alerta.setIcon(R.drawable.ic_launcher);
		alerta.setPositiveButton("OK", null);
		alerta.show();
	}


}
