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
import com.example.padelnet.objetos.Reserva;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterAllReservas extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Reserva> listobj;

    public AdapterAllReservas(Context context, int layout, ArrayList<Reserva> listobj){
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
        v = inflate.inflate(R.layout.list_object_all_reservas, null);

        //Asociamos
        TextView idReserva = v.findViewById(R.id.textView3);
        TextView fechaReserva = v.findViewById(R.id.textView2);
        TextView horaReserva = v.findViewById(R.id.textView3);
        TextView userReserva = v.findViewById(R.id.user);
        ImageView fotoPista = v.findViewById(R.id.imageView3);


        //Definiciones
        int id = listobj.get(position).getId();
        String user = listobj.get(position).getUser();
        String fecha = listobj.get(position).getFecha();
        String hora = listobj.get(position).getHora();

        //Aplicamos texto y foto
        idReserva.setText("Pista: "+id+"");
        fechaReserva.setText("Fecha: "+fecha);
        horaReserva.setText("Hora: "+hora+"");
        userReserva.setText("Usuario: "+user);

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
