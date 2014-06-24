package com.example.banco;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Remetente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GerenciadorRemetente {
	private static final String TAG = "GERENCIADOR_REMETENTE";
	private SQLiteDatabase db = null;

	private static GerenciadorRemetente instance;

	public static GerenciadorRemetente getInstance(Context context) {
		if (instance == null)
			instance = new GerenciadorRemetente(context);
		return instance;
	}

	public GerenciadorRemetente(Context context) {
		DataBaseHelper dbh = DataBaseHelper.getInstance(context);
		db = dbh.getWritableDatabase();
	}

	public long insert(Remetente remetente) {
		ContentValues values = new ContentValues();
		values.put("nome", remetente.getNome());
		long remetente_id = db.insert("remetente", null, values);
		return remetente_id;
	}

	public void update(Remetente remetente) {
		ContentValues values = new ContentValues();
		values.put("nome", remetente.getNome());
		db.update("remetente", values, "_id" + "= ?",
				new String[] { String.valueOf(remetente.getId()) });
	}

	public void delete(long remetenteId) {
		db.delete("remetente", "_id" + " = ?",
				new String[] { String.valueOf(remetenteId) });
	}

	public Remetente getRemetente(long remetenteId) {
		String selectQuery = "SELECT * FROM remetente WHERE _id = "
				+ remetenteId;
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
			c.moveToFirst();
		Remetente remetente = new Remetente();
		if (c.getCount() > 0) {
			remetente.setId(c.getInt(c.getColumnIndex("_id")));
			remetente.setNome((c.getString(c.getColumnIndex("nome"))));
		}
		return remetente;
	}

	public List<Remetente> getRemetentes() {
		List<Remetente> remetentes = new ArrayList<Remetente>();
		String selectQuery = "SELECT  * FROM remetente";
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Remetente r = new Remetente();
				r.setId(c.getInt((c.getColumnIndex("_id"))));
				r.setNome((c.getString(c.getColumnIndex("nome"))));
				remetentes.add(r);
			} while (c.moveToNext());
		}

		return remetentes;
	}

	public void deletarTodos() {
		Log.i(TAG, "VOU Deletar Todas os Rementes ");
		String query = "DELETE FROM REMETENTE";
		db.execSQL(query);
		Log.i(TAG, "Todas os remetentes foram deletados");
	}

	public void fecharDB() {
		if (db != null && db.isOpen())
			db.close();
	}

}
