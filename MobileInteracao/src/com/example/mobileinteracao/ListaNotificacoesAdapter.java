package com.example.mobileinteracao;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.model.Notificacao;

import android.content.Context;
import android.content.Intent;
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
		tvRemetente.setText(notificacao.getRemetente());

		TextView tvMensagem = (TextView) view.findViewById(R.id.tvMensagem);
		tvMensagem.setText(notificacao.getMensagem());

		TextView tvData = (TextView) view.findViewById(R.id.tvData);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		tvData.setText("" + sdf.format(notificacao.getData()));

		final Intent intent = new Intent(context, DetalheMensagemActivity.class);
		intent.putExtra("mensagem", notificacao.getMensagem());
		intent.putExtra("remetente", notificacao.getRemetente());
		intent.putExtra("data", sdf.format(notificacao.getData()));
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
