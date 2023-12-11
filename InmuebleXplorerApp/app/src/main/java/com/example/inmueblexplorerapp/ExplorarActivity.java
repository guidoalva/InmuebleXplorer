package com.example.inmueblexplorerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExplorarActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<InmuebleVistaExplorar> listInmuebles;
    private int provSelec;
    private int opSelec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorar);

        Bundle datos = getIntent().getExtras();
        provSelec = datos.getInt("ProvSelec") + 1;
        opSelec = datos.getInt("OpSelec") + 1;

        listInmuebles = new ArrayList<>();
        rv = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        cargarInmuebles(provSelec, opSelec); //Se carga la lista listInmuebles con los valores que selecciono el usuario
        rv.setAdapter(new AdaptadorInmueble()); //Se crea la clase adaptador con los datos a mostrar como parametro
    }

    public void cargarInmuebles(int provincia, int estado) {
        SQLiteDatabase db = MainActivity.adminDB.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT inmueble.ID, Propietario.Nombre AS NombreProp, tipo.Descripcion AS DescTipo, " +
                "inmueble.Metros, inmueble.Ambientes, inmueble.Antiguedad, inmueble.Precio, " +
                "inmueble.Imagen, inmueble.Latitud, Inmueble.Longitud, estado.Descripcion AS DescEstado, " +
                "provincia.Nombre AS NombreProvincia, inmueble.Direccion FROM inmueble " +
                "JOIN propietario ON inmueble.Propietario = propietario.ID " +
                "JOIN tipo ON inmueble.Tipo = tipo.ID " +
                "JOIN estado ON inmueble.Estado = estado.ID " +
                "JOIN provincia ON inmueble.Provincia = provincia.ID " +
                "WHERE inmueble.Estado = " + estado + " AND inmueble.Provincia = " + provincia, null);

        if (cursor != null) {
            cursor.moveToFirst();
            do {
                InmuebleVistaExplorar vistaInmueble = new InmuebleVistaExplorar();
                vistaInmueble.setId(cursor.getInt(cursor.getColumnIndexOrThrow("ID")));
                vistaInmueble.setNombreProp(cursor.getString(cursor.getColumnIndexOrThrow("NombreProp")));
                vistaInmueble.setDescTipo(cursor.getString(cursor.getColumnIndexOrThrow("DescTipo")));
                vistaInmueble.setMetros(cursor.getFloat(cursor.getColumnIndexOrThrow("Metros")));
                vistaInmueble.setAmbientes(cursor.getInt(cursor.getColumnIndexOrThrow("Ambientes")));
                vistaInmueble.setAntiguedad(cursor.getInt(cursor.getColumnIndexOrThrow("Antiguedad")));
                vistaInmueble.setPrecio(cursor.getDouble(cursor.getColumnIndexOrThrow("Precio")));
                vistaInmueble.setImagen(cursor.getInt(cursor.getColumnIndexOrThrow("Imagen")));
                vistaInmueble.setLatitud(cursor.getString(cursor.getColumnIndexOrThrow("Latitud")));
                vistaInmueble.setLongitud(cursor.getString(cursor.getColumnIndexOrThrow("Longitud")));
                vistaInmueble.setDescEstado(cursor.getString(cursor.getColumnIndexOrThrow("DescEstado")));
                vistaInmueble.setNombreProvincia(cursor.getString(cursor.getColumnIndexOrThrow("NombreProvincia")));
                vistaInmueble.setDireccion(cursor.getString(cursor.getColumnIndexOrThrow("Direccion")));
                listInmuebles.add(vistaInmueble); //Añado a la lista
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "Cursor vacio", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
    }

    //Clase que crea cada uno de los inmuebles a mostrar
    //Holder significa contenedor de vistas
    private class AdaptadorInmueble extends RecyclerView.Adapter<AdaptadorInmueble.AdaptadorInmuebleHolder> {

        public AdaptadorInmueble() {
        }

        @NonNull
        @Override
        public AdaptadorInmuebleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Este metodo crea un contenedor para las vistas de un elemento en la lista
            return new AdaptadorInmuebleHolder(getLayoutInflater().inflate(R.layout.item_inmueble, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorInmuebleHolder holder, int position) {
            //Este metodo asigna un escuchador para el boton Contactar
            holder.btnContactar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Obtengo la posicion del inmueble correspondiente al boton Contactar que se presiono
                    InmuebleVistaExplorar inmueble = listInmuebles.get(holder.getAdapterPosition()); //Este metodo devuelve la posicion en el RV

                    //Creo el intent con los datos a enviar a la actividad Consulta
                    Intent consultaActivity = new Intent(v.getContext(), ConsultaActivity.class);
                    consultaActivity.putExtra("Estado", inmueble.getDescEstado());
                    consultaActivity.putExtra("Precio", inmueble.getPrecio());
                    consultaActivity.putExtra("Metros", inmueble.getMetros());
                    consultaActivity.putExtra("Ambientes", inmueble.getAmbientes());
                    consultaActivity.putExtra("Antiguedad", inmueble.getAntiguedad());
                    consultaActivity.putExtra("Provincia", inmueble.getNombreProvincia());
                    consultaActivity.putExtra("Direccion", inmueble.getDireccion());
                    consultaActivity.putExtra("Latitud", inmueble.getLatitud());
                    consultaActivity.putExtra("Longitud", inmueble.getLongitud());
                    consultaActivity.putExtra("Imagen", inmueble.getImagen());
                    v.getContext().startActivity(consultaActivity);
                }
            });
            //Este metodo carga el holder con con datos específicos de un elemento en la posición de la lista
            holder.imprimir(position);
        }

        @Override
        public int getItemCount() {
            //Devuelve la cantidad de elementos de la estructura de datos.
            return listInmuebles.size();
        }

        //Esta clase se encarga de imprimir cada uno de los inmuebles
        private class AdaptadorInmuebleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private ImageView imagen;
            private TextView precio, provincia, direccion, metros, ambientes, antiguedad;
            private Button btnContactar;

            public AdaptadorInmuebleHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setClickable(true);
                imagen = itemView.findViewById(R.id.imagen);
                precio = itemView.findViewById(R.id.precio);
                provincia = itemView.findViewById(R.id.provincia);
                direccion = itemView.findViewById(R.id.direccion);
                metros = itemView.findViewById(R.id.metros);
                ambientes = itemView.findViewById(R.id.ambientes);
                antiguedad = itemView.findViewById(R.id.antiguedad);
                btnContactar = itemView.findViewById(R.id.btnContactar);

                itemView.setClickable(true);
                itemView.setOnClickListener(this);
            }

            //Metodo que muestra todos los inmuebles al hacer click en el boton explorar
            public void imprimir(int position) {
                InmuebleVistaExplorar inmueble = listInmuebles.get(position);

                imagen.setImageResource(inmueble.getImagen());
                provincia.setText(inmueble.getNombreProvincia().toString());
                direccion.setText(inmueble.getDireccion().toString());
                ambientes.setText(inmueble.getAmbientes().toString() + " Amb.");
                antiguedad.setText(inmueble.getAntiguedad().toString() + " Años");
                metros.setText(inmueble.getMetros().toString() + "m²");
                DecimalFormat formatoPrecio = new DecimalFormat("#,###");
                String precioFormateado = formatoPrecio.format(inmueble.getPrecio());
                precio.setText("$" + precioFormateado);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(ExplorarActivity.this, "Propietario: " + listInmuebles.get(getAdapterPosition()).getNombreProp(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}