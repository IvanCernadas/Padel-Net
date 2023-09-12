package com.example.padelnet.funcionamiento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.example.padelnet.adapter.MyReservasAdapter;
import com.example.padelnet.db.Gestor;
import com.example.padelnet.objetos.Reserva;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class MyReservas extends AppCompatActivity {

    ListView listview;
    Button back;
    ArrayList<Reserva> listReservas;
    MyReservasAdapter adapter;
    String user2;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservas);

        //Ocultamos la ActionBar
        getSupportActionBar(). hide();

        //Definiciones
        listview = findViewById(R.id.listview);
        back = findViewById(R.id.btn_volver);

        //Rellenar Arraylist con las reservas de ese usuario
        recuperarReservas();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                tit.setText("¿Quieres eliminar esta reserva?");

                AlertDialog dialog = builder.create();
                dialog.show();

                si.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Obtenemos los valores
                        Reserva r = (Reserva) parent.getItemAtPosition(position);
                        String fecha = r.getFecha();
                        String hora = r.getHora();

                        //Borramos de la base de datos la reserva
                        Gestor gestor = new Gestor(builder.getContext());
                        gestor.deleteReserva(fecha, hora);

                        //Actualizamos las reservas
                        recuperarReservas();

                        //Feedback
                        Toast.makeText(MyReservas.this, "Reserva Borrada", Toast.LENGTH_SHORT).show();

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyReservas.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void recuperarReservas(){
        Gestor gestor = new Gestor(MyReservas.this);
        listReservas = new ArrayList<>();
        listReservas = gestor.recuperarReservas(obtenerUser());
        adapter = new MyReservasAdapter
                (MyReservas.this, R.layout.list_object_reserva, listReservas);
        listview.setAdapter(adapter);
    }

    private String obtenerUser() {

        //Llamada a la activity DbErp para usar el método recuperarunuser
        Gestor gestor = new Gestor(MyReservas.this);

        //Leemos el usuario del archivo en el que lo hemos guardado y lo guardamos en una string
        File log = getApplication().getFilesDir();
        File file = new File(log, "user.txt");
        String filePath = file.getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            sb.append(line);
            user2 = sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user2;
    }
}