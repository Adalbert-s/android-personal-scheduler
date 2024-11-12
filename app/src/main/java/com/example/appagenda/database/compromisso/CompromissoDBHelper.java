package com.example.appagenda.database.compromisso;
import com.example.appagenda.database.usuario.UsuarioDBSchema;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompromissoDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_COMPROMISSOS =
            "CREATE TABLE " + CompromissoDBSchema.CompromissoTable.TABLE_NAME + " (" +
                    CompromissoDBSchema.CompromissoTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CompromissoDBSchema.CompromissoTable.COLUMN_DATA + " TEXT NOT NULL, " +
                    CompromissoDBSchema.CompromissoTable.COLUMN_HORA + " TEXT NOT NULL, " +
                    CompromissoDBSchema.CompromissoTable.COLUMN_DESCRICAO + " TEXT, " +
                    CompromissoDBSchema.CompromissoTable.COLUMN_USUARIO_ID + " INTEGER, " +
                    "FOREIGN KEY(" + CompromissoDBSchema.CompromissoTable.COLUMN_USUARIO_ID + ") REFERENCES " +
                    UsuarioDBSchema.UsuarioTable.TABLE_NAME + "(" + UsuarioDBSchema.UsuarioTable.COLUMN_ID + ")" +
                    ");";


    private static final String SQL_DELETE_COMPROMISSOS =
            "DROP TABLE IF EXISTS " + CompromissoDBSchema.CompromissoTable.TABLE_NAME;

    public CompromissoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COMPROMISSOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CompromissoDBSchema.CompromissoTable.TABLE_NAME);
        onCreate(db);
    }
}
