package com.example.appcoffe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcoffe.adapter.AdapterDatos;
import com.example.appcoffe.data.Cafeteria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CafeteriasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button,atras;
    AdapterDatos adapterDatos;
    ArrayList<Cafeteria> listaCafeteria;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeterias);


        recyclerView = findViewById(R.id.recyclerID);
        atras = findViewById(R.id.btnAtrasEdi);
        add_button = findViewById(R.id.btnAgregarLocal);

        recyclerView.setLayoutManager(new LinearLayoutManager(CafeteriasActivity.this));

        DB = new DBHelper(CafeteriasActivity.this);

        listaCafeteria = new ArrayList<>();

        adapterDatos = new AdapterDatos(DB.listaLocales());
        recyclerView.setAdapter(adapterDatos);

        add_button.setOnClickListener(v -> {
            Intent intent = new Intent(CafeteriasActivity.this,EditCafeActivity.class);
            startActivity(intent);
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CafeteriasActivity.this, EditCafeActivity.class);
                startActivity(intent);
            }
        });
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CafeteriasActivity.this, InicioActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}