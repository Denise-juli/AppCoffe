package com.example.appcoffe;

import static com.example.appcoffe.utilidades.Utilidades.DATO_DIRECCION;
import static com.example.appcoffe.utilidades.Utilidades.DATO_DUENO_CAFE;
import static com.example.appcoffe.utilidades.Utilidades.DATO_NOMBRE_CAFE;
import static com.example.appcoffe.utilidades.Utilidades.DATO_TELEFONO_CAFE;
import static com.example.appcoffe.utilidades.Utilidades.TABLA_CAFETERIA;
import static com.example.appcoffe.utilidades.Utilidades.TABLA_USUARIO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appcoffe.data.Cafeteria;
import com.example.appcoffe.utilidades.Utilidades;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase database;

    public DBHelper(@Nullable Context context) {

        super(context, "bdCoffe4", null, 1);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIOS);
        db.execSQL(Utilidades.CREAR_TABLA_CAFETERIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLA_CAFETERIA);
        onCreate(db);
    }

    //USUARIO
    public Boolean insertar(String nombre, String email, String pass, String conf) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("nombre", nombre);
        content.put("email", email);
        content.put("pass", pass);
        content.put("conf", conf);
        long result = db.insert("usuarios", null, content);
        db.close();
        return result != -1;
    }
    public Boolean checkUsuario(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkUsuarioCont(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ? and pass = ?", new String[]{email,pass});
        return cursor.getCount() > 0;
    }
    public String name(String email) {
        String nom = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM usuarios WHERE email = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            nom = cursor.getString(0);
            cursor.close();
        }
        return nom;
    }

    public Cursor datorPorEmail(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] campos = {Utilidades.DATO_NOMBRE, Utilidades.DATO_EMAIL,
                Utilidades.DATO_PASS, Utilidades.DATO_CONF};
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, "email=?", new String[]{email}, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                return cursor;
            }else {
                cursor.close();
                return null;
            }
    }
    public boolean actualizarDatos(String emailV, String nombreN, String emailN,  String passN) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Utilidades.DATO_EMAIL, emailN);
        valores.put(Utilidades.DATO_NOMBRE, nombreN);
        valores.put(Utilidades.DATO_PASS, passN);

        int filasActualizadas = db.update(Utilidades.TABLA_USUARIO, valores, "email=?", new String[]{emailV});

        return filasActualizadas > 0;
    }

    public boolean eliminarPerfil( String email) {
    SQLiteDatabase db = this.getWritableDatabase();
        int filaEliminada = db.delete(Utilidades.TABLA_USUARIO, "email=?", new String[]{email});
        return filaEliminada >0;
    }

//CAFETERIA
    public Boolean checkCafeteria(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM cafeteria WHERE nombre = ?", new String[]{nombre});
        return cursor.getCount() > 0;
    }
    public Boolean insertarCafeteria(String nombre, String dueno, String telefono, String direccion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("nombre", nombre);
        content.put("dueno", dueno);
        content.put("telefono", telefono);
        content.put("direccion", direccion);
        long result = db.insert("cafeteria", null, content);
        db.close();
        return result != -1;
    }
    public Cafeteria clickLocal(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cafeteria cafeteria = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLA_CAFETERIA + " WHERE id = " +id+ " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            cafeteria = new Cafeteria();
            cafeteria.setId(cursor.getInt(0));
            cafeteria.setNombre(cursor.getString(1));
            cafeteria.setDueno(cursor.getString(2));
            cafeteria.setTelefono(cursor.getString(3));
            cafeteria.setDireccion(cursor.getString(4));
        }

        cursor.close();

        return cafeteria;
    }
    public ArrayList<Cafeteria> listaLocales() {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Cafeteria> listaCafe = new ArrayList<>();
        Cafeteria cafeteria;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLA_CAFETERIA + " ORDER BY nombre ASC", null);

        if (cursor.moveToFirst()) {
            do {
                cafeteria = new Cafeteria();
                cafeteria.setId(cursor.getInt(0));
                cafeteria.setNombre(cursor.getString(1));
                cafeteria.setDueno(cursor.getString(2));
                cafeteria.setTelefono(cursor.getString(3));
                cafeteria.setDireccion(cursor.getString(4));
                listaCafe.add(cafeteria);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaCafe;
    }

    public boolean actualizarCafeteria(int id, String nombreN, String duenoN, String telefonoN, String direccionN) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(DATO_NOMBRE_CAFE, nombreN);
        valores.put(DATO_DUENO_CAFE, duenoN);
        valores.put(DATO_TELEFONO_CAFE, telefonoN);
        valores.put(DATO_DIRECCION, direccionN);

        int filasActualizadas = db.update(TABLA_CAFETERIA, valores, "id=?", new String[]{String.valueOf(id)});

        return filasActualizadas > 0;
    }

    public boolean eliminarCafeteria( int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int filaEliminada = db.delete(TABLA_CAFETERIA, "id=?", new String[]{(Integer.toString(id))});
        return filaEliminada >0;
    }

    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
