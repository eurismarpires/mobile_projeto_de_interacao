package com.example.mobileinteracao;

import java.util.Date;

import com.example.banco.DataBaseHelper;
import com.example.banco.GerenciadorLogin;
import com.example.banco.GerenciadorRemetente;
import com.example.banco.GerenciadorTipo;
import com.example.model.Login;
import com.example.model.Remetente;
import com.example.model.Tipo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class TestActivity extends Activity {
	DataBaseHelper db;
	private static final String TAG = "TEST";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		db = new DataBaseHelper(getApplicationContext());

		
	}
	public void onClick(View v){
		
		Log.i(TAG, "VOU TENTAR INSERIR LOGIN");
		Login login = new Login();
		login.setMatricula("123455");
		login.setSenha("123");
		login.setUsuario("Eurismar");
		GerenciadorLogin gLogin = new GerenciadorLogin(this);
	    long login1_id = gLogin.insertLogin(login);
		Log.i(TAG, "INSERIU LOGIN " + login1_id);
		
		
		Log.i(TAG, "VOU TENTAR INSERIR TIPO");
		Tipo tipo = new Tipo();
		tipo.setDescricao("descricao teste");	
		long tipo1_id = gLogin.insertTipo(tipo);
		Log.i(TAG, "INSERIU TIPO " + tipo1_id);		
		
	}
	public void inserir(View v){
		Remetente r1 = new Remetente();
		r1.setNome("Eurismar");
		
		Remetente r2 = new Remetente();
		r2.setNome("Elisangela");
		
		Remetente r3 = new Remetente();
		r3.setNome("Kathlem");
		
		Remetente r4 = new Remetente();
		r4.setNome("Hevellyn");		
		
		GerenciadorRemetente gr  = new GerenciadorRemetente(this);
		gr.insert(r1);
		gr.insert(r2);
		gr.insert(r3);
		gr.insert(r4);
		
		 
		
		Tipo t = new Tipo();
		t.setDescricao("teste 1");
	    GerenciadorTipo gt = new GerenciadorTipo(this);
	    gt.insert(t);
	}
	public void atualizar(View v){
		GerenciadorRemetente gr  = new GerenciadorRemetente(this);
		Remetente r = gr.getRemetente(3);
		r.setNome(r.getNome() + " alterado as " + new Date());
		gr.update(r);
	}
	public void deletar(View v){		
		new GerenciadorRemetente(this).deletarTodos();
	}
	public void buscar1(View v){}
	public void buscarTodos(View v){}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
