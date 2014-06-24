package com.example.banco;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Disciplina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GerenciadorDisciplina {
	private static final String TAG = "GERENCIADOR_DISCIPLINA";
	private SQLiteDatabase db = null;

	private static GerenciadorDisciplina instance;

	public static GerenciadorDisciplina getInstance(Context context) {
		if (instance == null)
			instance = new GerenciadorDisciplina(context);
		return instance;
	}

	public GerenciadorDisciplina(Context context) {
		DataBaseHelper dbh = DataBaseHelper.getInstance(context);
		db = dbh.getWritableDatabase();
	}

	public long insert(Disciplina disciplina) {
		ContentValues values = new ContentValues();
		values.put("descricao", disciplina.getDescricao());
		long disciplina_id = db.insert("disciplina", null, values);
		return disciplina_id;
	}

	public void update(Disciplina disciplina) {
		ContentValues values = new ContentValues();
		values.put("descricao", disciplina.getDescricao());
		db.update("disciplina", values, "_id" + "= ?",
				new String[] { String.valueOf(disciplina.getId()) });
	}

	public void delete(long disciplinaId) {
		db.delete("disciplina", "_id" + " = ?",
				new String[] { String.valueOf(disciplinaId) });
	}

	public Disciplina getDisciplina(long disciplinaId) {
		String selectQuery = "SELECT * FROM disciplina WHERE _id = "
				+ disciplinaId;
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
			c.moveToFirst();

		Disciplina disciplina = new Disciplina();
		if (c.getCount() > 0) {
			disciplina.setId(c.getInt(c.getColumnIndex("_id")));
			disciplina.setDescricao((c.getString(c.getColumnIndex("descricao"))));
		}
		return disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		String selectQuery = "SELECT  * FROM disciplina";
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Disciplina r = new Disciplina();
				r.setId(c.getInt((c.getColumnIndex("_id"))));
				r.setDescricao((c.getString(c.getColumnIndex("descricao"))));
				disciplinas.add(r);
			} while (c.moveToNext());
		}

		return disciplinas;
	}

	public void deletarTodos() {
		Log.i(TAG, "VOU Deletar Todas os Rementes ");
		String query = "DELETE FROM disciplina";
		db.execSQL(query);
		Log.i(TAG, "Todas os disciplinas foram deletados");
	}

	public void fecharDB() {
		if (db != null && db.isOpen())
			db.close();
	}

}
