package com.example.padelnet.funcionamiento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.padelnet.R;
import com.example.padelnet.adapter.AdapterAllReservas;
import com.example.padelnet.adapter.AdapterCampeonato;
import com.example.padelnet.adapter.AdapterInscripciones;
import com.example.padelnet.adapter.AdapterPista;
import com.example.padelnet.adapter.MyReservasAdapter;
import com.example.padelnet.db.Gestor;
import com.example.padelnet.objetos.Campeonato;
import com.example.padelnet.objetos.Inscripcion;
import com.example.padelnet.objetos.Pista;
import com.example.padelnet.objetos.Reserva;

import java.util.ArrayList;

public class Root extends AppCompatActivity {

    Button pistas, reservas, campeonatos, inscripcion;
    ArrayList<Pista> listAllPistas;
    ArrayList<Reserva> listAllReservas;
    ArrayList<Inscripcion> listInscripciones;
    ArrayList<Campeonato> listAllCampeonatos;
    AlertDialog.Builder builderPistas, builderReservas;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        //Ocultamos la ActionBar
        getSupportActionBar(). hide();

        //Definiciones
        pistas = findViewById(R.id.btn_pistas);
        reservas = findViewById(R.id.btn_reserva);
        campeonatos = findViewById(R.id.btn_campeonatos);
        inscripcion = findViewById(R.id.btn_incripciones);

        pistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderPistas = new AlertDialog.Builder(Root.this);

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_gestion_root,null);
                builderPistas.setView(customLayout);

                //Definiciones
                TextView tit = customLayout.findViewById(R.id.titu);
                listView = customLayout.findViewById(R.id.listview);
                Button crear = customLayout.findViewById(R.id.crear);
                Button borrar = customLayout.findViewById(R.id.borrar);

                recuperarTodasLasPistas();

                //Cambiamos el texto del título
                tit.setText("Lista de Pistas");

                AlertDialog dialog = builderPistas.create();
                dialog.show();

                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Feedback
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
                borrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        reservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builderReservas = new AlertDialog.Builder(Root.this);

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_gestion_root,null);
                builderReservas.setView(customLayout);

                //Definiciones
                TextView tit = customLayout.findViewById(R.id.titu);
                listView = customLayout.findViewById(R.id.listview);
                Button crear = customLayout.findViewById(R.id.crear);
                Button borrar = customLayout.findViewById(R.id.borrar);

                recuperarTodasLasReservas();

                //Cambiamos el texto del título
                tit.setText("Lista de Reservas");

                AlertDialog dialog = builderReservas.create();
                dialog.show();

                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Feedback
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
                borrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        campeonatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderReservas = new AlertDialog.Builder(Root.this);

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_gestion_root,null);
                builderReservas.setView(customLayout);

                //Definiciones
                TextView tit = customLayout.findViewById(R.id.titu);
                listView = customLayout.findViewById(R.id.listview);
                Button crear = customLayout.findViewById(R.id.crear);
                Button borrar = customLayout.findViewById(R.id.borrar);

                recuperarTodosLosCampeonatos();

                //Cambiamos el texto del título
                tit.setText("Lista de Campeonatos");

                AlertDialog dialog = builderReservas.create();
                dialog.show();

                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Feedback
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
                borrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        inscripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderReservas = new AlertDialog.Builder(Root.this);

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_gestion_root,null);
                builderReservas.setView(customLayout);

                //Definiciones
                TextView tit = customLayout.findViewById(R.id.titu);
                listView = customLayout.findViewById(R.id.listview);
                Button crear = customLayout.findViewById(R.id.crear);
                Button borrar = customLayout.findViewById(R.id.borrar);

                recuperarTodasLasInscripciones();

                //Cambiamos el texto del título
                tit.setText("Lista de Inscripciones Campeonatos");

                AlertDialog dialog = builderReservas.create();
                dialog.show();

                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Feedback
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
                borrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Root.this, "No implementado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    public void recuperarTodasLasPistas(){

        Gestor gestor = new Gestor(Root.this);
        listAllPistas = new ArrayList<>();
        listAllPistas = gestor.recuperarTodasLasPistas();
        AdapterPista adapter = new AdapterPista
                (Root.this, R.layout.list_object_reserva, listAllPistas);

        listView.setAdapter(adapter);
    }

    public void recuperarTodasLasReservas(){
        Gestor gestor = new Gestor(Root.this);
        listAllReservas = new ArrayList<>();
        listAllReservas = gestor.recuperarTodasLasReservas();
         AdapterAllReservas adapter = new AdapterAllReservas
                 (Root.this, R.layout.list_object_all_reservas, listAllReservas);

        listView.setAdapter(adapter);
    }

    public void recuperarTodosLosCampeonatos(){
        Gestor gestor = new Gestor(Root.this);
        listAllCampeonatos = new ArrayList<>();
        listAllCampeonatos = gestor.obtenerCampeonatos();
        AdapterCampeonato adapter = new AdapterCampeonato
                (Root.this, R.layout.list_object_campeonato, listAllCampeonatos);

        listView.setAdapter(adapter);
    }

    public void recuperarTodasLasInscripciones(){
        Gestor gestor = new Gestor(Root.this);
        listInscripciones = new ArrayList<>();
        listInscripciones = gestor.obtenerInscripciones();
        AdapterInscripciones adapter = new AdapterInscripciones
                (Root.this, R.layout.list_object_inscripciones, listInscripciones);

        listView.setAdapter(adapter);
    }
}