package com.example.padelnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.padelnet.R;
import com.example.padelnet.objetos.Reserva;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyReservasAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Reserva> listobj;

    public MyReservasAdapter(Context context, int layout, ArrayList<Reserva> listobj) {
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
        TextView numPista = v.findViewById(R.id.textView3);
        TextView fechaPista = v.findViewById(R.id.textView2);
        TextView horaPista = v.findViewById(R.id.hora);
        ImageView fotoPista = v.findViewById(R.id.imageView3);

        //Definiciones
        String pista = listobj.get(position).getId()+"";
        String fecha = listobj.get(position).getFecha();
        String hora = listobj.get(position).getHora();

        //Cambiamos imagen
        switch (listobj.get(position).getId()){
            case 1: fotoPista.setImageResource(R.drawable.pistauno);
                break;
            case 2: fotoPista.setImageResource(R.drawable.pistados);
                break;
            case 3: fotoPista.setImageResource(R.drawable.pistatres);
                break;
            default: break;
        }

        //Cambiamos txt y foto
        numPista.setText("Pista: "+ pista);
        fechaPista.setText("Fecha: "+fecha);
        horaPista.setText("Hora: "+hora);

        return v;
    }
}
