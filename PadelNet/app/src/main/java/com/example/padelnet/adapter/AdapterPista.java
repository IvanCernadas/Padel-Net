package com.example.padelnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.padelnet.R;
import com.example.padelnet.objetos.Pista;

import java.util.ArrayList;

public class AdapterPista extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Pista> listobj;

    public AdapterPista(Context context, int layout, ArrayList<Pista> listobj){
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
        v = inflate.inflate(R.layout.list_object_reserva, null);

        //Asociamos
        TextView idPista = v.findViewById(R.id.textView3);
        ImageView fotoPista = v.findViewById(R.id.imageView3);

        //Definiciones
        int id = listobj.get(position).getId();

        //Aplicamos texto y foto
        idPista.setText("Pista: "+id+"");

        switch (id){
            case 1:
                fotoPista.setImageResource(R.drawable.pistauno);
                break;
            case 2:
                fotoPista.setImageResource(R.drawable.pistados);
                break;
            case 3:
                fotoPista.setImageResource(R.drawable.pistatres);
                break;
            default:
                break;
        }

        return v;
    }
}
