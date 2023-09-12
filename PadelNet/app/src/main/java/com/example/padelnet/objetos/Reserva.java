package com.example.padelnet.objetos;

public class Reserva {

    private String fecha, user, hora;
    private int id;

    public Reserva(){

    }

    public Reserva(int idPista, String user, String fecha, String hora){
        this.id = idPista;
        this.fecha = fecha;
        this.user = user;
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
