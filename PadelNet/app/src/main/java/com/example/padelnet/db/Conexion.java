package com.example.padelnet.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Conexion extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbpadel.sqlite";
    private static final int DB_VERSION = 1;

    public Conexion(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Usuario                (nombre text primary key, email text, pass text)");

        db.execSQL("Create table Admin                  (nombre text primary key, pass text)");

        db.execSQL("Create table Pista                  (id integer primary key autoincrement, foto text, nombre_admin text,"
                +" foreign key      (nombre_admin)      references      Admin       (nombre))");

        db.execSQL("Create table Competicion            (nombre text primary key, foto text, nombre_admin text, fecha text,"
                +" foreign key      (nombre_admin)      references      Admin       (nombre))");

        db.execSQL("Create table Inscribir_competicion  (user_nombre text, competi_nombre text, fecha text, primary key(user_nombre, competi_nombre, fecha),"
                +" foreign key      (user_nombre)       references      Usuario(nombre), "
                +" foreign key      (competi_nombre)    references      Competicion (nombre))");

        db.execSQL("Create table Reserva_Pista           (user_nombre text, id_pista integer, fecha text, hora text, primary key(user_nombre, id_pista, fecha, hora),"
                +" foreign key      (user_nombre)       references      Usuario     (nombre), "
                +" foreign key      (id_pista)          references      Pista       (id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
