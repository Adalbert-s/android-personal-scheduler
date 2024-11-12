package com.example.appagenda.database.usuario;

import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_USUARIOS =
            "CREATE TABLE " + UsuarioDBSchema.UsuarioTable.TABLE_NAME + " (" +
                    UsuarioDBSchema.UsuarioTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UsuarioDBSchema.UsuarioTable.COLUMN_NOME + " TEXT NOT NULL, " +
                    UsuarioDBSchema.UsuarioTable.COLUMN_SENHA + " TEXT NOT NULL" +
                    ");";

    private static final String SQL_DELETE_USUARIOS =
            "DROP TABLE IF EXISTS " + UsuarioDBSchema.UsuarioTable.TABLE_NAME;

    public UsuarioDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USUARIOS);
        onCreate(db);
    }

    // Método para verificar o login

    public boolean verificarLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                UsuarioDBSchema.UsuarioTable.TABLE_NAME,
                new String[] {UsuarioDBSchema.UsuarioTable.COLUMN_ID, UsuarioDBSchema.UsuarioTable.COLUMN_NOME},
                UsuarioDBSchema.UsuarioTable.COLUMN_NOME + " = ? AND " + UsuarioDBSchema.UsuarioTable.COLUMN_SENHA + " = ?",
                new String[] { username, password },
                null, null, null);

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }
}
