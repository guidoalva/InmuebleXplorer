package com.example.inmueblexplorerapp.tablas;

import android.provider.BaseColumns;

public class TipoContract {

    private TipoContract(){

    }

    public static class TipoEntry implements BaseColumns {
        public static final String TABLE_NAME = "Tipo";
        public static final String _ID = "ID";
        public static final String COLUMN_NAME_DESCRIPCION = "Descripcion";
    }
}
