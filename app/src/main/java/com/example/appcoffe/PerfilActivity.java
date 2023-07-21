package com.example.appcoffe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PerfilActivity extends AppCompatActivity {

    EditText name,email,pass,confPass;
    Button guardar,eliminar;
    FloatingActionButton atras;
    DBHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        guardar = findViewById(R.id.btnG);
        eliminar = findViewById(R.id.btnE);
        name = findViewById(R.id.edit_nombre);
        email = findViewById(R.id.edit_email);
        pass = findViewById(R.id.edit_pass);
        confPass = findViewById(R.id.edit_confPass);
        atras = findViewById(R.id.btnAtrasPerfil);

        DB = new DBHelper(this);

        Intent recibeDatos = getIntent();
        String info = recibeDatos.getStringExtra("key2");

            if (recibeDatos != null) {
                Cursor cursor = DB.datorPorEmail(info);
                if (cursor != null & cursor.moveToFirst()){
                    name.setText(cursor.getString(0));
                    email.setText(cursor.getString(1));
                    pass.setText(cursor.getString(2));
                    confPass.setText(cursor.getString(3));
                    cursor.close();
                }
            }
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = name.getText().toString();
                String em = email.getText().toString();
                String cont = pass.getText().toString();
                String confCont = confPass.getText().toString();

                if (nom.isEmpty()  || em.isEmpty()  || cont.isEmpty()  || confCont.isEmpty()  ) {
                    Toast.makeText(PerfilActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (cont.equals(confCont)) {
                        boolean actualizar = DB.actualizarDatos(info,nom, em, cont);
                        if (actualizar) {
                            String info = em;
                            Toast.makeText(PerfilActivity.this, "Actualizacion exitosa", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PerfilActivity.this, InicioActivity.class);
                            intent.putExtra("key",info);
                            startActivity(intent);
                        }else {
                            Toast.makeText(PerfilActivity.this, "Actualizacion fallida", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(PerfilActivity.this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean eliminado = DB.eliminarPerfil(info);
                if (eliminado) {
                    Toast.makeText(PerfilActivity.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PerfilActivity.this, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DB.closeDatabase();
    }
}
