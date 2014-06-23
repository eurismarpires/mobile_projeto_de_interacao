package com.example.mobileinteracao;

import com.example.banco.GerenciadorLogin;
import com.example.model.Login;
import com.example.model.Notificacao;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		setTitle("Cadastro de Usuário");
		context = getApplicationContext();

		edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		edtMatricula = (EditText) findViewById(R.id.edtMatricula);

		gerenciador = new GerenciadorLogin(this);
		Login login = new Login();
		login = gerenciador.getLogin(1);
		if (login != null) {

			edtUsuario.setText(login.getUsuario());
			edtSenha.setText(login.getSenha());
			edtMatricula.setText(login.getMatricula());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		gerenciador.fecharDB();
	}

	public void onClick(View v) {
		Login login = new Login();
		login.setId(1);
		login.setUsuario(edtUsuario.getText().toString());
		login.setSenha(edtSenha.getText().toString());
		login.setMatricula(edtMatricula.getText().toString());

		Login loginBusca = new Login();
		loginBusca = gerenciador.getLogin(1);
		if (loginBusca == null) {
			gerenciador.insertLogin(login);
		} else {
			gerenciador.update(login);
		}		
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Login");
		alerta.setMessage("Usuário cadastrado com sucesso!");
		alerta.setIcon(R.drawable.ic_launcher);
		alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(context, LoginActivity.class);
				startActivity(intent);

			}
		});
		alerta.show();

	}

}
