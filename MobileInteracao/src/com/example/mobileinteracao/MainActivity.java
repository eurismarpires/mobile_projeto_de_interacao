package com.example.mobileinteracao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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

	public void onClick(View v){
		Intent intent = new Intent(this, CadastroActivity.class);
		startActivity(intent);
	}

}
