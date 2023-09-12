package com.example.padelnet.funcionamiento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.padelnet.R;
import com.example.padelnet.acceso.MainActivity;
import com.example.padelnet.adapter.AdapterCampeonato;
import com.example.padelnet.db.Gestor;
import com.example.padelnet.objetos.Campeonato;
import com.example.padelnet.objetos.Inscripcion;
import com.example.padelnet.objetos.Reserva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Campeonatos extends AppCompatActivity {

    ListView listView;
    Button volver;
    String user;
    ArrayList<Campeonato> listCampeonato;
    AdapterCampeonato adapter;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeonato);

        //Ocultamos la ActionBar
        getSupportActionBar().hide();

        //Definiciones
        volver = findViewById(R.id.btn_volver);
        listView = findViewById(R.id.listview);

        //Mostramos todos los campeonatos existentes
        recuperarCampeonatos();

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Campeonatos.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder = new AlertDialog.Builder(view.getContext());

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_choose_default,null);
                builder.setView(customLayout);

                //Definiciones
                TextView tit = customLayout.findViewById(R.id.tit);
                Button si = customLayout.findViewById(R.id.si);
                Button no = customLayout.findViewById(R.id.no);

                //Cambiamos el texto del título
                tit.setText("¿Quieres inscribirte a esta competición?");

                AlertDialog dialog = builder.create();
                dialog.show();

                si.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Obtenemos el nombre de la competición
                        /*Inscripcion i = (Inscripcion) parent.getItemAtPosition(position);
                        String competi = i.getCompeti();*/

                        //Obtenemos fecha actual
                        LocalDate currentDate = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            currentDate = LocalDate.now();
                        }

                        //Guardamos la inscripción en base de datos
                        Gestor admin = new Gestor(builder.getContext());
                        admin.insertInscripcion(obtenerUser(view), "example", currentDate+"");

                        //Quitamos el alertDialog y mosntramos un mensaje a modo de feedback
                        dialog.dismiss();
                        Toast.makeText(Campeonatos.this, "Inscripción Realizada", Toast.LENGTH_SHORT).show();
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
    }

    private void recuperarCampeonatos(){
        Gestor gestor = new Gestor(Campeonatos.this);
        listCampeonato = new ArrayList<>();
        listCampeonato = gestor.obtenerCampeonatos();
        adapter = new AdapterCampeonato
                (Campeonatos.this, R.layout.list_object_campeonato, listCampeonato);

        listView.setAdapter(adapter);
    }

    private String obtenerUser(View v) {

        Gestor gestor = new Gestor(Campeonatos.this);

        //Leemos el usuario del archivo en el que lo hemos guardado y lo guardamos en una string
        File log = v.getContext().getFilesDir();
        File file = new File(log, "user.txt");
        String filePath = file.getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            sb.append(line);
            user = sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}