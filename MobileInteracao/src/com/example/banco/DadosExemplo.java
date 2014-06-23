package com.example.banco;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.model.Notificacao;
import com.example.model.Remetente;
import com.example.model.Tipo;

import android.content.Context;
import android.util.Log;

public class DadosExemplo {
	static Date d;
	static final String TAG = "DadosFicticios";
	public static void gerarNotificacoes(Context context){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");		
		
		Remetente r1 = new Remetente("Coordenador do Curso");
		Remetente r2 = new Remetente("Direção de Unidade do Curso");
		Remetente r3 = new Remetente("Biblioteca");
		Remetente r4 = new Remetente("Pro-Reitoria");
		Remetente r5 = new Remetente("Docente da disciplina");
		GerenciadorRemetente gr = new GerenciadorRemetente(context);
		gr.deletarTodos();
		r1.setId(gr.insert(r1));
		r2.setId(gr.insert(r2));
		r3.setId(gr.insert(r3));
		r4.setId(gr.insert(r4));
		r5.setId(gr.insert(r5));
		
		
		Tipo t1 = new Tipo("Aviso de avaliação");
		Tipo t2 = new Tipo("Notas e Frequencias");
		Tipo t3 = new Tipo("aviso de vencimentos de emprestimos biblioteca");
		Tipo t4 = new Tipo("Comunicação Geral - Notificações públicas");		
		GerenciadorTipo gt = new GerenciadorTipo(context);
		gt.deletarTodos();
		t1.setId(gt.insert(t1));
		t2.setId(gt.insert(t2));
		t3.setId(gt.insert(t3));
		t4.setId(gt.insert(t4));
		
		
		
		String d1 = sdf.format(d = new Date(2015,6,1)); 
		Notificacao n1 = new Notificacao(d1, r1, "Atenção, conforme previsto em calendário acadêmico, não haverá aulas nos dias 19, 20 e 21 de junho. Na... http://fb.me/1zN2B74dO", 0, t1);
		
		String d2 = sdf.format(d = new Date(2015,6,2)); 
		Notificacao n2 = new Notificacao(d2, r2 , "Atenção alunos, fiquem atentos com a devolução e com o prazo de entrega de materiais nas Bibliotecas. http://fb.me/30Xwd8009", 0, t2);	

		String d3 = sdf.format(d = new Date(2015,5,5)); 
		Notificacao n3 = new Notificacao(d3, r5 , "A coordenação de Relações Públicas da Ascom #UFG conquistou 1º lugar da região Centro-Oeste e o 3º lugar Nacional... http://fb.me/12t657P8a", 0, t4);		
		
		
		
		GerenciadorNotificacoes gNote = new GerenciadorNotificacoes(context);
		Log.i(TAG, "Vou deletar todas");
		gNote.deletarTodas();
		gNote.inserir(n1);
		gNote.inserir(n2);
		gNote.inserir(n3);		
	}
}
