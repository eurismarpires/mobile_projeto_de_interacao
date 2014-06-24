package com.example.banco;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.model.Disciplina;
import com.example.model.Notificacao;
import com.example.model.Remetente;
import com.example.model.Tipo;

import android.content.Context;
import android.util.Log;

public class DadosExemplo {	
	static final String TAG = "DadosFicticios";
	public static void gerarNotificacoes(Context context){
				
		
		Remetente r1 = new Remetente("Coordenador do Curso");
		Remetente r2 = new Remetente("Dire��o de Unidade do Curso");
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
		
		
		Tipo t1 = new Tipo("Aviso de avalia��o");
		Tipo t2 = new Tipo("Notas e Frequencias");
		Tipo t3 = new Tipo("aviso de vencimentos de emprestimos biblioteca");
		Tipo t4 = new Tipo("Comunica��o Geral - Notifica��es p�blicas");		
		GerenciadorTipo gt = new GerenciadorTipo(context);
		gt.deletarTodos();
		t1.setId(gt.insert(t1));
		t2.setId(gt.insert(t2));
		t3.setId(gt.insert(t3));
		t4.setId(gt.insert(t4));
		
		
		Disciplina disciplina1 = new Disciplina("Desenvolvimento Para Dispositivos M�veis");
		Disciplina disciplina2 = new Disciplina("Desenvolvimento Para Dispositivos Web");
		Disciplina disciplina3 = new Disciplina("Integra��o de Aplica��es");
		Disciplina disciplina4 = new Disciplina("Desenvolvimento Para Persist�ncia");
		Disciplina disciplina5 = new Disciplina("Desenvolvimento de Software Concorrente");
		GerenciadorDisciplina gd = new GerenciadorDisciplina(context);
		gd.deletarTodos();
		disciplina1.setId(gd.insert(disciplina1));
		disciplina2.setId(gd.insert(disciplina2));
		disciplina3.setId(gd.insert(disciplina3));
		disciplina4.setId(gd.insert(disciplina4));
		disciplina5.setId(gd.insert(disciplina5));
		
		
		
		 
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();

		calendar.set(2014, 5, 2);	
		String d1 = sdf.format(calendar.getTime());
				
		
		Notificacao n1 = new Notificacao(d1, r1, "Aten��o, conforme previsto em calend�rio acad�mico, n�o haver� aulas nos dias 19, 20 e 21 de junho. Na... http://fb.me/1zN2B74dO", 0, t1,disciplina1);
		
		calendar.set(2014, 5, 6,20,3);		
		String d2 = sdf.format(calendar.getTime()); 
		Notificacao n2 = new Notificacao(d2, r2 , "Aten��o alunos, fiquem atentos com a devolu��o e com o prazo de entrega de materiais nas Bibliotecas. http://fb.me/30Xwd8009", 0, t2,disciplina2);	

		calendar.set(2014, 6, 5,7,0);
		String d3 = sdf.format(calendar.getTime());		 
		Notificacao n3 = new Notificacao(d3, r5 , "A coordena��o de Rela��es P�blicas da Ascom #UFG conquistou 1� lugar da regi�o Centro-Oeste e o 3� lugar Nacional... http://fb.me/12t657P8a", 0, t4,disciplina3);		
		
		calendar.set(2014, 7, 23,4,5);
		String d4 = sdf.format(calendar.getTime());		 
		Notificacao n4 = new Notificacao(d4, r5 , "Professor da Colorado State University, Ian Michael Orme, fala sobre os avan�os e entraves no combate �... http://fb.me/6B0zym0Vi", 0, t4,disciplina3);		

		
		calendar.set(2014, 7, 10,12,0);
		String d5 = sdf.format(calendar.getTime());		
		Notificacao n5 = new Notificacao(d5, r5 , "M�sica No Campus UFG emociona o p�blico, com repert�rio diversificado e beliss�ma interpreta��o de Filipe Catto. http://fb.me/3i0gKgVdr", 0, t4,disciplina4);		
		
        
		calendar.setTime(new Date());
		String d6 = sdf.format(calendar.getTime()); 
		Notificacao n6 = new Notificacao(d6, r5 , "Alunos, n�o se esque�am de avaliar os professores.O question�rio pode ser preenchido at� o dia 12 de junho. http://fb.me/1o4eyKVux", 0, t1,disciplina5);		
				
		
		GerenciadorNotificacoes gNote = new GerenciadorNotificacoes(context);
		Log.i(TAG, "Vou deletar todas");
		gNote.deletarTodas();
		gNote.inserir(n1);
		gNote.inserir(n2);
		gNote.inserir(n3);
		gNote.inserir(n4);
		gNote.inserir(n5);
		gNote.inserir(n6);
	}
}
