package com.example.padelnet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.padelnet.objetos.Campeonato;
import com.example.padelnet.objetos.Inscripcion;
import com.example.padelnet.objetos.Pista;
import com.example.padelnet.objetos.Reserva;

import java.util.ArrayList;

public class Gestor extends Conexion{
    private Context context;
    ArrayList<Pista> listPistas;
    ArrayList<Campeonato> listCampeonato;
    private int count;

    //Conexión DB
    public Gestor(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public ArrayList<Campeonato> obtenerCampeonatos(){
        Conexion conex = new Conexion(context);
        SQLiteDatabase db = conex.getReadableDatabase();

        ArrayList<Campeonato> listaCampeonato = new ArrayList<>();
        Campeonato reserva = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Competicion ",null);

        if (cursor.moveToFirst()){
            do {
                reserva = new Campeonato();
                reserva.setName(cursor.getString(0));
                reserva.setFoto(cursor.getString(1));
                reserva.setRoot(cursor.getString(2));
                reserva.setFecha(cursor.getString(3));

                listaCampeonato.add(reserva);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return listaCampeonato;
    }

    public void insertCampeonatos(){

        String nombre[]= {"Split de Verano", "Partido Liga", "Campeón de Galicia"};
        String foto[] = {"torneo1", "torneo2", "torneo3"};
        String nombre_admin[] = {"root","root","root"};
        String fecha[] = {"4-8-2023 (17:00 - 21:00)","31-8-2023 (18:00 - 22:00)", "10-9-2023 (17:00 - 22:00)"};

        int count=0;
        listCampeonato = new ArrayList<Campeonato>();
        do {
            Conexion admin = new Conexion(context);
            SQLiteDatabase bd;
            try {
                bd = admin.getWritableDatabase();
                if (bd != null) {

                    String p_nombre = nombre[count];
                    String p_foto = foto[count];
                    String p_nombre_admin = nombre_admin[count];
                    String p_fecha = fecha[count];

                    //Creción del objeto
                    Campeonato co = new Campeonato(p_nombre, p_foto, p_fecha, p_nombre_admin);

                    //Añadimos al ArrayList
                    listCampeonato.add(co);

                    ContentValues registro = new ContentValues();

                    registro.put("nombre", p_nombre);
                    registro.put("foto", p_foto);
                    registro.put("nombre_admin", p_nombre_admin);
                    registro.put("fecha",p_fecha);

                    bd.insert("Competicion", null, registro);

                    bd.close();
                } else
                    Toast.makeText(context, "Base de datos no creada", Toast.LENGTH_SHORT).show();
            } catch (SQLiteException ex) {
                bd = admin.getReadableDatabase();
                Toast.makeText(context, "No se puede escribir la Base de Datos", Toast.LENGTH_SHORT).show();
            }
            bd.close();
            count++;
        }while(count!=3);
    }

    public void insertPistas(){

        int id[] = {1,2,3};
        String foto[] = {"pista1", "pista2", "pista3"};
        String nombre_admin[] = {"root","root","root"};

        int count=0;
        listPistas = new ArrayList<Pista>();
        do {
            Conexion admin = new Conexion(context);
            SQLiteDatabase bd;
            try {
                bd = admin.getWritableDatabase();
                if (bd != null) {
                    int p_id = id[count];
                    String p_foto = foto[count];
                    String p_admin = nombre_admin[count];

                    //Creción del objeto
                    Pista co = new Pista(p_id,p_foto,p_admin);

                    //Añadimos al ArrayList
                    listPistas.add(co);

                    ContentValues registro = new ContentValues();

                    registro.put("id", p_id);
                    registro.put("foto", p_foto);
                    registro.put("nombre_admin", p_admin);

                    bd.insert("Pista", null, registro);

                    bd.close();
                } else
                    Toast.makeText(context, "Base de datos no creada", Toast.LENGTH_SHORT).show();
            } catch (SQLiteException ex) {
                bd = admin.getReadableDatabase();
                Toast.makeText(context, "No se puede escribir la Base de Datos", Toast.LENGTH_SHORT).show();
            }
            bd.close();
            count++;
        }while(count!=3);
    }


    public void insertReserva(int id, String user, String fecha, String hora){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_nombre", user);
        contentValues.put("id_pista", id);
        contentValues.put("fecha",fecha);
        contentValues.put("hora", hora);

        db.insert("Reserva_Pista",null,contentValues);
    }

    public void insertInscripcion(String user,String competi, String fecha){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_nombre", user);
        contentValues.put("competi_nombre", competi);
        contentValues.put("fecha",fecha);

        db.insert("Inscribir_competicion",null,contentValues);
    }

    public boolean verificarFecha(String fechaBuscar, String horaBuscar, int idPista, String user){
        //Comprobamos si la base de datos está vacía
        int count = 0;
        Conexion mDbHelper = new Conexion(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM Reserva_Pista" +
                " where fecha = \""+fechaBuscar+"\" and hora = \""+horaBuscar+"\" and " +
                        "id_pista = \""+idPista+"\"",null);
        try {
            if(cursor != null)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if(count>0)
            return false;
        else
            return true;
    }

    public boolean verificarCampeonatos(){
        //Comprobamos si la base de datos está vacía
        int count = 0;

        Conexion mDbHelper = new Conexion(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM Competicion",null);
        try {
            if(cursor != null)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if(count>0)
            return true;
        else
            return false;
    }

    public boolean verificarRoot(){
        //Comprobamos si la base de datos está vacía
        int count = 0;
        String nombret = "root";

        Conexion mDbHelper = new Conexion(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM Admin " +
                "where nombre = \""+nombret+"\"", null);
        try {
            if(cursor != null)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if(count>0)
            return true;
        else
            return false;
    }

    public boolean adminExists(String nombret){
        //Comprobamos si la base de datos está vacía
        int count = 0;
        Conexion mDbHelper = new Conexion(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM Admin " +
                "where nombre = \""+nombret+"\"", null);
        try {
            if(cursor != null)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if(count>0)
            return true;
        else
            return false;
    }

    public boolean pistasExists(){
        //Comprobamos si la base de datos está vacía
        int count = 0;
        Conexion mDbHelper = new Conexion(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM Pista ",null);
        try {
            if(cursor != null)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if(count>0)
            return true;
        else
            return false;
    }

    public boolean userExists(String nombret){
        //Comprobamos si la base de datos está vacía
        int count = 0;
        Conexion mDbHelper = new Conexion(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM Usuario " +
                "where nombre = \""+nombret+"\"", null);
        try {
            if(cursor != null)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if(count>0)
            return false;
        else
            return true;
    }

    public String verificarPassAdmin(String nombreBusq){

        Conexion gestor = new Conexion(context);
        SQLiteDatabase db = gestor.getReadableDatabase();
        Cursor cursor = null;
        String f = null;

        cursor = db.rawQuery("SELECT pass FROM Admin " +
                "where nombre=\""+nombreBusq+"\"", null);

        if(cursor.moveToFirst()){
            do {
                f = cursor.getString(0);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return f;
    }

    public String verificarContrasenhia(String nombreBusq){

        Conexion gestor = new Conexion(context);
        SQLiteDatabase db = gestor.getReadableDatabase();
        Cursor cursor = null;
        String f = null;

        cursor = db.rawQuery("SELECT pass FROM Usuario " +
                "where nombre=\""+nombreBusq+"\"", null);

        if(cursor.moveToFirst()){
            do {
                f = cursor.getString(0);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return f;
    }


    public ArrayList<Reserva> recuperarReservas(String nombre){

        Conexion conex = new Conexion(context);
        SQLiteDatabase db = conex.getReadableDatabase();

        ArrayList<Reserva> listaReservas = new ArrayList<>();
        Reserva reserva = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Reserva_Pista " +
                "WHERE user_nombre = \""+nombre+"\"", null);

        if (cursor.moveToFirst()){
            do {
                reserva = new Reserva();
                reserva.setUser(cursor.getString(0));
                reserva.setId(cursor.getInt(1));
                reserva.setFecha(cursor.getString(2));
                reserva.setHora(cursor.getString(3));

                listaReservas.add(reserva);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return listaReservas;
    }

    public void insertRoot(){
        Conexion conexion = new Conexion(context);
        SQLiteDatabase bd;

        try {
            bd = conexion.getWritableDatabase();
            if (bd != null) {

                ContentValues registro = new ContentValues();
                registro.put("nombre", "root");
                registro.put("pass", "toor");

                bd.insert("Admin", null, registro);

                bd.close();
            } else
                Toast.makeText(context, "Base de datos no creada", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException ex) {
            bd = conexion.getReadableDatabase();
            Toast.makeText(context, "No se puede escribir la Base de Datos", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void insertUser(String campo_nombre, String campo_email, String campo_pass){
        Conexion conexion = new Conexion(context);
        SQLiteDatabase bd;

        try {
            bd = conexion.getWritableDatabase();
            if (bd != null) {

                ContentValues registro = new ContentValues();
                registro.put("nombre", campo_nombre);
                registro.put("email", campo_email);
                registro.put("pass", campo_pass);

                bd.insert("Usuario", null, registro);

                bd.close();
            } else
                Toast.makeText(context, "Base de datos no creada", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException ex) {
            bd = conexion.getReadableDatabase();
            Toast.makeText(context, "No se puede escribir la Base de Datos", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void deleteReserva(String fecha, String hora){

        Conexion conex = new Conexion(context);
        SQLiteDatabase db = conex.getWritableDatabase();

        String tabla = "Reserva_Pista";
        String whereClause = "fecha = \""+fecha+"\" and hora = \""+hora+"\"";
        db.delete(tabla, whereClause, null);
    }

    public ArrayList<Pista> recuperarTodasLasPistas(){

        Conexion conex = new Conexion(context);
        SQLiteDatabase db = conex.getReadableDatabase();

        ArrayList<Pista> listAllPistas = new ArrayList<>();
        Pista reserva = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Pista", null);

        if (cursor.moveToFirst()){
            do {
                reserva = new Pista();

                reserva.setId(cursor.getInt(0));
                reserva.setFoto(cursor.getString(1));

                listAllPistas.add(reserva);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return listAllPistas;
    }

    public ArrayList<Reserva> recuperarTodasLasReservas(){

        Conexion conex = new Conexion(context);
        SQLiteDatabase db = conex.getReadableDatabase();

        ArrayList<Reserva> listAllReservas = new ArrayList<>();
        Reserva reserva = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Reserva_Pista", null);

        if (cursor.moveToFirst()){
            do {
                reserva = new Reserva();

                reserva.setUser(cursor.getString(0));
                reserva.setId(cursor.getInt(1));
                reserva.setFecha(cursor.getString(2));
                reserva.setHora(cursor.getString(3));

                listAllReservas.add(reserva);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return listAllReservas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(){

        Conexion conex = new Conexion(context);
        SQLiteDatabase db = conex.getReadableDatabase();

        ArrayList<Inscripcion> listInscripciones = new ArrayList<>();
        Inscripcion reserva = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Inscribir_competicion", null);

        if (cursor.moveToFirst()){
            do {
                reserva = new Inscripcion();

                reserva.setUser(cursor.getString(0));
                reserva.setCompeti(cursor.getString(1));
                reserva.setFecha(cursor.getString(2));

                listInscripciones.add(reserva);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return listInscripciones;
    }
}
