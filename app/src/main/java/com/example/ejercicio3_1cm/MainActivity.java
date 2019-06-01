package com.example.ejercicio3_1cm;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.example.ejercicio3_1cm.Alumno;

import com.example.ejercicio3_1cm.Adaptador;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.example.ejercicio3_1cm.R.layout.activity_main;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    private static final String LOGTAG ="INFORMACION" ;
    Adaptador adaptador;
    private ListView lv;
    private Button mostrar;

    ByteArrayInputStream inputStream;
    ArrayList<Alumno> datos = new ArrayList<Alumno>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        mostrar = findViewById(R.id.button1);

        mostrar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                        Bundle ArregloAlu = new Bundle();
                        new ConexionHttp().execute("");
                        //ArregloAlu.putSerializable("ArreAlu",datos);
                        intent.putExtra("ArreAlu",datos);
                        startActivity(intent);

                    }

                });
        Log.d(LOGTAG,"DSDSDSDS");
    }

        public class ConexionHttp extends AsyncTask<String, Float, String> {

            boolean sinError = true;

            @Override
            protected String doInBackground(String... strings) {
                try {
                    StringBuffer fileData = new StringBuffer(4096);

                    URL sourceURL = new URL("https://serverbpw.com/cm/2019-2/cursos.php");

                    BufferedReader in = new BufferedReader(new InputStreamReader(sourceURL.openStream()));

                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        fileData.append(inputLine);
                    }

                    in.close();

                    inputStream = new ByteArrayInputStream(fileData.toString().getBytes());

                    DocumentBuilderFactory dbFabrica = DocumentBuilderFactory.newInstance();

                    DocumentBuilder dBuilder = dbFabrica.newDocumentBuilder();
                    Document doc = dBuilder.parse(inputStream);

                    Element elemento = doc.getDocumentElement();
                    elemento.normalize();

                    NodeList nList = doc.getElementsByTagName("item");

                    for (int i = 0; i < nList.getLength(); i++) {

                        Node nodo = nList.item(i);
                        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                            Element elemento2 = (Element) nodo;
                            Log.d("DATO", "id: " + obtenValor("id", elemento2));
                            Log.d("DATO", "nombre: " + obtenValor("nombre", elemento2));
                            Log.d("DATO", "tipo: " + obtenValor("sede", elemento2));
                            Log.d("DATO", "id: " + obtenValor("fechainic", elemento2));
                            Log.d("DATO", "nombre: " + obtenValor("fechafin", elemento2));
                            Alumno alu = new Alumno(Integer.parseInt(obtenValor("id", elemento2)), obtenValor("nombre", elemento2), obtenValor("sede", elemento2), obtenValor("fechainic", elemento2), obtenValor("fechafin", elemento2));
                            datos.add(alu);
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    sinError = false;
                }
                return null;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (sinError) {
                    for (int i = 0; i < datos.size(); i++) {
                        Toast.makeText(MainActivity.this, datos.get(i).getId(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Aviso")
                            .setMessage("Servicio no disponible en estos momentos.")
                            .setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    new ConexionHttp().execute("");
                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            new ConexionHttp().execute("");
                        }
                    }).setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                            .show();

                }
            }

        }

        private static String obtenValor (String tag, Element elemento){
            NodeList listaNodos = elemento.getElementsByTagName(tag).item(0).getChildNodes();
            Node nodo = listaNodos.item(0);
            return nodo.getNodeValue();
        }

    }