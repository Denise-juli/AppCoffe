package com.example.appcoffe.data;

public class Cafeteria {

    int id;
    String nombre;
    String dueno;
    String telefono;
    String direccion;

    public Cafeteria(int id, String nombre, String dueno, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.dueno = dueno;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cafeteria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
