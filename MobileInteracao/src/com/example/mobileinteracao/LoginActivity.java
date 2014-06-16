package com.example.mobileinteracao;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
	}

	public void onClickEntrar(View v) {
		EditText edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		EditText edtSenha = (EditText) findViewById(R.id.edtLoginSenha);

		String usuario = edtUsuario.getText().toString();
		String senha = edtSenha.getText().toString();

		if (usuario.equals("eurismar") && senha.equals("123")) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		} else {

			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setTitle("Atenção");			
			alerta.setMessage("Usuário ou senha incorretos");			
			alerta.setIcon(R.drawable.ic_launcher);
			alerta.setPositiveButton("OK", null);						
			alerta.show();

		}

	}
	public void onClickCadastrar(View v) {
		Intent intent = new Intent(this, CadastroActivity.class);
		startActivity(intent);		
		
	}

}
