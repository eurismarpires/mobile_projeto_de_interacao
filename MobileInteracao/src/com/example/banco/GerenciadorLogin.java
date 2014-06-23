package com.example.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.model.Login;
import com.example.model.Tipo;

public class GerenciadorLogin {

	private static final String TAG = "GERENCIADOR_LOGIN";
	private SQLiteDatabase db = null;

	private static GerenciadorLogin instance;

	public static GerenciadorLogin getInstance(Context context) {
		if (instance == null)
			instance = new GerenciadorLogin(context);
		return instance;
	}

	public GerenciadorLogin(Context context) {
		DataBaseHelper dbh = DataBaseHelper.getInstance(context);
		db = dbh.getWritableDatabase();
	}

	public long insertLogin(Login login) {
		ContentValues values = new ContentValues();
		values.put("matricula", login.getMatricula());
		values.put("senha", login.getSenha());
		values.put("usuario", login.getUsuario());
		long login_id = db.insert("login", null, values);
		return login_id;
	}

	public void update(Login login) {
		ContentValues values = new ContentValues();
		values.put("usuario", login.getUsuario());
		values.put("matricula", login.getMatricula());
		values.put("senha", login.getSenha());
		db.update("login", values, "_id" + "= ?",
				new String[] { String.valueOf(login.getId()) });
	}
	public Login getLogin(long loginId) {
	//	String selectQuery = "SELECT * FROM login WHERE _id = " + loginId;
		String selectQuery = "SELECT * FROM login WHERE _id = 1";
		Log.e(TAG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
			c.moveToFirst();

		Login login = new Login();
		login.setId(c.getInt(c.getColumnIndex("_id")));
		login.setMatricula((c.getString(c.getColumnIndex("matricula"))));
		login.setSenha((c.getString(c.getColumnIndex("senha"))));
		login.setUsuario((c.getString(c.getColumnIndex("usuario"))));			
		return login;
	}

	public void fecharDB() {
		if (db != null && db.isOpen())
			db.close();
	}
}
