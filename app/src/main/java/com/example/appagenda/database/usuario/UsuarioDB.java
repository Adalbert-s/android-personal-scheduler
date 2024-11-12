package com.example.appagenda.database.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appagenda.model.Usuario;

public class UsuarioDB {
    private final SQLiteDatabase db;

    public UsuarioDB(Context context) {
        UsuarioDBHelper dbHelper = new UsuarioDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long inserirUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(UsuarioDBSchema.UsuarioTable.COLUMN_NOME, usuario.getNome());
        // Adicione mais valores conforme necessário

        return db.insert(UsuarioDBSchema.UsuarioTable.TABLE_NAME, null, values);
    }

    public Usuario buscarUsuario(String nome) {
        Usuario usuario = null;
        String[] colunas = {
                UsuarioDBSchema.UsuarioTable.COLUMN_ID,
                UsuarioDBSchema.UsuarioTable.COLUMN_NOME
                // Adicione mais colunas conforme necessário
        };

        String selection = UsuarioDBSchema.UsuarioTable.COLUMN_NOME + " = ?";
        String[] selectionArgs = { nome };

        Cursor cursor = db.query(
                UsuarioDBSchema.UsuarioTable.TABLE_NAME,
                colunas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String nomeUsuario = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioDBSchema.UsuarioTable.COLUMN_NOME));
            usuario = new Usuario(nomeUsuario);
            cursor.close();
        }
        return usuario;
    }
}
