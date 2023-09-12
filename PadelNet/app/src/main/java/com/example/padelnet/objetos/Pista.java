package com.example.padelnet.objetos;

public class Pista {

    private int id;
    private String foto, nombre_admin;

    public Pista(){

    }

    public Pista(int id, String foto, String nombre_admin){
        this.id = id;
        this.foto = foto;
        this.nombre_admin = nombre_admin;
    }

    public String getNombre_admin() {
        return nombre_admin;
    }

    public void setNombre_admin(String nombre_admin) {
        this.nombre_admin = nombre_admin;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
