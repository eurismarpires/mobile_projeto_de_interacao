package com.example.banco;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Tipo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GerenciadorTipo {
	private static final String TAG = "GERENCIADOR_TIPO";
	private SQLiteDatabase db = null;

	private static GerenciadorTipo instance;

	public static GerenciadorTipo getInstance(Context context) {
		if (instance == null)
			instance = new GerenciadorTipo(context);
		return instance;
	}

	public GerenciadorTipo(Context context) {
		DataBaseHelper dbh = DataBaseHelper.getInstance(context);
		db = dbh.getWritableDatabase();
	}

	public long insert(Tipo tipo) {
		ContentValues values = new ContentValues();
		values.put("descricao", tipo.getDescricao());
		long tipo_id = db.insert("tipo", null, values);
		return tipo_id;
	}

	public void update(Tipo tipo) {
		ContentValues values = new ContentValues();
		values.put("descricao", tipo.getDescricao());
		db.update("tipo", values, "_id" + "= ?",
				new String[] { String.valueOf(tipo.getId()) });
	}

	public void delete(long tipoId) {
		db.delete("tipo", "_id" + " = ?",
				new String[] { String.valueOf(tipoId) });
	}

	public Tipo getTipo(int tipoId) {
		String selectQuery = "SELECT * FROM tipo WHERE _id = " + tipoId;
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
			c.moveToFirst();

		Tipo tipo = new Tipo();
		if (c.getCount() > 0) {
			tipo.setId(c.getInt(c.getColumnIndex("_id")));
			tipo.setDescricao((c.getString(c.getColumnIndex("descricao"))));
		}
		return tipo;
	}

	public List<Tipo> getTipos() {
		List<Tipo> tipos = new ArrayList<Tipo>();
		String selectQuery = "SELECT  * FROM tipo";
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Tipo r = new Tipo();
				r.setId(c.getInt((c.getColumnIndex("_id"))));
				r.setDescricao((c.getString(c.getColumnIndex("descricao"))));
				tipos.add(r);
			} while (c.moveToNext());
		}

		return tipos;
	}

	public void deletarTodos() {
		Log.i(TAG, "VOU Deletar Todas os Rementes ");
		String query = "DELETE FROM tipo";
		db.execSQL(query);
		Log.i(TAG, "Todas os tipos foram deletados");
	}

	public void fecharDB() {
		if (db != null && db.isOpen())
			db.close();
	}

}
