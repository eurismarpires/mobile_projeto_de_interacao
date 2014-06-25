package com.example.banco;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.model.Disciplina;
import com.example.model.Notificacao;
import com.example.model.Remetente;
import com.example.model.Tipo;

public class GerenciadorNotificacoes {

	private static final String TAG = "GerenciadorNotificacoes";
	private SQLiteDatabase db = null;
	private static final String TABELA = "notificacao";
	private static GerenciadorNotificacoes instance;
	private Context context;

	public static GerenciadorNotificacoes getInstance(Context context) {
		if (instance == null)
			instance = new GerenciadorNotificacoes(context);
		return instance;
	}

	public GerenciadorNotificacoes(Context context) {
		this.context = context;
		DataBaseHelper dbh = DataBaseHelper.getInstance(context);
		db = dbh.getWritableDatabase();
	}

	// db.execSQL("CREATE TABLE notificacao (_id INTEGER, lida INTEGER,data DATETIME, mensagem TEXT, id_remetente INTEGER, id_tipo INTEGER)");
	public void inserir(Notificacao notificacao) {
		Log.i(TAG, "VOU INSERIR");
		ContentValues values = new ContentValues();
		values.put("lida", notificacao.getLida());
		values.put("data", notificacao.getData());
		values.put("mensagem", notificacao.getMensagem());
		values.put("id_remetente", notificacao.getRemetente().getId());
		values.put("id_tipo", notificacao.getTipo().getId());
		values.put("id_disciplina", notificacao.getDisciplina().getId());
		db.insert(TABELA, null, values);
		Log.i(TAG, "NOTIFICACAO inserida: " + notificacao.getMensagem());
	}

	public void alterar(Notificacao notificacao) {
		Log.i(TAG, "VOU ALTERAR");
		ContentValues values = new ContentValues();
		values.put("lida", notificacao.getLida());
		values.put("data", notificacao.getData());
		values.put("mensagem", notificacao.getMensagem());
		values.put("id_remetente", notificacao.getRemetente().getId());
		values.put("id_tipo", notificacao.getTipo().getId());
		values.put("id_disciplina", notificacao.getDisciplina().getId());
		String[] args = { notificacao.getId().toString() };
		db.update(TABELA, values, "_id=?", args);
		Log.i(TAG, "Notifica��o alterada: " + notificacao.getMensagem());
	}

	public void deletar(Notificacao notificacao) {

		String[] args = { notificacao.getId().toString() };

		db.delete(TABELA, "id=?", args);

		Log.i(TAG, "Notifica��o deletada: " + notificacao.getMensagem());
	}

	public void deletarTodas() {
		Log.i(TAG, "VOU Deletar Todas as notifica��es ");
		String query = "DELETE FROM " + TABELA;
		db.execSQL(query);
		Log.i(TAG, "Todas as notifica��es foram deletadas: ");
	}

	public List<Notificacao> getNotificacoes(String ordem, String filtro) {
		List<Notificacao> note = new ArrayList<Notificacao>();
		
		
		
	//	String selectQuery = "SELECT * FROM notificacao";
		
		String selectQuery = "SELECT _id, lida,strftime('%d/%m/%Y %H:%M',data) data, mensagem, id_remetente, id_tipo, id_disciplina FROM notificacao " + filtro  + " "+ ordem;
		
		
		
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Notificacao n = new Notificacao();
				n.setId(c.getInt(c.getColumnIndex("_id")));

				n.setData(c.getString(c.getColumnIndex("data")));
				n.setLida(c.getInt(c.getColumnIndex("lida")));
				n.setMensagem(c.getString(c.getColumnIndex("mensagem")));

				// busca o remetente da mensagem
				GerenciadorRemetente gr = new GerenciadorRemetente(context);
				Remetente r = new Remetente();
				r = gr.getRemetente(c.getInt(c.getColumnIndex("id_remetente")));
				n.setRemetente(r);

				// busca o tipo da mensagem
				GerenciadorTipo gt = new GerenciadorTipo(context);
				Tipo t = new Tipo();
				t = gt.getTipo(c.getInt(c.getColumnIndex("id_tipo")));
				n.setTipo(t);

				// busca disciplina
				GerenciadorDisciplina gd = new GerenciadorDisciplina(context);
				Disciplina dc = new Disciplina();
				dc = gd.getDisciplina(c.getInt(c
						.getColumnIndex("id_disciplina")));
				n.setDisciplina(dc);

				note.add(n);
			} while (c.moveToNext());
		}

		return note;
	}

	public Notificacao getNotificacao(long idNotificacao) {
		String selectQuery = "SELECT * FROM notificacao WHERE _id = "
				+ idNotificacao;
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
			c.moveToFirst();

		Notificacao n = new Notificacao();
		if (c.getCount() > 0) {
			n.setId(c.getInt(c.getColumnIndex("_id")));
			n.setData(c.getString(c.getColumnIndex("data")));
			n.setLida(c.getInt(c.getColumnIndex("lida")));
			n.setMensagem(c.getString(c.getColumnIndex("mensagem")));

			// busca o remetente da mensagem
			GerenciadorRemetente gr = new GerenciadorRemetente(context);
			Remetente r = new Remetente();
			r = gr.getRemetente(c.getInt(c.getColumnIndex("id_remetente")));
			n.setRemetente(r);

			// busca o tipo da mensagem
			GerenciadorTipo gt = new GerenciadorTipo(context);
			Tipo t = new Tipo();
			t = gt.getTipo(c.getInt(c.getColumnIndex("id_tipo")));
			n.setTipo(t);

			// busca disciplina
			GerenciadorDisciplina gd = new GerenciadorDisciplina(context);
			Disciplina dc = new Disciplina();
			dc = gd.getDisciplina(c.getInt(c.getColumnIndex("id_disciplina")));
			n.setDisciplina(dc);
		}
		return n;
	}

	public void fecharDB() {
		if (db != null && db.isOpen())
			db.close();
	}
}
