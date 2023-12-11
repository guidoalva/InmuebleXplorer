package com.example.inmueblexplorerapp.tablas;

import java.util.UUID;

public class Tipo {
    private String id;
    private String descripcion;

    public Tipo(String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
