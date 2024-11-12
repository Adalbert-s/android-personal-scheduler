package com.example.appagenda.database.compromisso;

public class CompromissoDBSchema {

    private CompromissoDBSchema() {}

    public static final class CompromissoTable {
        public static final String TABLE_NAME = "compromissos";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_HORA = "hora";
        public static final String COLUMN_DESCRICAO = "descricao";
        public static final String COLUMN_USUARIO_ID = "usuario_id";
    }
}
