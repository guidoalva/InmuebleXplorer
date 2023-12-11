package com.example.inmueblexplorerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inmueblexplorerapp.tablas.EstadoContract;
import com.example.inmueblexplorerapp.tablas.InmuebleContract;
import com.example.inmueblexplorerapp.tablas.InmuebleXplorerDbHelper;
import com.example.inmueblexplorerapp.tablas.PropietarioContract;
import com.example.inmueblexplorerapp.tablas.ProvinciaContract;
import com.example.inmueblexplorerapp.tablas.TipoContract;

public class MainActivity extends AppCompatActivity {

    public static InmuebleXplorerDbHelper adminDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminDB = new InmuebleXplorerDbHelper(this);
        //adminDB.getReadableDatabase();
    }

    public void iniciarFormulario(View v) {
        Intent intentoActForm = new Intent(this, FormActivity.class);
        startActivity(intentoActForm);
    }

    public void query(View v) {
        SQLiteDatabase db = adminDB.getWritableDatabase();

        db.execSQL(InmuebleXplorerDbHelper.SQL_DELETE_PROPIETARIO_ENTRIES);
        db.execSQL(InmuebleXplorerDbHelper.SQL_CREATE_PROPIETARIO_ENTRIES);
        cargarPropietarios();
        Toast.makeText(this, "Tabla propietario reseteada", Toast.LENGTH_SHORT).show();

        /*db.execSQL(InmuebleXplorerDbHelper.SQL_DELETE_INMUEBLE_ENTRIES);
        db.execSQL(InmuebleXplorerDbHelper.SQL_CREATE_INMUEBLE_ENTRIES);
        cargarInmuebles();
        Toast.makeText(this, "Tabla inmueble reseteada", Toast.LENGTH_SHORT).show();*/

        db.close();
        adminDB.getReadableDatabase();
    }

    public void cargarPropietarios() {
        // Coloco la base de datos en modo escritura
        SQLiteDatabase db = adminDB.getWritableDatabase();

        ContentValues v1 = new ContentValues();
        v1.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Aldana");
        v1.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 38643222);
        v1.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "aldiuz@gmail.com");
        v1.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3854965710");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v1);

        ContentValues v2 = new ContentValues();
        v2.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Carlos");
        v2.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 40555321);
        v2.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "carlos123@gmail.com");
        v2.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3854795214");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v2);

        ContentValues v3 = new ContentValues();
        v3.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Gisella");
        v3.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 25369852);
        v3.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "gisevaz@gmail.com");
        v3.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3856201496");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v3);

        ContentValues v4 = new ContentValues();
        v4.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Juan");
        v4.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 40223514);
        v4.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "juancito@gmail.com");
        v4.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3855630012");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v4);

        ContentValues v5 = new ContentValues();
        v5.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Matias");
        v5.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 35896321);
        v5.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "matiasale@gmail.com");
        v5.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3856982255");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v5);

        ContentValues v6 = new ContentValues();
        v6.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Sergio");
        v6.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 33520147);
        v6.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "segioalva@gmail.com");
        v6.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3855880050");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v6);

        ContentValues v7 = new ContentValues();
        v7.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Cecilia");
        v7.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 25444320);
        v7.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "cecichaz@gmail.com");
        v7.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3854720255");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v7);

        ContentValues v8 = new ContentValues();
        v8.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Emir");
        v8.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 40863222);
        v8.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "emirgonz@gmail.com");
        v8.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3854779966");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v8);

        ContentValues v9 = new ContentValues();
        v9.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Florencia");
        v9.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 41223695);
        v9.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "florgonz@gmail.com");
        v9.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3854782244");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v9);

        ContentValues v10 = new ContentValues();
        v10.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Maria");
        v10.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 42335764);
        v10.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "mariasol@gmail.com");
        v10.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3856995544");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v10);

        ContentValues v11 = new ContentValues();
        v11.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Mario");
        v11.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 25123557);
        v11.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "mariomont@gmail.com");
        v11.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3854119966");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v11);

        ContentValues v12 = new ContentValues();
        v12.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_NOMBRE, "Rocio");
        v12.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_DNI, 32132100);
        v12.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_MAIL, "roale@gmail.com");
        v12.put(PropietarioContract.PropietarioEntry.COLUMN_NAME_TELEFONO, "3854223300");
        db.insert(PropietarioContract.PropietarioEntry.TABLE_NAME, null, v12);

        db.close();
    }

    public void cargarInmuebles() {
        // Coloco la base de datos en modo escritura
        SQLiteDatabase db = adminDB.getWritableDatabase();
        ContentValues v1 = new ContentValues();
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO, 7);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO, 2);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS, 100);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES, 3);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD, 5);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO, 50000);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN, R.drawable.inm1);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO, 1);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA, 2);
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION, "Independencia 3800");
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD, "-27.826429");
        v1.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD, "-64.2372443");
        db.insert(InmuebleContract.InmuebleEntry.TABLE_NAME, null, v1);

        ContentValues v2 = new ContentValues();
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO, 6);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO, 2);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS, 120);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES, 4);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD, 10);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO, 700000);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN, R.drawable.inm2);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO, 2);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA, 2);
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION, "Av. Belgrano Sur 2750");
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD, "-27.8097874");
        v2.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD, "-64.2495507");
        db.insert(InmuebleContract.InmuebleEntry.TABLE_NAME, null, v2);

        ContentValues v3 = new ContentValues();
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO, 4);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO, 1);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS, 53);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES, 2);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD, 7);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO, 1500000);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN, R.drawable.inm3);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO, 2);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA, 1);
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION, "Corrientes 1236");
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD, "-34.604007");
        v3.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD, "-58.4026368");
        db.insert(InmuebleContract.InmuebleEntry.TABLE_NAME, null, v3);

        ContentValues v4 = new ContentValues();
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO, 10);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO, 2);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS, 130);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES, 3);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD, 15);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO, 70000);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN, R.drawable.inm4);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO, 1);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA, 1);
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION, "Misiones 144");
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD, "-34.6116499");
        v4.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD, "-58.4068991");
        db.insert(InmuebleContract.InmuebleEntry.TABLE_NAME, null, v4);

        ContentValues v5 = new ContentValues();
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO, 11);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO, 1);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS, 73);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES, 5);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD, 8);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO, 60000);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN, R.drawable.inm5);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO, 1);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA, 2);
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION, "Av. Solis Sur 355");
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD, "-27.8178129");
        v5.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD, "-64.2465086");
        db.insert(InmuebleContract.InmuebleEntry.TABLE_NAME, null, v5);

        ContentValues v6 = new ContentValues();
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROPIETARIO, 5);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_TIPO, 2);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_METROS, 77);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_AMBIENTES, 2);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ANTIGUEDAD, 2);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PRECIO, 1500000);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_IMAGEN, R.drawable.inm6);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_ESTADO, 2);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_PROVINCIA, 1);
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_DIRECCION, "Av. Libertador 500");
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LATITUD, "-34.5893666");
        v6.put(InmuebleContract.InmuebleEntry.COLUMN_NAME_LONGITUD, "-58.3836739");
        db.insert(InmuebleContract.InmuebleEntry.TABLE_NAME, null, v6);

        db.close();
    }

    public void cargarProvincias() {
        // Coloco la base de datos en modo escritura
        SQLiteDatabase db = adminDB.getWritableDatabase();

        //  Creo un nuevo map de valores para la primera provincia
        ContentValues ba = new ContentValues();
        ba.put(ProvinciaContract.ProninciaEntry.COLUMN_NAME_NOMBRE, "Buenos Aires");
        // Inserto la primera tupla en la tabla Provincia
        db.insert(ProvinciaContract.ProninciaEntry.TABLE_NAME, null, ba);

        ContentValues sa = new ContentValues();
        sa.put(ProvinciaContract.ProninciaEntry.COLUMN_NAME_NOMBRE, "Santiago del Estero");
        // Inserto la primera tupla en la tabla Provincia
        db.insert(ProvinciaContract.ProninciaEntry.TABLE_NAME, null, sa);
        db.close();
    }

    public void cargarTipo() {
        SQLiteDatabase db = adminDB.getWritableDatabase();
        ContentValues dpto = new ContentValues();
        dpto.put(TipoContract.TipoEntry.COLUMN_NAME_DESCRIPCION, "Departamento");
        db.insert(TipoContract.TipoEntry.TABLE_NAME, null, dpto);
        ContentValues vivienda = new ContentValues();
        vivienda.put(TipoContract.TipoEntry.COLUMN_NAME_DESCRIPCION, "Vivienda");
        db.insert(TipoContract.TipoEntry.TABLE_NAME, null, vivienda);
        db.close();
    }

    public void cargarEstado() {
        SQLiteDatabase db = adminDB.getWritableDatabase();
        ContentValues alq = new ContentValues();
        alq.put(EstadoContract.EstadoEntry.COLUMN_NAME_DESCRIPCION, "Alquiler");
        db.insert(EstadoContract.EstadoEntry.TABLE_NAME, null, alq);
        ContentValues venta = new ContentValues();
        venta.put(EstadoContract.EstadoEntry.COLUMN_NAME_DESCRIPCION, "Venta");
        db.insert(EstadoContract.EstadoEntry.TABLE_NAME, null, venta);
        db.close();
    }
}