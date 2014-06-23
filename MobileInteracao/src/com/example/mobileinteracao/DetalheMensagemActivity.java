package com.example.mobileinteracao;

import com.example.banco.GerenciadorNotificacoes;
import com.example.model.Notificacao;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.os.Build;

public class DetalheMensagemActivity extends Activity {
	TextView tvData;
	TextView tvRemetente;
	TextView tvMensagem;
	CheckBox cbNaoLida;
	Context context;
	long idNotificacao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe_mensagem);
		Log.i("DETALHE", "AKI ........................0");
		context = getApplicationContext();
		setTitle("Mensagem");
		Log.i("DETALHE", "AKI ........................1");
		Intent intent = getIntent();
		
		idNotificacao = Long.parseLong(intent.getStringExtra("id"));
		
		Log.i("DETALHE", "AKI ........................2");
		marcarNotificacaoLida(1);
		
		
		tvData = (TextView) findViewById(R.id.tvDt);		
		Log.i("DETALHE", "AKI ........................3");
		tvRemetente = (TextView) findViewById(R.id.tvRem);		
		Log.i("DETALHE", "AKI ........................4");
		
		tvMensagem = (TextView) findViewById(R.id.tvMsg);
		Log.i("DETALHE", "AKI ........................5");		
		
		
		cbNaoLida = (CheckBox)findViewById(R.id.cbNaoLida);
		
		

		tvData.setText(intent.getStringExtra("data"));		
		tvRemetente.setText(intent.getStringExtra("remetente"));		
		tvMensagem.setText(intent.getStringExtra("mensagem"));
		
		cbNaoLida.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(cbNaoLida.isChecked()){
					marcarNotificacaoLida(0);
				}
				
			}
		});
		
		
	}
	//lida = 1; não lida = 0
	public void marcarNotificacaoLida(int lida){
		Log.i("DETALHE","marcarNotificacaoLida");
		GerenciadorNotificacoes gn = new GerenciadorNotificacoes(context);
		Notificacao n = new Notificacao();
		n = gn.getNotificacao(idNotificacao);
		n.setLida(lida);
		gn.alterar(n);
	}

}
