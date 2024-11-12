package com.example.appagenda.database.compromisso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appagenda.model.Compromisso;

import java.util.ArrayList;
import java.util.List;

public class CompromissoDB {
    private final SQLiteDatabase db;

    public CompromissoDB(Context context) {
        CompromissoDBHelper dbHelper = new CompromissoDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long inserirCompromisso(Compromisso compromisso, long usuarioId) {
        ContentValues values = new ContentValues();
        values.put(CompromissoDBSchema.CompromissoTable.COLUMN_DATA, compromisso.getData());
        values.put(CompromissoDBSchema.CompromissoTable.COLUMN_HORA, compromisso.getHora());
        values.put(CompromissoDBSchema.CompromissoTable.COLUMN_DESCRICAO, compromisso.getDescricao());
        values.put(CompromissoDBSchema.CompromissoTable.COLUMN_USUARIO_ID, usuarioId);

        return db.insert(CompromissoDBSchema.CompromissoTable.TABLE_NAME, null, values);
    }


    public List<Compromisso> listarCompromissos() {
        List<Compromisso> lista = new ArrayList<>();
        String[] colunas = {
                CompromissoDBSchema.CompromissoTable.COLUMN_ID,
                CompromissoDBSchema.CompromissoTable.COLUMN_DATA,
                CompromissoDBSchema.CompromissoTable.COLUMN_HORA,
                CompromissoDBSchema.CompromissoTable.COLUMN_DESCRICAO
        };

        Cursor cursor = db.query(
                CompromissoDBSchema.CompromissoTable.TABLE_NAME,
                colunas,
                null,
                null,
                null,
                null,
                CompromissoDBSchema.CompromissoTable.COLUMN_DATA + " ASC, " +
                        CompromissoDBSchema.CompromissoTable.COLUMN_HORA + " ASC"
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow(CompromissoDBSchema.CompromissoTable.COLUMN_DATA));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(CompromissoDBSchema.CompromissoTable.COLUMN_HORA));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(CompromissoDBSchema.CompromissoTable.COLUMN_DESCRICAO));
                lista.add(new Compromisso(data, hora, descricao));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lista;
    }

    public List<Compromisso> listarCompromissosDoUsuario(long usuarioId) {
        List<Compromisso> compromissos = new ArrayList<>();

        String selection = CompromissoDBSchema.CompromissoTable.COLUMN_USUARIO_ID + " = ?";
        String[] selectionArgs = { String.valueOf(usuarioId) };

        Cursor cursor = db.query(
                CompromissoDBSchema.CompromissoTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow(CompromissoDBSchema.CompromissoTable.COLUMN_DATA));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(CompromissoDBSchema.CompromissoTable.COLUMN_HORA));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(CompromissoDBSchema.CompromissoTable.COLUMN_DESCRICAO));
                compromissos.add(new Compromisso(data, hora, descricao));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return compromissos;
    }

}
