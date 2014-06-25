package com.example.mobileinteracao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapter.ListaNotificacoesAdapter;
import com.example.banco.GerenciadorDisciplina;
import com.example.banco.GerenciadorNotificacoes;
import com.example.banco.GerenciadorRemetente;
import com.example.model.Disciplina;
import com.example.model.Notificacao;
import com.example.model.Remetente;

public class ListaActivity extends Activity {
	Context context;
	private static String TAG = "ListaActivity";
	private static Boolean visitante;
	private static String ordem = "ORDER BY data desc";
	private static String filtro = "";
	private AlertDialog alerta;
	private String txtDtaInicial;
	private String txtDtaFinal;
	private boolean apenasLida;
	private long idRemetente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		context = getApplicationContext();
		setTitle("Notificações");

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			visitante = extras.getBoolean("visitante");
		} else {
			visitante = false;
		}
		criarLista("ORDER BY data asc");
	}

	public void criarLista(String ordem) {
		Log.i(TAG, "criarLista: " + ordem);
		GerenciadorNotificacoes g = new GerenciadorNotificacoes(context);
		List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();
		filtro = " WHERE _id > 0 ";
		if (visitante) {
			filtro = filtro + " and id_tipo = 4 ";
		}
		if (txtDtaInicial != null && txtDtaFinal != null){
			if((!txtDtaInicial.equals("")) && (!txtDtaInicial.equals(""))) 
			filtro = filtro + " and data BETWEEN \"" + txtDtaInicial
					+ "\" AND \"" + txtDtaFinal + "\"";
		}
		if (idRemetente != 0)
			filtro = filtro + " and id_remetente = " + idRemetente;
		if (apenasLida == true)
			filtro = filtro + " and lida = 0";
	//	mensagem(filtro);
		listaNotificacao = g.getNotificacoes(ordem, filtro);
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
		} else if (id == R.id.ordenar_data_desc) {
			ordem = "ORDER BY data DESC";
			criarLista(ordem);
			return true;
		} else if (id == R.id.localizar_por_data_remetente) {
			localizarData();
		}
		else if (id == R.id.localizar_por_disciplina) {
			localizarPorDisciplina();
		}
		return super.onOptionsItemSelected(item);
	}

	private void localizarData() {
		LayoutInflater li = getLayoutInflater();
		final View view = li.inflate(R.layout.pesquisa_data_remetente, null);		
		final EditText edtDataInicial = (EditText) view
				.findViewById(R.id.edtDataInicial);
		final EditText edtDataFinal = (EditText) view
				.findViewById(R.id.edtDataFinal);
		final CheckBox chkApenasLida = (CheckBox) view
				.findViewById(R.id.chkApenasLida);

		List<Remetente> remTemp = new ArrayList<Remetente>();
		List<Remetente> rem = new ArrayList<Remetente>();
		GerenciadorRemetente gRem = new GerenciadorRemetente(this);
		remTemp = gRem.getRemetentes();
		Remetente remTodos = new Remetente();

		remTodos.setId(0);
		remTodos.setNome("Todos Remetentes");
		rem.add(remTodos);
		for (Remetente remetente : remTemp) {
			rem.add(remetente);
		}

		ArrayAdapter<Remetente> adapter = new ArrayAdapter<Remetente>(this,
				android.R.layout.simple_list_item_1, rem);

		final Spinner spRemententes = (Spinner) view
				.findViewById(R.id.spinner1);
		spRemententes.setAdapter(adapter);
		txtDtaInicial = "";
		txtDtaFinal = "";
		view.findViewById(R.id.btnLocalizarDisciplina).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						txtDtaInicial = edtDataInicial.getText().toString();
						txtDtaFinal = edtDataFinal.getText().toString();
						apenasLida = chkApenasLida.isChecked();
						Remetente r = new Remetente();
						r = (Remetente) spRemententes.getSelectedItem();
						idRemetente = r.getId();

						if ((txtDtaInicial.length() == 0)
								|| (txtDtaFinal.length() == 0)) {
							txtDtaInicial = "";
							txtDtaFinal = "";
							criarLista("ORDER BY data desc");
						} else if ((txtDtaInicial.length() != 10)
								|| (txtDtaFinal.length() != 10))
							mensagem("Data inválida! \n" + txtDtaInicial + "\n"
									+ txtDtaFinal);
						else {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd");
							// data inicial
							String anoIni = txtDtaInicial.substring(6, 10);
							String mesIni = txtDtaInicial.substring(3, 5);
							String diaIni = txtDtaInicial.substring(0, 2);

							Calendar calIni = Calendar.getInstance();
							calIni.set(Integer.parseInt(anoIni),
									Integer.parseInt(mesIni) - 1,
									Integer.parseInt(diaIni));
							txtDtaInicial = sdf.format(calIni.getTime());

							// data final
							String anoFim = txtDtaFinal.substring(6, 10);
							String mesFim = txtDtaFinal.substring(3, 5);
							String diaFim = txtDtaFinal.substring(0, 2);

							Calendar calFim = Calendar.getInstance();
							calIni.set(Integer.parseInt(anoFim),
									Integer.parseInt(mesFim) - 1,
									Integer.parseInt(diaFim));
							txtDtaFinal = sdf.format(calIni.getTime());
							

							criarLista("ORDER BY data desc");
						}
						alerta.dismiss();
					}
				});

		view.findViewById(R.id.btnCancelarDisciplina).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						alerta.dismiss();
					}
				});

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Informe os dados para a pesquisa");
		builder.setView(view);
		alerta = builder.create();
		alerta.show();

	}
	private void localizarPorDisciplina(){
		LayoutInflater li = getLayoutInflater();
		final View view = li.inflate(R.layout.pesquisa_por_disciplina, null);		
		
		GerenciadorDisciplina gDisc = new GerenciadorDisciplina(this);
		List<Disciplina> disc = new ArrayList<Disciplina>();
		disc = gDisc.getDisciplinas();
		ArrayAdapter<Disciplina> adapter = new ArrayAdapter<Disciplina>(this,
				android.R.layout.simple_list_item_1, disc);
		

		final Spinner spRemententes = (Spinner) view
				.findViewById(R.id.spinner1);
		spRemententes.setAdapter(adapter);

		view.findViewById(R.id.btnLocalizarDisciplina).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						alerta.dismiss();
					}
				});

		view.findViewById(R.id.btnCancelarDisciplina).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						alerta.dismiss();
					}
				});

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Informe a disciplina");
		builder.setView(view);
		alerta = builder.create();
		alerta.show();		
	}
	public void mensagemToast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
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
