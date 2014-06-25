package com.example.mobileinteracao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.example.banco.DadosExemplo;
import com.example.model.Notificacao;

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
		setTitle("Academy News - UFG");		
		DadosExemplo.gerarNotificacoes(getApplicationContext());
	}

	public void onClickEntrar(View v){
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);			
	}
	public void onClickVisitante(View v){		
		Intent intent = new Intent(this, ListaActivity.class);
		intent.putExtra("visitante", true);
		startActivity(intent);		
	}
	public void onClickCadastrar(View v){
		Intent intent = new Intent(this, CadastroActivity.class);
		startActivity(intent);			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.configuracoes) {
			Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
			startActivity(intent);
			return true;
		}else
		if (id == R.id.sair) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);			
			return true;
		}			
		return super.onOptionsItemSelected(item);
	}	
}
