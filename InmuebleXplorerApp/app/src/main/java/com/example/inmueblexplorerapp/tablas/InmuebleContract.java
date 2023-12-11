package com.example.inmueblexplorerapp.tablas;

import android.provider.BaseColumns;

public class InmuebleContract {

    private InmuebleContract() {
        //Al ser pirvado el constructor, no se puede instanciar desde fuera de la propia clase.
        //Esto se hace para evitar que alguien accidentalmente cree instancias de la clase contract.
        //Las clases contract generalmente contienen solo constantes y no deberían ser instanciadas.
    }

    public static class InmuebleEntry implements BaseColumns {
        // La interfaz BaseColumns proporciona automáticamente
        // dos columnas estándar llamadas _ID y _COUNT.
        public static final String TABLE_NAME = "Inmueble";
        public static final String _ID = "ID";
        public static final String COLUMN_NAME_PROPIETARIO = "Propietario";
        public static final String COLUMN_NAME_TIPO = "Tipo";
        public static final String COLUMN_NAME_METROS = "Metros";
        public static final String COLUMN_NAME_AMBIENTES = "Ambientes";
        public static final String COLUMN_NAME_ANTIGUEDAD = "Antiguedad";
        public static final String COLUMN_NAME_PRECIO = "Precio";
        public static final String COLUMN_NAME_IMAGEN = "Imagen";
        public static final String COLUMN_NAME_ESTADO = "Estado";
        public static final String COLUMN_NAME_PROVINCIA = "Provincia";
        public static final String COLUMN_NAME_DIRECCION = "Direccion";
        public static final String COLUMN_NAME_LATITUD = "Latitud";
        public static final String COLUMN_NAME_LONGITUD = "Longitud";
    }

}
