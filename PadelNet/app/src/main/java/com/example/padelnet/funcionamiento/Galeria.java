package com.example.padelnet.funcionamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.padelnet.R;
import com.example.padelnet.acceso.MainActivity;

public class Galeria extends AppCompatActivity {

    ImageView pista1, pista2, pista3;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        //Ocultamos la ActionBar
        getSupportActionBar(). hide();

        //Definiciones de las imágenes y el botón
        pista1 = findViewById(R.id.pista1);
        pista2 = findViewById(R.id.pista2);
        pista3 = findViewById(R.id.pista3);
        volver = findViewById(R.id.btn_register);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Galeria.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}