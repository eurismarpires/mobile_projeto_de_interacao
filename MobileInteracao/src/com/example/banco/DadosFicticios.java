package com.example.banco;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.model.Notificacao;

import android.content.Context;
import android.util.Log;

public class DadosFicticios {
	static Date d;
	static final String TAG = "DadosFicticios";
	public static void gerarNotificacoes(Context context){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");		
		
		Notificacao n1 = new Notificacao();
		n1.setData(sdf.format(d = new Date(2015,6,21)));
		n1.setLida(0);
		n1.setMensagem("Aten��o, conforme previsto em calend�rio acad�mico, n�o haver� aulas nos dias 19, 20 e 21 de junho. Na... http://fb.me/1zN2B74dO ");
		n1.setRemetente("Coordenador do Curso");
		n1.setTipo("Coordenador do Curso");
		

		
		
		Notificacao n2 = new Notificacao();
		n2.setData(sdf.format(d = new Date(2015,6,1)));
		n2.setLida(0);
		n2.setMensagem("Aten��o alunos, fiquem atentos com a devolu��o e com o prazo de entrega de materiais nas Bibliotecas. http://fb.me/30Xwd8009  ");
		n2.setRemetente("Biblioteca");
		n2.setTipo("Aviso de Biblioteca");
		Log.i(TAG, "Vou gerar as notifica��es");
		GerenciadorNotificacoes gNote = new GerenciadorNotificacoes(context);
		Log.i(TAG, "Vou deletar todas");
		gNote.deletarTodas();
		gNote.inserir(n1);
		gNote.inserir(n2);
				
	}
}
