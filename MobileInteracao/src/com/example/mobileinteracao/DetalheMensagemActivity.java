package com.example.mobileinteracao;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class DetalheMensagemActivity extends Activity {
	TextView tvData;
	TextView tvRemetente;
	TextView tvMensagem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe_mensagem);
		setTitle("Mensagem");
		
		Intent intent = getIntent();
		
		tvData = (TextView) findViewById(R.id.tvDt);		
		tvRemetente = (TextView) findViewById(R.id.tvRem);		
		tvMensagem = (TextView) findViewById(R.id.tvMsg);
		
				
		
		tvData.setText(intent.getStringExtra("data"));		
		tvRemetente.setText(intent.getStringExtra("remetente"));		
		tvMensagem.setText(intent.getStringExtra("mensagem"));
		
		
		
	}


}
