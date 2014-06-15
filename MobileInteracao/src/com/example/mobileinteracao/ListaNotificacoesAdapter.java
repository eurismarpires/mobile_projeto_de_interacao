package com.example.mobileinteracao;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListaNotificacoesAdapter extends ArrayAdapter<Notificacao> {		
	List<Notificacao> notificacoes;
  
	public ListaNotificacoesAdapter(Context context,
			List<Notificacao> notificacoes) {
		super(context, 0, notificacoes);
		this.notificacoes = notificacoes; 
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
        
        return view;		
	}
	
	


}
