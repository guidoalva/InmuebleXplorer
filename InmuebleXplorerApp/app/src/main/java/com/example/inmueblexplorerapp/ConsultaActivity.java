package com.example.inmueblexplorerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.DecimalFormat;

public class ConsultaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private MaterialToolbar materialToolbar;
    private String estado, provincia, direccion;
    private Double precio, latitud, longitud;
    private Float metros;
    private Integer imagen, ambientes, antiguedad;
    private TextView tvEstado, tvProvincia, tvDireccion, tvPrecio, tvMetros, tvAmb, tvAnt, tvDesc, tvMensaje;
    private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;
    private EditText etNombre, etMail, etTelefono;
    private ImageView imgImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        //Titulo para el Toolbar
        materialToolbar = findViewById(R.id.materialToolbar);
        materialToolbar.setTitle("Consulta");

        tvEstado = findViewById(R.id.tvEstado);
        tvProvincia = findViewById(R.id.tvProvincia);
        tvDireccion = findViewById(R.id.tvDireccion);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvMetros = findViewById(R.id.tvMetros);
        tvAmb = findViewById(R.id.tvAmbientes);
        tvAnt = findViewById(R.id.tvAntiguedad);
        tvDesc = findViewById(R.id.tvDescripcion);
        etNombre = findViewById(R.id.etNombre2);
        etMail = findViewById(R.id.etMail2);
        etTelefono = findViewById(R.id.etTelefono2);
        imgImagen = findViewById(R.id.imgImagen);
        tvMensaje = findViewById(R.id.tvMensaje);
        tvMensaje.setText(" Hola!, me interesa esta propiedad. Me gustaría recibir más información. Gracias!");

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        //"asincrónico" se refiere a la ejecución de la operación en segundo plano sin bloquear el hilo principal de la aplicación
        //Cuando el mapa esta listo, se ejecuta el metodo onMapReady.

        Bundle datos = getIntent().getExtras();
        estado = datos.getString("Estado");
        provincia = datos.getString("Provincia");
        direccion = datos.getString("Direccion");
        precio = datos.getDouble("Precio");
        metros = datos.getFloat("Metros");
        ambientes = datos.getInt("Ambientes");
        antiguedad = datos.getInt("Antiguedad");
        imagen = datos.getInt("Imagen");
        //Convertir a Doble para poder usarlos como parametros para LatLng
        latitud = Double.parseDouble(datos.getString("Latitud"));
        longitud = Double.parseDouble(datos.getString("Longitud"));

        cargarDatos();
    }

    private void cargarDatos() {
        tvEstado.setText(estado.toString());
        tvProvincia.setText(provincia.toString());
        tvDireccion.setText(direccion.toString());
        // Formatear el precio con separadores de miles y sin decimales
        DecimalFormat formatoPrecio = new DecimalFormat("#,###");
        String precioFormateado = formatoPrecio.format(precio);
        // Concatenar el precio formateado al TextView
        tvPrecio.setText("$" + precioFormateado);
        tvMetros.setText(metros.toString() + "m²");
        tvAmb.setText(ambientes.toString() + " Amb.");
        tvAnt.setText(antiguedad.toString() + " Años");
        imgImagen.setImageResource(imagen);
        tvDesc.setText("Descripcion...");
    }

    //este metodo e ejecutará automáticamente una vez que el mapa
    //de Google esté completamente cargado y listo para interactuar
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng ubicacion = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(ubicacion).title("Ubicacion")); //Logo de color rojo de ubicaicon
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion)); //Movimiento de la camara
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }

    public void enviarConsulta(View v) {

        if (!etNombre.getText().toString().isEmpty() && !etMail.getText().toString().isEmpty()
                && !etTelefono.getText().toString().isEmpty()) {
            etNombre.setText("");
            etMail.setText("");
            etTelefono.setText("");
            Toast.makeText(this, "Su consulta fue enviada con exito", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Debe completar todos los campos solicitados", Toast.LENGTH_SHORT).show();
        }

    }
}