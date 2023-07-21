package com.example.appcoffe.data;

public class Usuario {

    private String nombre;
    private String email;
    private String pass;
    private String conf;

    public Usuario(String nombre, String email, String pass, String conf) {
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
        this.conf = conf;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }
}
