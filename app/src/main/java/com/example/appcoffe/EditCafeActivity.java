package com.example.appcoffe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcoffe.data.Cafeteria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditCafeActivity extends AppCompatActivity {

    EditText nombre,dueno,telefono,direccion;
    Button agregar,eliminar,actualizar,verificar;
    FloatingActionButton atras;
    Cafeteria cafeteria;
    int id = 0;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cafe);

        nombre = findViewById(R.id.edit_nombre);
        dueno = findViewById(R.id.edit_dueno);
        telefono = findViewById(R.id.edit_telefono);
        direccion = findViewById(R.id.edit_direccion);
        actualizar = findViewById(R.id.btnActualizar);
        agregar = findViewById(R.id.btnAgregar);
        atras = findViewById(R.id.btnAtrasEdi);
        eliminar = findViewById(R.id.btnEliminar);
        verificar = findViewById(R.id.btnVerificar);

        DB = new DBHelper(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id = extras.getInt("ID");
            cafeteria = DB.clickLocal(id);
                nombre.setText(cafeteria.getNombre());
                dueno.setText(cafeteria.getDueno());
                telefono.setText(cafeteria.getTelefono());
                direccion.setText(cafeteria.getDireccion());
            agregar.setVisibility(View.INVISIBLE);
            } else {
            actualizar.setVisibility(View.INVISIBLE);
            eliminar.setVisibility(View.INVISIBLE);
        }

        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dir = direccion.getText().toString();
                Uri map = Uri.parse("geo:0,0?q=" + Uri.encode(dir));

                Intent mapa = new Intent(Intent.ACTION_VIEW, map);
                startActivity(mapa);
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nom = nombre.getText().toString();
                    String du = dueno.getText().toString();
                    String tel = telefono.getText().toString();
                    String dire = direccion.getText().toString();

                    if (nombre.length() == 0 || dueno.length() == 0 || telefono.length() == 0 || direccion.length() == 0) {
                        Toast.makeText(EditCafeActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean check = DB.checkCafeteria(nom);
                        if (!check) {
                            Boolean insert = DB.insertarCafeteria(nom, du, tel, dire);
                            if (insert) {
                                Toast.makeText(EditCafeActivity.this, "Registro de Cafeteria exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditCafeActivity.this, CafeteriasActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(EditCafeActivity.this, "Registro de Cafeteria fallido", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditCafeActivity.this, "Esta cafeteria ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                 public void onClick(View v) {
                    id = extras.getInt("ID");
                    String nom = nombre.getText().toString();
                    String du = dueno.getText().toString();
                    String tel = telefono.getText().toString();
                    String dire = direccion.getText().toString();

                    if (nom.length() == 0 || du.length() == 0 || tel.length() == 0 || dire.length() == 0) {
                        Toast.makeText(EditCafeActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean actualizar = DB.actualizarCafeteria(id, nom, du, tel, dire);
                        if (actualizar) {
                            Toast.makeText(EditCafeActivity.this, "Actualizacion de Cafeteria exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditCafeActivity.this, CafeteriasActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditCafeActivity.this, "Actualizacion de Cafeteria fallido", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCafeActivity.this, CafeteriasActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = extras.getInt("ID");
                boolean eliminado = DB.eliminarCafeteria(id);

                if (eliminado) {
                    Toast.makeText(EditCafeActivity.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditCafeActivity.this, CafeteriasActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditCafeActivity.this, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        DB.closeDatabase();
    }
}