package com.example.padelnet.funcionamiento;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.padelnet.R;
import com.example.padelnet.acceso.MainActivity;
import com.example.padelnet.db.Gestor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NewReserva extends AppCompatActivity {

    ImageView foto, derecha, izquierda;
    CalendarView calendarView;
    Button volver;
    ArrayList<Integer> imagePistasList = new ArrayList<>();
    int count;
    String user;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reserva);

        //Ocultamos la ActionBar
        getSupportActionBar().hide();
        
        //Definiciones
        calendarView = findViewById(R.id.calendarView);
        foto = findViewById(R.id.imageView2);
        derecha = findViewById(R.id.flecha_derecha);
        izquierda = findViewById(R.id.flecha_izquierda);
        volver = findViewById(R.id.btn_volver);
        titulo = findViewById(R.id.textView1);

        //Guardamos todas las imágenes de pistas existentes
        imagePistasList.add(R.drawable.pistauno);
        imagePistasList.add(R.drawable.pistados);
        imagePistasList.add(R.drawable.pistatres);

        //aplicamos la imágen en la posición del arraylist
        foto.setImageResource(imagePistasList.get(count));
        titulo.setText("Pista "+sumarUnoContador());

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantallaDeAcceso();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Crea un objeto AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                //set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.alert_dialog_fecha_reserva, null);
                builder.setView(customLayout);

                // Configura el mensaje del AlertDialog
                builder.setMessage("Selecciona una hora del día "+dayOfMonth+" de "+saberMes(month));

                // Crea y muestra el AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

                //Definición de todos los botones
                TextView cinco = customLayout.findViewById(R.id.cinco);
                TextView seis = customLayout.findViewById(R.id.seis);
                TextView siete = customLayout.findViewById(R.id.siete);
                TextView ocho = customLayout.findViewById(R.id.ocho);

                //Verificamos que fechas están disponibles para reservar
                if (!verificarFecha(year,month,dayOfMonth,"17:00 - 18:00", sumarUnoContador(),
                        obtenerUser(view))) cinco.setVisibility(View.INVISIBLE);

                if (!verificarFecha(year,month,dayOfMonth,"18:00 - 19:00", sumarUnoContador(),
                        obtenerUser(view))) seis.setVisibility(View.INVISIBLE);

                if (!verificarFecha(year,month,dayOfMonth,"19:00 - 20:00", sumarUnoContador(),
                        obtenerUser(view))) siete.setVisibility(View.INVISIBLE);

                if (!verificarFecha(year,month,dayOfMonth,"20:00 - 21:00", sumarUnoContador(),
                        obtenerUser(view))) ocho.setVisibility(View.INVISIBLE);

                //Formato de la fecha
                String insertFecha = dayOfMonth+"-"+month+"-"+year;

                cinco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gestor admin = new Gestor(builder.getContext());
                        admin.insertReserva(sumarUnoContador(), obtenerUser(v), insertFecha,"17:00 - 18:00");
                        dialog.dismiss();
                        Toast.makeText(NewReserva.this, "Reserva Realizada exitósamente", Toast.LENGTH_SHORT).show();
                        pantallaDeAcceso();
                    }
                });

                seis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gestor admin = new Gestor(builder.getContext());
                        admin.insertReserva(sumarUnoContador(), obtenerUser(v), insertFecha,"18:00 - 19:00");
                        dialog.dismiss();
                        Toast.makeText(NewReserva.this, "Reserva Realizada exitósamente", Toast.LENGTH_SHORT).show();
                        pantallaDeAcceso();
                    }
                });

                siete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gestor admin = new Gestor(builder.getContext());
                        admin.insertReserva(sumarUnoContador(), obtenerUser(v), insertFecha,"19:00 - 20:00");
                        dialog.dismiss();
                        Toast.makeText(NewReserva.this, "Reserva Realizada exitósamente", Toast.LENGTH_SHORT).show();
                        pantallaDeAcceso();
                    }
                });

                ocho.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gestor admin = new Gestor(builder.getContext());
                        admin.insertReserva(sumarUnoContador(), obtenerUser(v), insertFecha,"20:00 - 21:00");
                        dialog.dismiss();
                        Toast.makeText(NewReserva.this, "Reserva Realizada exitósamente", Toast.LENGTH_SHORT).show();
                        pantallaDeAcceso();
                    }
                });
            }
        });

        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el contador es igual al tamanio del arraylist, ponemos el contador a 0
                //y si no es así, sumamos uno al contador
                if(count == 2){
                    count=0;
                    titulo.setText("Pista "+sumarUnoContador());
                    foto.setImageResource(imagePistasList.get(count));
                }else{
                    count++;
                    titulo.setText("Pista "+sumarUnoContador());
                    foto.setImageResource(imagePistasList.get(count));
                }
            }
        });

        izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el contador es igual a 0, ponemos el mayor valor posible en el arraylist
                //y si no es así, restamos uno al contador
                if(count == 0){
                    count=2;
                    titulo.setText("Pista "+sumarUnoContador());
                    foto.setImageResource(imagePistasList.get(count));
                }else{
                    count--;
                    titulo.setText("Pista "+sumarUnoContador());
                    foto.setImageResource(imagePistasList.get(count));
                }
            }
        });
    }

    private boolean verificarFecha(int year, int month, int day, String momento, int idPista, String userr) {
        Gestor gestor = new Gestor(NewReserva.this);
        String fecha = day+"-"+month+"-"+year;

        if(gestor.verificarFecha(fecha, momento, idPista, userr)){
            return true;
        }else{
            return false;
        }
    }

    private String obtenerUser(View v) {

        Gestor gestor = new Gestor(NewReserva.this);

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

    private void pantallaDeAcceso(){
        Intent intent = new Intent(NewReserva.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public int sumarUnoContador(){
        int count2 = count +1;
        return count2;
    }

    public String saberMes(int month){
        switch(month){
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default: return "error";
        }
    }
}