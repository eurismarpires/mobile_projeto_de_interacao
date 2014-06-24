package com.example.mobileinteracao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.adapter.ListaNotificacoesAdapter;
import com.example.banco.DadosExemplo;
import com.example.banco.GerenciadorNotificacoes;
import com.example.model.Notificacao;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.Build;

public class ListaActivity extends Activity {
	Context context;
	private static String TAG = "ListaActivity";
	private static Boolean visitante;
	private static String ordem = "ORDER BY data desc";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		context = getApplicationContext();
		setTitle("Notificações");
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    visitante  = extras.getBoolean("visitante");
		}else{
			visitante = false;
		}
		criarLista("ORDER BY data asc");
	}

	public void criarLista(String ordem) {		
		Log.i(TAG, "criarLista: " + ordem);
		GerenciadorNotificacoes g = new GerenciadorNotificacoes(context);
		
		List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();
		if(visitante){
			listaNotificacao = g.getNotificacoesVisitante(ordem);
		}else{
			listaNotificacao = g.getNotificacoes(ordem);	
		}
		
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		ListaNotificacoesAdapter adapter = new ListaNotificacoesAdapter(this,
				listaNotificacao);
		lista.setAdapter(adapter);

	}

	@Override
	protected void onStart() {		
		super.onStart();
		criarLista(ordem);
		Log.i(TAG, "ORDENOU ON START: " + ordem);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.lista, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.ordenar_data_asc) {
			ordem = "ORDER BY data ASC";
			criarLista(ordem);
			return true;
		}else if (id == R.id.ordenar_data_desc) {
			ordem = "ORDER BY data DESC";
			criarLista(ordem);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
