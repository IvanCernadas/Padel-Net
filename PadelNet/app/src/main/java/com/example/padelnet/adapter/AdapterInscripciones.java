package com.example.padelnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.padelnet.R;
import com.example.padelnet.objetos.Inscripcion;


import java.util.ArrayList;

public class AdapterInscripciones extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Inscripcion> listobj;

    public AdapterInscripciones(Context context, int layout, ArrayList<Inscripcion> listobj){
        this.context = context;
        this.layout = layout;
        this.listobj = listobj;
    }

    @Override
    public int getCount() {
        return this.listobj.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listobj.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater inflate = LayoutInflater.from(this.context);
        v = inflate.inflate(R.layout.list_object_inscripciones, null);

        //Asociamos
        TextView nombreUser = v.findViewById(R.id.user);
        TextView nombreCompeti = v.findViewById(R.id.competi);
        TextView fechaCompeti = v.findViewById(R.id.fecha);

        //Definiciones
        String nombre = listobj.get(position).getUser();
        String competi = listobj.get(position).getCompeti();
        String fecha = listobj.get(position).getFecha();

        //Cambiamos txt y foto
        nombreUser.setText("Usuario: "+nombre);
        nombreCompeti.setText(competi);
        fechaCompeti.setText("Fecha: "+fecha);

        return v;
    }
}
