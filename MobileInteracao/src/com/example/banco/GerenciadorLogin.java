package com.example.banco;

import com.example.model.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GerenciadorLogin extends SQLiteOpenHelper {
	
	private static final String NOME_BANCO = "mobile_interacao.db";
	private static final int VERSAO_SCHEMA = 1;

	public GerenciadorLogin(Context context) {
		super(context, NOME_BANCO, null, VERSAO_SCHEMA);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE login (_id INTEGER PRIMARY KEY," +
				" usuario TEXT, senha TEXT, matricula TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS LOGIN");
	}
	
	public void inserir(String usuario, String senha, String matricula) {
		ContentValues valores = new ContentValues();

		valores.put("usuario", usuario);
		valores.put("senha", senha);
		valores.put("matricula", matricula);
		

		getWritableDatabase().insert("login", "usuario", valores);		
	}
	public void atualizar(GerenciadorLogin helper, Login login){
		ContentValues valores = new ContentValues();
		valores.put("usuario", login.getUsuario());
	}
	
	public Cursor obterTodos() {
		return getReadableDatabase().rawQuery("select _id, usuario, senha, matricula " +
				" FROM login ORDER BY usuario", null);
	}
	public Cursor obterRegistroLogin() {
		return getReadableDatabase().rawQuery("select _id, usuario, senha, matricula " +
				" FROM login WHERE _id = 1", null);
	}	
	public String obterLogin(Cursor c) {
		return c.getString(1);
	}

	public String obterSenha(Cursor c) {
		return c.getString(2);
	}

	public String obterMatricula(Cursor c) {
		return c.getString(3);
	}
}
