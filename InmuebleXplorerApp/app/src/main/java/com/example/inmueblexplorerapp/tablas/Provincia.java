package com.example.inmueblexplorerapp.tablas;

import java.util.UUID;

public class Provincia {
    private String id;
    private String nombre;

    public Provincia(String nombre) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
