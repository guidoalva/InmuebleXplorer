package com.example.inmueblexplorerapp.tablas;

import android.provider.BaseColumns;

public class PropietarioContract {

    private PropietarioContract() {
    }

    public static class PropietarioEntry implements BaseColumns {
        public static final String TABLE_NAME = "Propietario";
        public static final String _ID = "ID";
        public static final String COLUMN_NAME_DNI = "Dni";
        public static final String COLUMN_NAME_NOMBRE = "Nombre";
        public static final String COLUMN_NAME_MAIL = "Mail";
        public static final String COLUMN_NAME_TELEFONO = "Telefono";

    }
}
