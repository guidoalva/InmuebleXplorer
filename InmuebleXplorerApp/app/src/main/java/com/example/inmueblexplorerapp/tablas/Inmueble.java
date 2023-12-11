package com.example.inmueblexplorerapp.tablas;

import com.example.inmueblexplorerapp.R;

import java.util.UUID;

public class Inmueble {
    private String id;
    private Integer propietario;
    private Integer tipo;
    private Float metros;
    private Integer ambientes;
    private Integer antiguedad;
    private Double precio;
    private String imagen;
    private String latitud;
    private String longitud;
    private Integer estado;
    private Integer provincia;
    private String direccion;
    private Integer imgLogotipo = R.drawable.logotipo; //Imagen que contiene el logitpo de la aplicacion
    public Inmueble() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Integer getPropietario() {
        return propietario;
    }

    public void setPropietario(Integer propietario) {
        this.propietario = propietario;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Float getMetros() {
        return metros;
    }

    public void setMetros(Float metros) {
        this.metros = metros;
    }

    public Integer getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(Integer ambientes) {
        this.ambientes = ambientes;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getProvincia() {
        return provincia;
    }

    public void setProvincia(Integer provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getImgLogotipo() {
        return imgLogotipo;
    }
}
