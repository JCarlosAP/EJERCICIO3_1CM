package com.example.ejercicio3_1cm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ejercicio3_1cm.Adaptador;
import com.example.ejercicio3_1cm.Alumno;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity{
    Adaptador adaptador;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<Alumno> alumnos = (ArrayList<Alumno>) getIntent().getSerializableExtra("ArreAlu") ;

        adaptador = new Adaptador(this,alumnos);

        lv = findViewById(R.id.lv);

        lv.setAdapter(adaptador);



    }
}