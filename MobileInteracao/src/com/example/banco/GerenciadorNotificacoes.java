package com.example.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class GerenciadorNotificacoes extends SQLiteOpenHelper {
	
	private static final String NOME_BANCO = "mobile_interacao.db";
	private static final int VERSAO_SCHEMA = 1;
	
	public GerenciadorNotificacoes(Context context) {
		super(context, NOME_BANCO, null, VERSAO_SCHEMA);		
	}	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE notificacao (_id INTEGER PRIMARY KEY," +
				" data TEXT, remetente TEXT, mensagem TEXT, logado TEXT);");		

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
