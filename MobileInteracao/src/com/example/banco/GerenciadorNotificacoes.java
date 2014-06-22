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




import com.example.model.Notificacao;

public class GerenciadorNotificacoes extends SQLiteOpenHelper {
	
	private static final String NOME_BANCO = "notificacao.db";
	private static final String TABELA = "NOTIFICACAO";
	private static final int VERSAO_SCHEMA = 1;
	private static final String TAG = "GerenciadorNotificacoes";
	
	public GerenciadorNotificacoes(Context context) {
		super(context, NOME_BANCO, null, VERSAO_SCHEMA);		
	}	

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "VOU CRIAR A TABELA");
		db.execSQL("CREATE TABLE " + TABELA + " (_id INTEGER PRIMARY KEY," +
				" data TEXT,lida INTEGER,mensagem TEXT, remetente TEXT, tipo TEXT);");		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		Log.i(TAG, "VOU DELETAR A TABELA");
		db.execSQL("DROP TABLE IF EXISTS " + TABELA);

	}
	
	public void inserir(Notificacao notificacao) {
		Log.i(TAG, "VOU INSERIR");
		// Objeto para armazenar os valores dos campos
		ContentValues values = new ContentValues();

		// Definicao de valores dos campos da tabela
		
		values.put("data", notificacao.getData());
		values.put("lida", notificacao.getLida());
		values.put("mensagem", notificacao.getMensagem());
		values.put("remetente", notificacao.getRemetente());
		values.put("tipo", notificacao.getTipo());		

		// Inserir dados da notificacao no BD
		getWritableDatabase().insert(TABELA, null, values);
		Log.i(TAG, "NOTIFICACAO inserida: " + notificacao.getMensagem());
	}	
	
	public void alterar(Notificacao notificacao) {
		Log.i(TAG, "VOU ALTERAR");
		ContentValues values = new ContentValues();

		values.put("data", notificacao.getData());
		values.put("lida", notificacao.getLida());
		values.put("mensagem", notificacao.getMensagem());
		values.put("remetente", notificacao.getRemetente());
		values.put("tipo", notificacao.getTipo());	

		String[] args = {notificacao.getId().toString()};

		getWritableDatabase().update(TABELA, values, "id=?", args);
		Log.i(TAG, "Notificação alterada: " + notificacao.getMensagem());
	}	
	public void deletar(Notificacao notificacao) {
		
		String[] args = { notificacao.getId().toString() };

		
		getWritableDatabase().delete(TABELA, "id=?", args);

		Log.i(TAG, "Notificação deletada: " + notificacao.getMensagem());
	}	
	public void deletarTodas() {
		Log.i(TAG, "VOU Deletar Todas as notificações ");
		SQLiteDatabase db = getWritableDatabase();
		String query = "DELETE FROM " + TABELA;
		db.execSQL(query);
		
		
		Log.i(TAG, "Todas as notificações foram deletadas: ");
	}	
	public List<Notificacao> listar() {		
		Log.i(TAG, "VOU LISTAR TUDO");
		List<Notificacao> lista = new ArrayList<Notificacao>();
		String sql = "Select * from notificacao order by mensagem";
		// Objeto que recebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);

		try {
			while (cursor.moveToNext()) {
				Notificacao notificacao = new Notificacao();
				notificacao.setId(cursor.getLong(0));
				notificacao.setData(cursor.getString(1));				
				notificacao.setLida(cursor.getInt(2));
				notificacao.setMensagem(cursor.getString(3));
				notificacao.setRemetente(cursor.getString(4));
				notificacao.setTipo(cursor.getString(5));
				lista.add(notificacao);
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		} finally {
			cursor.close();
		}
		return lista;
	}
}
