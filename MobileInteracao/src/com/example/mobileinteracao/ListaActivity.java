package com.example.mobileinteracao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.model.Notificacao;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.Build;

public class ListaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		criarLista();						
	}

	public void criarLista(){
		Notificacao n1 = new Notificacao();		
		n1.setData(new Date());
		n1.setRemetente("Biblioteca");
		n1.setMensagem("Devolva o Livro");
		
		
		Notificacao n2 = new Notificacao();		
		n2.setData(new Date());
		n2.setRemetente("INF");
		n2.setMensagem("Prova na semana que vem");		
		
		Notificacao n3 = new Notificacao();		
		n3.setData(new Date());
		n3.setRemetente("DCE");
		n3.setMensagem("Este é um teste de notificação para alunos e funcionários da Universidade Federal de Goiás");		
		
		
		List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();
		
		
		listaNotificacao.add(n1);
		listaNotificacao.add(n2);
		listaNotificacao.add(n3);
	    ListView lista = (ListView)findViewById(R.id.listView1);		
		ListaNotificacoesAdapter adapter = new ListaNotificacoesAdapter(this, listaNotificacao);		
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
