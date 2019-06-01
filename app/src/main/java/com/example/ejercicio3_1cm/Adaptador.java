package com.example.ejercicio3_1cm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejercicio3_1cm.Alumno;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    ArrayList<Alumno> alumnos;
    private String ap;
    private String am;
    private String edad;
    private String gener;
    private static LayoutInflater inflater = null;


    public Adaptador(Context contexto, ArrayList<Alumno> alumnos) {

        this.contexto = contexto;
        this.alumnos = alumnos;
        this.am= am;
        this.edad=edad;
        this.ap=ap;
        this.gener=gener;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return alumnos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return alumnos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista,null);

        TextView tvNombreLista = vista.findViewById(R.id.tvNombreLista);
        TextView tvApellidoP = vista.findViewById(R.id.tvApellidoP);
        TextView tvApellidoM = vista.findViewById(R.id.tvApellidoM);
        TextView tvGenero= vista.findViewById(R.id.tvEdad);
        TextView tvCuentaA= vista.findViewById(R.id.tvCuentaAlu);
        tvNombreLista.setText(alumnos.get(position).getId());
        tvApellidoM.setText(alumnos.get(position).getNombre());
        tvApellidoP.setText(alumnos.get(position).getLugar());
        tvGenero.setText(alumnos.get(position).getstart());
        tvCuentaA.setText(alumnos.get(position).getend());
        return vista;
    }

}
