package com.example.ejercicio3_1cm;

import android.content.Context;
import android.view.View;

import com.example.ejercicio3_1cm.R;

import java.io.Serializable;

public class Alumno implements Serializable {
    private int id;
    private String nombrecurso;
    private String datestart;
    private String dateend;
    private String sed;
    //private Context contexto;

    public Alumno(int id, String nombrecurso, String sed, String datestart, String dateend) {
        this.id = id;
        this.nombrecurso = nombrecurso;
        this.sed = sed;
        this.datestart =datestart;
        this.dateend = dateend;
        //this.contexto = contexto;

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombrecurso;
    }
    public void setNombre(String nombre) {
        this.nombrecurso = nombrecurso;
    }

    public String getLugar() {
        return sed;
    }
    public void setLugar(String sed) {
        this.sed = sed;
    }

    public String getstart() {
        return datestart;
    }
    public void setstart(String datestart){
        this.datestart = datestart;
    }

    public String getend() {
        return dateend;
    }
    public void setend(String dateend){
        this.dateend = dateend;
    }


//    public Context getContexto() {
  //      return contexto;
    //}

    //public void setContexto(Context contexto) {
      //  this.contexto = contexto;
    //}

    //@Override
   // public String toString() {
       // return contexto.getResources().getString(R.string.texto_nombre)
         //       + getNombre();
    //}


}