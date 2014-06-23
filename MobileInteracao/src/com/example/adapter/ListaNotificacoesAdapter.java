package com.example.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.mobileinteracao.DetalheMensagemActivity;
import com.example.mobileinteracao.R;
import com.example.mobileinteracao.R.id;
import com.example.mobileinteracao.R.layout;
import com.example.model.Notificacao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListaNotificacoesAdapter extends ArrayAdapter<Notificacao> {
	List<Notificacao> notificacoes;
	Context context;

	public ListaNotificacoesAdapter(Context context,
			List<Notificacao> notificacoes) {
		super(context, 0, notificacoes);
		this.notificacoes = notificacoes;
		this.context = context;
		
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		Notificacao notificacao = notificacoes.get(index);
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.notificacao_item, parent, false);
		}

		TextView tvRemetente = (TextView) view.findViewById(R.id.tvRemetente);
		tvRemetente.setText(notificacao.getRemetente().getNome());
		
		TextView tvMensagem = (TextView) view.findViewById(R.id.tvMensagem);
		tvMensagem.setText(notificacao.getMensagem());
		
		if(notificacao.getLida() == 0){
			tvMensagem.setTypeface(null,Typeface.BOLD);
		}else{
			tvMensagem.setTypeface(null,Typeface.NORMAL);
		}
		
		TextView tvData = (TextView) view.findViewById(R.id.tvData);
		tvData.setText(notificacao.getData());
		
		
		final Intent intent = new Intent(context, DetalheMensagemActivity.class);
		String id = notificacao.getId().toString();
		Log.i("ADAPTER", "id = " + id);
		intent.putExtra("id", id);		
		intent.putExtra("mensagem", notificacao.getMensagem());
		intent.putExtra("remetente", notificacao.getRemetente().getNome());
		intent.putExtra("data", notificacao.getData());
		tvMensagem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {						
				if (context != null) {					
					context.startActivity(intent);

				}
			}
		});

		return view;
	}

}
