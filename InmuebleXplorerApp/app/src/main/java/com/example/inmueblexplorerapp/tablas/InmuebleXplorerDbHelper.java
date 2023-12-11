package com.example.inmueblexplorerapp.tablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class InmuebleXplorerDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "InmuebleXplorer.db";

    public InmuebleXplorerDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String SQL_CREATE_INMUEBLE_ENTRIES =
            "CREATE TABLE " + InmuebleContract.InmuebleEntry.TABLE_NAME + " (" +
                    InmuebleContract.InmuebleEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO + " INTEGER, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO + " INTEGER, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS + " DECIMAL(10,2), " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES + " INTEGER, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD + " INTEGER, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO + " DECIMAL(10,2), " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN + " INTEGER, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO + " INTEGER, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA + " INTEGER, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION + " TEXT, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD + " TEXT, " +
                    InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD + " TEXT, " +
                    "FOREIGN KEY(" + InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO + ") REFERENCES " +
                    PropietarioContract.PropietarioEntry.TABLE_NAME + "(" + PropietarioContract.PropietarioEntry._ID + "))";
    public static final String SQL_CREATE_PROPIETARIO_ENTRIES =
            "CREATE TABLE " + PropietarioContract.PropietarioEntry.TABLE_NAME + " (" +
                    PropietarioContract.PropietarioEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI + " INTEGER UNIQUE, " +
                    PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE + " TEXT, " +
                    PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL + " TEXT, " +
                    PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO + " TEXT)";
    private static final String SQL_CREATE_TIPO_ENTRIES =
            "CREATE TABLE " + TipoContract.TipoEntry.TABLE_NAME + " (" +
                    TipoContract.TipoEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    TipoContract.TipoEntry.COLUMN_NAME_DESCRIPCION + " TEXT)";
    private static final String SQL_CREATE_ESTADO_ENTRIES =
            "CREATE TABLE " + EstadoContract.EstadoEntry.TABLE_NAME + " (" +
                    EstadoContract.EstadoEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    EstadoContract.EstadoEntry.COLUMN_NAME_DESCRIPCION + " TEXT)";
    private static final String SQL_CREATE_PROVINCIA_ENTRIES =
            "CREATE TABLE " + ProvinciaContract.ProninciaEntry.TABLE_NAME + " (" +
                    ProvinciaContract.ProninciaEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    ProvinciaContract.ProninciaEntry.COLUMN_NAME_NOMBRE + " TEXT)";
    public static final String SQL_DELETE_INMUEBLE_ENTRIES =
            "DROP TABLE IF EXISTS " + InmuebleContract.InmuebleEntry.TABLE_NAME;
    public static final String SQL_DELETE_PROPIETARIO_ENTRIES =
            "DROP TABLE IF EXISTS " + PropietarioContract.PropietarioEntry.TABLE_NAME;

    //Metodo para insertar un nuevo propietario y devuelve su ID
    public long insertarPropietario(int dni, String nombre, String mail, String telefono) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, dni);
        valores.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, nombre);
        valores.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, mail);
        valores.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, telefono);

        // Insertar el nuevo propietario y obtener su ID
        long propietarioId = db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, valores);
        db.close();
        return propietarioId;
    }

    public void insertarInmueble(Inmueble inmueble) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO, inmueble.getPropietario());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO, inmueble.getTipo());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS, inmueble.getMetros());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES, inmueble.getAmbientes());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD, inmueble.getAntiguedad());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO, inmueble.getPrecio());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO, inmueble.getEstado());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA, inmueble.getProvincia());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION, inmueble.getDireccion());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD, inmueble.getLatitud());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD, inmueble.getLongitud());
        valores.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN, inmueble.getImgLogotipo()); //Se carga el logotipo de la app
        db.insert(InmuebleContract.InmuebleEntry.TABLE_NAME, null, valores);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_INMUEBLE_ENTRIES);
        db.execSQL(SQL_CREATE_PROPIETARIO_ENTRIES);
        db.execSQL(SQL_CREATE_TIPO_ENTRIES);
        db.execSQL(SQL_CREATE_PROVINCIA_ENTRIES);
        db.execSQL(SQL_CREATE_ESTADO_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se usa cuando se cambia la version de la base de datos
    }
}
