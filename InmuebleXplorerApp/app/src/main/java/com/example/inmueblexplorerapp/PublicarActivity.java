package com.example.inmueblexplorerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inmueblexplorerapp.tablas.Inmueble;
import com.google.android.material.appbar.MaterialToolbar;

public class PublicarActivity extends AppCompatActivity {

    private EditText etNombre, etMail, etTelefono, etMetros, etAmb, etAnt, etPrecio, etDireccion, etDni;
    private Spinner spTipo, spProvincias;
    private RadioGroup rgEstado;
    private Button btnSubirImagen;
    private int itemTipoSelec; //Variable que guarda la eleccion del Spinner tipo. 0-> Departamento, 1-> Vivienda
    private int itemProvSelec;// 0-> Buenos Aires, 1-> Santiago del Estero
    private int selecEstado; //Variable que guarda la eleccion del RadioGroup. 0-> Alquilar, 1-> Vender
    private String[] tipo = {"Departamento", "Vivienda"};
    private FormActivity formActivity;
    private MaterialToolbar materialToolbar;
    private String rutaImagen;
    private static final int PICK_IMAGE_REQUEST = 1; //Codigo de solicitud para cargar imagen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar);

        etDni = findViewById(R.id.etDni2);
        etNombre = findViewById(R.id.etNombre2);
        etMail = findViewById(R.id.etMail2);
        etTelefono = findViewById(R.id.etTelefono2);
        etMetros = findViewById(R.id.etCargarMetros);
        etAmb = findViewById(R.id.etCargarAmbientes);
        etAnt = findViewById(R.id.etCargarAntiguedad);
        etPrecio = findViewById(R.id.etCargarPrecio);
        etDireccion = findViewById(R.id.etCargarDireccion);
        spTipo = findViewById(R.id.spCargarTipo);
        spProvincias = findViewById(R.id.spProvincias2);
        rgEstado = findViewById(R.id.rgCargarEstado);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);
        formActivity = new FormActivity();
        materialToolbar = findViewById(R.id.materialToolbar2);
        materialToolbar.setTitle("Publicación");

        ArrayAdapter<String> adaptadorTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tipo);
        spTipo.setAdapter(adaptadorTipo);
        //Escuchador de evento del spinner tipo (Departamento, Vivienda)
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                itemTipoSelec = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        ArrayAdapter<String> adaptadorProvincias = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, formActivity.getProvincias());
        spProvincias.setAdapter(adaptadorProvincias);
        //Escuchador de evento del spinner provincia
        spProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                itemProvSelec = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void publicar(View V) {
        if (!isCamposVacios()) {
            int dni = Integer.parseInt(etDni.getText().toString());
            SQLiteDatabase db = MainActivity.adminDB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT Propietario.ID FROM Propietario WHERE Propietario.Dni = " + dni, null);
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                cargarInmueble(id);
            } else {
                cargarPropietario();
            }
            cursor.close();
            db.close();
        }
    }

    //Metodo que verifica si todos los campos estan vacios o no
    private boolean isCamposVacios() {
        String dni = etDni.getText().toString();
        String nombre = etNombre.getText().toString();
        String mail = etMail.getText().toString();
        String telefono = etTelefono.getText().toString();
        String metros = etMetros.getText().toString();
        String ambientes = etAmb.getText().toString();
        String antiguedad = etAnt.getText().toString();
        String precio = etPrecio.getText().toString();
        String direccion = etDireccion.getText().toString();

        if (dni.isEmpty() || nombre.isEmpty() || mail.isEmpty() || telefono.isEmpty() || metros.isEmpty() ||
                ambientes.isEmpty() || antiguedad.isEmpty() || precio.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return true; //Todos los campos estas vacios
        } else {
            return false;//Todos los campos no estan vacios
        }
    }

    //Metodo que carga un propietario que no existe en la bd
    private void cargarPropietario() {
        int dni = Integer.parseInt(etDni.getText().toString());
        String nombre = etNombre.getText().toString();
        String mail = etMail.getText().toString();
        String telefono = etTelefono.getText().toString();
        //Insertar propietario y devuelve su ID
        int propID = (int) MainActivity.adminDB.insertarPropietario(dni, nombre, mail, telefono);
        //Cargar su inmueble
        cargarInmueble(propID);
    }

    //Metodo que carga un inmueble
    private void cargarInmueble(int propID) {
        Inmueble inmueble = new Inmueble();

        inmueble.setPropietario(propID);
        inmueble.setTipo(itemTipoSelec + 1);
        inmueble.setProvincia(itemProvSelec + 1);
        inmueble.setMetros(Float.parseFloat(etMetros.getText().toString()));
        inmueble.setAmbientes(Integer.parseInt(etAmb.getText().toString()));
        inmueble.setAntiguedad(Integer.parseInt(etAnt.getText().toString()));
        inmueble.setPrecio(Double.parseDouble(etPrecio.getText().toString()));
        inmueble.setDireccion(etDireccion.getText().toString());
        selecEstado = getSeleccionRadioButton(); //Se guarda el radiobutton seleccionado
        inmueble.setEstado(selecEstado + 1);
        switch (itemProvSelec) {
            case 0:
                //Obelisco, Buenos Aires
                inmueble.setLatitud("-34.6037345");
                inmueble.setLongitud("-58.3841453");
                break;
            case 1:
                //Plaza libertad, Santiago del Estero
                inmueble.setLatitud("-27.787523");
                inmueble.setLongitud("-64.2620768");
                break;
        }

        MainActivity.adminDB.insertarInmueble(inmueble);
        Toast.makeText(this, "Publicado con exito!", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    //Metodo que se ejecuta cuando el usuario verifica si existe su dni ya resgistrado o no
    public void verificarDni(View v) {
        String documento = etDni.getText().toString();
        if (!documento.isEmpty()) {
            int dni = Integer.parseInt(documento);
            SQLiteDatabase db = MainActivity.adminDB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Propietario WHERE Propietario.Dni = " + dni, null);
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "DNI encontrado! se cargan sus datos", Toast.LENGTH_SHORT).show();
                etNombre.setText(cursor.getString(cursor.getColumnIndexOrThrow("Nombre")));
                etMail.setText(cursor.getString(cursor.getColumnIndexOrThrow("Mail")));
                etTelefono.setText(cursor.getString(cursor.getColumnIndexOrThrow("Telefono")));
                // Inhabilita los EditText
                etNombre.setEnabled(false);
                etMail.setEnabled(false);
                etTelefono.setEnabled(false);
            } else {
                Toast.makeText(this, "DNI no encontrado. Cargue sus datos abajo", Toast.LENGTH_SHORT).show();
            }
            db.close();
            cursor.close();
        } else {
            Toast.makeText(this, "Cargar campo DNI", Toast.LENGTH_SHORT).show();
        }
    }


    //Metodo que obtiene el valor del radio button seleccionado
    private int getSeleccionRadioButton() {
        int count = rgEstado.getChildCount();
        int selec = -1;

        for (int i = 0; i < count; i++) {
            RadioButton radioButton = (RadioButton) rgEstado.getChildAt(i); //Obtiene el boton en una posicion especifica
            if (radioButton.isChecked()) {
                selec = i;
                break; //Romper el for al encontrar el boton chekeado
            }
        }
        return selec;
    }

    //Metodos para cargar imagen (No implementado)
    public void cargarImagen(View v) {
        abrirGaleria();
    }

    public void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //Intent.ACTION_PICK -> Abrir selector de imagenes
        //MediaStore.Images.Media.EXTERNAL_CONTENT_URI -> Especifica la URI de contenido para la galería de imágenes externas
        //URI = Es una clase que representa un identificador único para identificar y acceder a recursos,
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imagenUri = data.getData(); //Carga la URI de la imagen
            rutaImagen = getRutaImagen(imagenUri);
            /*// Utiliza Glide para cargar la imagen en un ImageView
            Glide.with(this)
                    .load(ruta)
                    .into(imageView); //ImagenView temporal para obtener el codigo hash de la imagen cargada*/

            Toast.makeText(this, "Imagen cargada con éxito", Toast.LENGTH_SHORT).show();
            btnSubirImagen.setEnabled(false);
        } else {
            Toast.makeText(this, "Imagen no cargada", Toast.LENGTH_SHORT).show();
        }
    }

    private String getRutaImagen(Uri uri) {
        //Se especifica que columnas quiero que se devuelvan en el cursor
        //En este caso, la columna DATA que contiene la ruta del archivo
        String[] proyeccion = {MediaStore.Images.Media.DATA};

        //Consulta en el proveedor de contenido de Android.
        Cursor cursor = getContentResolver().query(uri, proyeccion, null, null, null);

        if (cursor != null) {
            //Se obtiene el índice de la columna DATA
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            //Se obtiene la ruta del archivo desde el Cursor usando el índice de la columna.
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        } else {
            return null;
        }
    }

    public void limpiar(View v) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etMail.setText("");
        etDni.setText("");
        etTelefono.setText("");
        etMetros.setText("");
        etAmb.setText("");
        etAnt.setText("");
        etPrecio.setText("");
        etDireccion.setText("");
    }
}