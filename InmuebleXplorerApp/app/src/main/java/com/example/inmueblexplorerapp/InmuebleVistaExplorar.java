package com.example.inmueblexplorerapp;

//Clase utilizada para representar la consulta realizada por el JOIN.
//Con el fin de usarla en el RecyclerView

//MUY IMPORTANTE: NO DEFINIR LOS DATOS COMO TIPO DE DATOS PRIMITIVOS
//PARA ASI PODER USAR EL TOSTRING() EN LOS SETTEXT()
public class InmuebleVistaExplorar {
    private Integer id; //ID de la tabla inmueble
    private String nombreProp;
    private String descTipo;
    private Float metros;
    private Integer ambientes;
    private Integer antiguedad;
    private Double precio;
    private Integer imagen;
    private String latitud;
    private String longitud;
    private String descEstado;
    private String nombreProvincia;
    private String direccion;

    public InmuebleVistaExplorar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProp() {
        return nombreProp;
    }

    public void setNombreProp(String nombreProp) {
        this.nombreProp = nombreProp;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
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

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
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

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
