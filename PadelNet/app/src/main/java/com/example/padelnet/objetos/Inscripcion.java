package com.example.padelnet.objetos;

public class Inscripcion {

    private String user, competi, fecha;

    public Inscripcion(){

    }

    public Inscripcion(String user, String competi, String fecha){
        this.user = user;
        this.competi = competi;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCompeti() {
        return competi;
    }

    public void setCompeti(String competi) {
        this.competi = competi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
