package com.example.appcoffe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    TextView nom;
    Button perfil,abm;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        perfil = findViewById(R.id.btnPerfil);
        abm  = findViewById(R.id.btnAbm);
        nom = findViewById(R.id.txtNombre);
        DB = new DBHelper(this);

       Intent recibeDatos = getIntent();

               if (recibeDatos != null) {
                   String info = recibeDatos.getStringExtra("key");
                   String nombre = DB.name(info);
                   nom.setText(nombre);
               }else {
                   String info = recibeDatos.getStringExtra("key");
                   String dato = DB.name(info);
                   nom.setText(dato);
               }

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        String dato = recibeDatos.getStringExtra("key");
                        Intent intent = new Intent(InicioActivity.this, PerfilActivity.class);
                        intent.putExtra("key2",dato);
                        startActivity(intent);
            }
        });
        abm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(InicioActivity.this, CafeteriasActivity.class);
                        startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DB.closeDatabase();
    }
}