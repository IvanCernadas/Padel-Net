package com.example.padelnet.objetos;

public class Campeonato {

    String name, fecha, root, foto;

    public Campeonato() {
    }

    public Campeonato(String name, String foto, String fecha, String root) {
        this.name = name;
        this.fecha = fecha;
        this.root = root;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}


