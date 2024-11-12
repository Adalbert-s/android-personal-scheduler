package com.example.appagenda.database.usuario;

public class UsuarioDBSchema {

    private UsuarioDBSchema() {}

    public static final class UsuarioTable{
        public static final String TABLE_NAME = "usuarios";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_SENHA = "senha";

    }
}
