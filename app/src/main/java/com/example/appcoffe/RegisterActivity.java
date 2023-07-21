package com.example.appcoffe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterActivity extends AppCompatActivity {

    EditText nombre,email,pass,confPass;
    Button singUp;
    FloatingActionButton atras;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.edit_nombre);
        email = findViewById(R.id.edit_email);
        pass = findViewById(R.id.edit_pass);
        confPass = findViewById(R.id.edit_confPass);
        singUp = findViewById(R.id.btnRegistrar);
        atras = findViewById(R.id.btnAtrasRegister);

        DB = new DBHelper(this);

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString();
                String em = email.getText().toString();
                String cont = pass.getText().toString();
                String confCont = confPass.getText().toString();

                if (nombre.length() == 0 || email.length() == 0 || pass.length() == 0 || confPass.length() == 0 ) {
                    Toast.makeText(RegisterActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (cont.equals(confCont)) {
                        Boolean check = DB.checkUsuario(em);
                        if (!check) {
                            Boolean insert = DB.insertar(nom, em, cont, confCont);
                            if (insert) {
                                Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegisterActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this, "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        onBackPressed();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DB.closeDatabase();
    }
}