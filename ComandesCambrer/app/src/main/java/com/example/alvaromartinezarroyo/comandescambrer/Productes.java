package com.example.alvaromartinezarroyo.comandescambrer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class Productes extends ActionBarActivity implements View.OnClickListener {

    private DBManager manager;
    Cursor cursor;
    private ListView llista;
    ItemListAdapter adapter;
    private TextView texte;
    private Button boto;
    private ImageView fotito;
    private Button botonagregarproducto;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productes);

        manager = new DBManager(this);

        llista = (ListView) findViewById(R.id.listView);
        texte = (TextView) findViewById(R.id.editText);
        boto = (Button) findViewById(R.id.button);
        boto.setOnClickListener(this);

        //ACCION AGREGAR PRODUCTO

        botonagregarproducto = (Button) findViewById(R.id.button5);
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Productes.this, AgregarProducto.class));
            }
        });

        //PRUEBAS ELIMINAR
        //manager.eliminar("Alvaro");
        //manager.eliminar("Erick");

        //PRUEBAS INSERTAR

        //manager.insertar("Alvaro", "3", 10);
        //manager.insertar("Erick", "4", 9);

        cursor = manager.cargarCursorProductes();

        adapter = new ItemListAdapter(this, cursor);
        llista.setAdapter(adapter);

        //Botón Atrás
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                nombre = (TextView) view.findViewById(R.id.textolistview);
                String aux_nombre = nombre.getText().toString();

                Intent consultar_intent = new Intent(Productes.this, Consultar_Producte.class);
                consultar_intent.putExtra("nombre", aux_nombre);
                startActivity(consultar_intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button){
            Cursor c = manager.buscarProducte(texte.getText().toString());
            adapter.changeCursor(c);
        }
    }

}
