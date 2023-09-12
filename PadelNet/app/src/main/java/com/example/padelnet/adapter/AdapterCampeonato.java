package com.example.padelnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.padelnet.R;
import com.example.padelnet.objetos.Campeonato;

import java.util.ArrayList;

public class AdapterCampeonato extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Campeonato> listobj;

    public AdapterCampeonato(Context context, int layout, ArrayList<Campeonato> listobj){
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
        v = inflate.inflate(R.layout.list_object_campeonato, null);

        //Asociamos
        TextView nombreTorneo = v.findViewById(R.id.textView3);
        TextView fechaTorneo = v.findViewById(R.id.textView7);

        //Definiciones
        String nombre = listobj.get(position).getName();
        String fecha = listobj.get(position).getFecha();

        //Cambiamos txt y foto
        nombreTorneo.setText(nombre);
        fechaTorneo.setText("Fecha: "+fecha);

        return v;
    }
}
