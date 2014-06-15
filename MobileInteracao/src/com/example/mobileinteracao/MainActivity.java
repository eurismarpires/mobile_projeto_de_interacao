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

	List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();
	ArrayAdapter<Notificacao> adaptador = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Notificacao n1 = new Notificacao();		
		n1.setData(new Date());
		n1.setRemetente("Eurismar Pires Borges");
		n1.setMensagem("Devolva o Livro");
		
		
		Notificacao n2 = new Notificacao();		
		n2.setData(new Date());
		n2.setRemetente("Eurismar Pires Borges");
		n2.setMensagem("Prova na semana que vem");		
		
	//	List<Notificacao> notificacoes = ger
		
		listaNotificacao.add(n1);
		listaNotificacao.add(n2);
		ListView lista = (ListView)findViewById(R.id.listView1);
		
		//adaptador = new ArrayAdapter<Notificacao>(this,android.R.layout.simple_list_item_1,listaNotificacao);
		ListaNotificacoesAdapter adaptador = new ListaNotificacoesAdapter();
		Log.i("EURISMAR", "PASSOU AKI....................1..........................");
		lista.setAdapter(adaptador);
	}

	public void onClick(View v){
		Intent intent = new Intent(this, CadastroActivity.class);
		startActivity(intent);
	}

}
