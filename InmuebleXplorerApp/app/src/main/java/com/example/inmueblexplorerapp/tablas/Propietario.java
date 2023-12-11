package com.example.inmueblexplorerapp.tablas;

import java.util.UUID;

public class Propietario {
    private String id;
    private int dni;
    private String nombre;
    private String mail;
    private String telefono;

    public Propietario(int dni, String nombre, String mail, String telefono) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
    }

    public Propietario() {

    }

    public int getDni() {
        return dni;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }
}
