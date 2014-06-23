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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		context = getApplicationContext();
		setTitle("Notificações");
		criarLista();
	}

	public void criarLista() {				
		GerenciadorNotificacoes g = new GerenciadorNotificacoes(context);
		
		List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();
		listaNotificacao = g.getNotificacoes();
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		ListaNotificacoesAdapter adapter = new ListaNotificacoesAdapter(this,
				listaNotificacao);
		lista.setAdapter(adapter);

	}

	@Override
	protected void onStart() {		
		super.onStart();
		GerenciadorNotificacoes g = new GerenciadorNotificacoes(context);
		
		List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();
		listaNotificacao = g.getNotificacoes();
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		ListaNotificacoesAdapter adapter = new ListaNotificacoesAdapter(this,
				listaNotificacao);
		lista.setAdapter(adapter);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.lista, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
