package com.example.padelnet.acceso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.padelnet.R;
import com.example.padelnet.db.Conexion;
import com.example.padelnet.db.Gestor;
import com.example.padelnet.funcionamiento.MyReservas;
import com.example.padelnet.funcionamiento.Root;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Login extends AppCompatActivity {

    Button register, login;
    EditText name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ocultamos la ActionBar
        getSupportActionBar().hide();

        //Declaraciones de todos los elementos del layout
        name = findViewById(R.id.edit_text_name);
        pass = findViewById(R.id.edit_text_password);
        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_login);

        //Creamos el administrador root por si no existe
        if(!rootExists()){
            insertAdmin();
        }
        if(!pistasExists()){
            insertPistas();
        }
        if(!campeonatoExists()){
            insertCampeonatos();
        }

        //Al hacer click en login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    if(name.getText().toString().isEmpty()){
                        name.setError("Campo Vacío");
                        name.requestFocus();
                    }
                    if (pass.getText().toString().isEmpty()){
                        pass.setError("Campo Vacío");
                        pass.requestFocus();
                    }
                }else{
                    if(!dbuserExists(name.getText().toString())){
                        if(userContraCoinciden(name.getText().toString(), pass.getText().toString())){
                            //Archivo para guardar el usuario
                            try {
                                File log2 = new File(getFilesDir(), "user.txt");
                                BufferedWriter writer = new BufferedWriter(new FileWriter(log2));
                                writer.write(name.getText().toString());
                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if(name.getText().toString().equalsIgnoreCase("root")) {
                                Intent intent = new Intent(Login.this, Root.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }else{
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        }else{
                            pass.setError("Contraseña mal introducida");
                            pass.requestFocus();
                        }
                    }else{
                        if(verificarAdmin(name.getText().toString())){
                            if(verificarPassAdmin(name.getText().toString(), pass.getText().toString())) {
                                Intent intent = new Intent(Login.this, Root.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }else{
                                pass.setError("Contraseña admin mal introducida");
                                pass.requestFocus();
                            }
                        }else {
                            name.setError("Usuario no registrado en la base de datos");
                            name.requestFocus();
                        }
                    }
                }
            }
        });

        //Al hacer click en register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public boolean dbuserExists(String nombre){
        Gestor dbusercontra = new Gestor(Login.this);
        boolean contra_real = dbusercontra.userExists(nombre);

        if(contra_real){
            return true;
        }else{
            return false;
        }
    }

    public boolean userContraCoinciden(String nombre, String contra){
        Gestor dbusercontra = new Gestor(Login.this);
        String contra_real = dbusercontra.verificarContrasenhia(nombre);

        if(contra_real.equalsIgnoreCase(contra)){
            return true;
        }else{
            return false;
        }
    }

    public boolean rootExists(){
        Gestor dbusercontra = new Gestor(Login.this);

        if(dbusercontra.verificarRoot()){
            return true;
        }else{
            return false;
        }
    }

    public void insertAdmin(){
        Gestor root = new Gestor(Login.this);
        root.insertRoot();
    }

    public boolean verificarAdmin(String nombre){
        Gestor verify = new Gestor(Login.this);

        if(verify.adminExists(nombre)){
            return true;
        }else{
            return false;
        }
    }

    public boolean verificarPassAdmin(String nombre, String pass){
        Gestor verify = new Gestor(Login.this);

        String contra_real = verify.verificarPassAdmin(nombre);

        if(contra_real.equalsIgnoreCase(pass)){
            return true;
        }else{
            return false;
        }
    }

    public boolean pistasExists(){
        Gestor verify = new Gestor(Login.this);

        if(verify.pistasExists()){
            return true;
        }else{
            return false;
        }
    }

    public void insertPistas(){
        Gestor verify = new Gestor(Login.this);
        verify.insertPistas();
    }

    public boolean campeonatoExists(){
        Gestor verify = new Gestor(Login.this);

        if(verify.verificarCampeonatos()){
            return true;
        }else{
            return false;
        }
    }

    public void insertCampeonatos(){
        Gestor verify = new Gestor(Login.this);
        verify.insertCampeonatos();
    }
}