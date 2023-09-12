package com.example.padelnet.acceso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.padelnet.funcionamiento.Campeonatos;
import com.example.padelnet.funcionamiento.Galeria;
import com.example.padelnet.funcionamiento.MyReservas;
import com.example.padelnet.funcionamiento.NewReserva;
import com.example.padelnet.R;

public class MainActivity extends AppCompatActivity {

    Button crearReserva, misReservas, campeonato, galeria;
    ImageView soporte, logout;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ocultamos la ActionBar
        getSupportActionBar().hide();

        //Definiciones
        crearReserva = findViewById(R.id.btn_realizar_reserva);
        misReservas = findViewById(R.id.btn_ver_reserva);
        campeonato = findViewById(R.id.btn_campeonatos);
        galeria = findViewById(R.id.btn_galeria);
        soporte = findViewById(R.id.soporte);
        logout = findViewById(R.id.logout);

        soporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(MainActivity.this);

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_choose_default, null);
                builder.setView(customLayout);

                //Definiciones
                TextView tit = customLayout.findViewById(R.id.tit);
                Button si = customLayout.findViewById(R.id.si);
                Button no = customLayout.findViewById(R.id.no);

                //Cambiamos el texto del título
                tit.setText("¿Necesitas ayuda de un soporte?");

                AlertDialog dialog = builder.create();
                dialog.show();

                si.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Recogemos el teléfono
                        String tlf = "999999999";

                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tlf));
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(MainActivity.this);

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_choose_default, null);
                builder.setView(customLayout);

                //Definiciones
                TextView tit = customLayout.findViewById(R.id.tit);
                Button si = customLayout.findViewById(R.id.si);
                Button no = customLayout.findViewById(R.id.no);

                //Cambiamos el texto del título
                tit.setText("¿Estás seguro que deseas cerrar sesión?");

                AlertDialog dialog = builder.create();
                dialog.show();

                si.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


        crearReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewReserva.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        misReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyReservas.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        campeonato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Campeonatos.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Galeria.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}