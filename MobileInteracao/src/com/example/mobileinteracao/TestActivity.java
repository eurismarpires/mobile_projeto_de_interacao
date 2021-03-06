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
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
	public void buscar1(View v){
		//buscarTipo1();
	//	Toast.makeText(this, "Vou buscar remetente", Toast.LENGTH_LONG);
		mensagem("este � uma mensagem de teste");
		buscarRemetente();
		//Toast.makeText(this, "j� busquei remetente", Toast.LENGTH_LONG);
	}
	public void buscarTipo1(){
	    GerenciadorTipo gt = new GerenciadorTipo(this);
	    Tipo t = new Tipo();
	    t = gt.getTipo(1);	    
	    if(t != null){
	    	Log.i(TAG, t.getDescricao());
	    }else{
	    	Log.i(TAG, "tipo est� nulo");
	    }
	}
	public void buscaLogin1(){
	    GerenciadorLogin glogin = new GerenciadorLogin(this);
	    Login login = new Login();
	    login = glogin.getLogin(1);
	    
	    //Log.i(TAG, login.getUsuario());
	}	
	public void buscarRemetente(){
		GerenciadorRemetente g = new GerenciadorRemetente(this);
		Remetente r = new Remetente();
		r = g.getRemetente(1L);
		Log.i(TAG,  r.toString());
	}
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
	public void mensagem(String msg) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Aten��o");
		alerta.setMessage(msg);
		alerta.setIcon(R.drawable.ic_launcher);
		alerta.setPositiveButton("OK", null);
		alerta.show();
	}
}
