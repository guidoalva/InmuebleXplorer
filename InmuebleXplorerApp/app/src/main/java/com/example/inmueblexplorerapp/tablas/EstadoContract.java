package com.example.inmueblexplorerapp.tablas;

import android.provider.BaseColumns;

public class EstadoContract {

    private EstadoContract() {

    }
    public static class EstadoEntry implements BaseColumns {
        public static final String TABLE_NAME = "Estado";
        public static final String _ID = "ID";
        public static final String COLUMN_NAME_DESCRIPCION = "Descripcion";

    }
}
