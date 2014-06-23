package com.example.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.model.Login;
import com.example.model.Tipo;

public class DataBaseHelper extends SQLiteOpenHelper {
	// Logcat tag
	private static final String TAG = "DatabaseHelper";
	private static DataBaseHelper instance;

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "banco.db";

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static DataBaseHelper getInstance(Context context) {
		if (instance == null)
			instance = new DataBaseHelper(context);
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "CREATE TABLE LOGIN");
		db.execSQL("CREATE TABLE login (_id INTEGER PRIMARY KEY, matricula TEXT, senha TEXT, usuario TEXT)");
		Log.i(TAG, "CREATE TABLE TIPO");
		db.execSQL("CREATE TABLE tipo (_id INTEGER PRIMARY KEY, descricao TEXT)");
		Log.i(TAG, "CREATE TABLE REMETENTE");
		db.execSQL("CREATE TABLE remetente (_id INTEGER PRIMARY KEY, nome TEXT)");
		Log.i(TAG, "CREATE TABLE NOTIFICACAO");
		db.execSQL("CREATE TABLE notificacao (_id INTEGER, lida INTEGER,data TEXT, mensagem TEXT, id_remetente INTEGER, id_tipo INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS login");
		db.execSQL("DROP TABLE IF EXISTS tipo");
		db.execSQL("DROP TABLE IF EXISTS remetente");
		db.execSQL("DROP TABLE IF EXISTS notificacao");

	}

}
