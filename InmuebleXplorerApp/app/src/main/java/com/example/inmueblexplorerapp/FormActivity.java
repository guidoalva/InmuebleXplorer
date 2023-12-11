package com.example.inmueblexplorerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FormActivity extends AppCompatActivity {

    private Spinner spProvincias;
    private Spinner spOperacion;
    private String[] op = {"Alquilar", "Comprar"};
    private int itemProvSelec;
    private int itemOpSelec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        spProvincias = findViewById(R.id.spProvincias);
        spOperacion = findViewById(R.id.spOperacion);

        ArrayAdapter<String> adaptadorProvincias = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getProvincias());
        spProvincias.setAdapter(adaptadorProvincias);
        ArrayAdapter<String> adaptadorOperacion = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, op);
        spOperacion.setAdapter(adaptadorOperacion);

        //Escuchador de evento del spinner provincia
        //Se crea una instancia anonima de la interfaz OnItemSelectedListene de AdapterView
        spProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Metodo que se ejecuta cuando el usuario selecciona un item
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Almacenar el índice seleccionado
                itemProvSelec = position; //Empieza desde 0
                //Toast.makeText(FormActivity.this, "Seleccion: " + itemProvSelec, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se realiza ninguna acción cuando no se selecciona nada
            }
        });

        //Escuchador de evento del spinner operacion
        spOperacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                itemOpSelec = position;
                //Toast.makeText(FormActivity.this, "Seleccion: " + itemOpSelec, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void explorar(View v) {
        Intent explorarActivity = new Intent(this, ExplorarActivity.class);
        explorarActivity.putExtra("ProvSelec", itemProvSelec);
        explorarActivity.putExtra("OpSelec", itemOpSelec);
        startActivity(explorarActivity);
    }

    public void publicarAqui(View v) {
        Intent publicarActivity = new Intent(this, PublicarActivity.class);
        startActivity(publicarActivity);
    }

    public String[] getProvincias() {
        SQLiteDatabase db = MainActivity.adminDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Nombre FROM Provincia", null);
        String[] vProvincias = new String[cursor.getCount()]; //vector con la dimesion de la consulta
        cursor.moveToFirst(); //Muevo el cursor a la primera fila

        try {
            int i = 0;
            do {
                vProvincias[i] = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"));
                i++;
            } while (cursor.moveToNext()); //Pasa a la siguiente fila
        } finally {
            cursor.close();
            db.close();
        }
        return vProvincias;
    }
}