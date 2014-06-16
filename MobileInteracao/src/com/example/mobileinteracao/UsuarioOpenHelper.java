package com.example.mobileinteracao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuarioOpenHelper extends SQLiteOpenHelper {
	Context context;

	public UsuarioOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] sql = context.getString(R.string.ContextoDados_onCreate)
				.split("\n");
		db.beginTransaction();
		try {
			executarComandosSQL(db, sql);
			db.beginTransaction();
		} catch (SQLException e) {
			Log.e("Erro ao criar as tabelas", e.toString());
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
		String[] sql = context.getString(R.string.ContextoDados_onCreate)
				.split("\n");
		db.beginTransaction();
		try {
			executarComandosSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Erro ao atualizar as tabelas", e.toString());
		} finally {
			db.endTransaction();
		}
		onCreate(db);
	}

	public void executarComandosSQL(SQLiteDatabase db, String[] sql) {
		for (String s : sql)
			if (s.trim().length() > 0)
				db.execSQL(s);
	}

}
