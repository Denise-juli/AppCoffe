package com.example.appcoffe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView regis,email,pass;
    Button ingresar;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        ingresar = findViewById(R.id.btnIngresar);
        regis = findViewById(R.id.registro);

        DB = new DBHelper(this);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String cont = pass.getText().toString();

                if (email.length() == 0 || pass.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = DB.checkUsuarioCont(em, cont);
                    if (check) {
                        String info = em;
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
                        intent.putExtra("key", info);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Datos invalidos", Toast.LENGTH_SHORT).show();
                    }
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