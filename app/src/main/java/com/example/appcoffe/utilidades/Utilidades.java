package com.example.appcoffe.utilidades;

public class Utilidades {
//USUARIO
    public static final String TABLA_USUARIO = "usuarios";
    public static final String DATO_ID = "id";
    public static final String DATO_NOMBRE = "nombre";
    public static final String DATO_EMAIL = "email";
    public static final String DATO_PASS = "pass";
    public static final String DATO_CONF = "conf";

    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE "+TABLA_USUARIO+" " +
            "("+DATO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" +DATO_NOMBRE+" TEXT, " +DATO_EMAIL+" TEXT, " +
            "" +DATO_PASS+" TEXT," +DATO_CONF+" TEXT)";

//CAFETERIA
public static final String TABLA_CAFETERIA = "cafeteria";
    public static final String DATO_ID_CAFE = "id";
    public static final String DATO_NOMBRE_CAFE = "nombre";
    public static final String DATO_DUENO_CAFE = "dueno";
    public static final String DATO_TELEFONO_CAFE = "telefono";
    public static final String DATO_DIRECCION = "direccion";

    public static final String CREAR_TABLA_CAFETERIA = "CREATE TABLE "+TABLA_CAFETERIA+" " +
            "("+DATO_ID_CAFE+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" +DATO_NOMBRE_CAFE+" TEXT, " +DATO_DUENO_CAFE+" TEXT, " +
            "" +DATO_TELEFONO_CAFE+" TEXT," +DATO_DIRECCION+" TEXT)";
}
