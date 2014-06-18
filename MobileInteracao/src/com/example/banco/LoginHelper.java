package com.example.banco;

import com.example.model.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginHelper extends SQLiteOpenHelper {

	public static int version = 1;
	private final String TABLE_NAME = "LOGIN";
	private final String ID = "ID";
	private final String USUARIO = "USUARIO";
	private final String SENHA = "SENHA";
	private final String MATRICULA = "MATRICULA";
	private final String LOGADO = "N";

	public LoginHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public LoginHelper(Context context, String name) {
		this(context, name, version);
	}

	public LoginHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE LOGIN(ID INT, USUARIO VARCHAR(50), SENHA VARCHAR(50), MATRICULA VARCHAR(50)");
		System.out.println("create table");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("update table");
	}

	public void insert(LoginHelper helper, Login login) {
		ContentValues values = new ContentValues();
		values.put(ID, login.getId());
		values.put(USUARIO, login.getUsuario());
		values.put("MATRICULA", MATRICULA);
		values.put("LOGADO", "N");
		helper.getWritableDatabase().insert(TABLE_NAME, null, values);
	}

	public void update(LoginHelper helper, Login login) {
		ContentValues values = new ContentValues();
		values.put(USUARIO, login.getUsuario());
		values.put(MATRICULA, login.getMatricula());
		values.put(SENHA, login.getSenha());
		values.put("LOGADO", login.getLogado());
		helper.getWritableDatabase().update(TABLE_NAME, values, ID + "= ?",
				new String[] { String.valueOf(login.getId()) });		
	}

	public Login query(LoginHelper helper, int id) {
		Cursor cursor = helper.getReadableDatabase().query(TABLE_NAME,
				new String[] { USUARIO }, "ID = ?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex(USUARIO));
			return new Login(id, USUARIO,SENHA,MATRICULA,LOGADO );
		} else {
			return null;
		}
	}

	public void delete(LoginHelper helper, Login login) {
		helper.getWritableDatabase().delete(TABLE_NAME, "ID = ?",
				new String[] { String.valueOf(login.getId()) });
	}

}