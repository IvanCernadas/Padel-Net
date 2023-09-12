package com.example.padelnet.acceso;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.padelnet.R;
import com.example.padelnet.db.Conexion;
import com.example.padelnet.db.Gestor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends AppCompatActivity {

    Button register;
    EditText name, email, pass, verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Ocultamos la ActionBar
        getSupportActionBar(). hide();

        //Declaraciones de todos los elementos del layout
        register = findViewById(R.id.btn_register);
        name = findViewById(R.id.edit_text_name);
        email = findViewById(R.id.edit_text_email);
        verify = findViewById(R.id.edit_text_confirm_password);
        pass = findViewById(R.id.edit_text_password);

        //Al hacer click en el botón de registro
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String campo_nombre = name.getText().toString();
                String campo_email = email.getText().toString();
                String campo_contra = pass.getText().toString();
                String campo_verify = verify.getText().toString();

                //Comprobamos que todos los campos sean válidos
                if (TextUtils.isEmpty(campo_nombre) || TextUtils.isEmpty(campo_email)
                        || TextUtils.isEmpty(campo_contra) || TextUtils.isEmpty(campo_verify)
                        || !campo_contra.equalsIgnoreCase(campo_verify) || !dbuserExists(campo_nombre)){

                    if (TextUtils.isEmpty(campo_nombre)) {
                        name.setError("Introduce un nombre");
                        name.requestFocus();
                    } else if (!dbuserExists(campo_nombre)) {
                        name.setError("Ya existe un usuario con este nombre");
                        name.requestFocus();
                    } else if (TextUtils.isEmpty(campo_email)) {
                        email.setError("Introduce un email");
                        email.requestFocus();
                    } else if (TextUtils.isEmpty(campo_contra)) {
                        pass.setError("Introduce una contraseña");
                        pass.requestFocus();
                    } else if (TextUtils.isEmpty(campo_verify)) {
                        verify.setError("Introduce una contraseña");
                        verify.requestFocus();
                    } else if (!campo_contra.equalsIgnoreCase(campo_verify)) {
                        verify.setError("Las contraseñas no coinciden");
                        verify.requestFocus();
                    }
                } else {

                    //Insertamos el usuario en la base de datos
                    Gestor gestor = new Gestor(Register.this);
                    gestor.insertUser(campo_nombre, campo_email, campo_contra);

                    //Archivo para guardar el usuario
                    try {
                        File log2 = new File(getFilesDir(), "user.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(log2));
                        writer.write(name.getText().toString());
                        writer.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(Register.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public boolean dbuserExists(String nombre){
        //Método para ver si existe el usuario introducido en la base de datos o no
        Gestor gestor = new Gestor(Register.this);
        boolean contra_real = gestor.userExists(nombre);

        if(contra_real){
            return true;
        }else{
            return false;
        }
    }
}
