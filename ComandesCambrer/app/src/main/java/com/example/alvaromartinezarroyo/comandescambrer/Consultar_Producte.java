package com.example.alvaromartinezarroyo.comandescambrer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Consultar_Producte extends ActionBarActivity implements View.OnClickListener {


    private TextView textonombre;
    private TextView textoprecio;
    private TextView textounidades;
    private ImageView foto;
    private Button botonreponer;
    private Button botoneliminar;
    private DBManager manager;

    private String nombre;
    private double precio;
    private Integer unidades;
    private byte[] bitMapData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__producte);

        manager = new DBManager(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String nombre_aux = i.getStringExtra("nombre");

        textonombre = (TextView) findViewById(R.id.textView7);
        nombre = nombre_aux;
        textonombre.setText(nombre_aux);

        Cursor c = manager.buscarProducte(nombre_aux);
        c.moveToFirst();

        double precio = c.getDouble(3);
        String aux = "";
        aux = String.valueOf(precio);
        textoprecio = (TextView) findViewById(R.id.textView9);
        textoprecio.setText(aux);

        Integer unidades = c.getInt(4);
        String aux2 = "";
        aux2 = String.valueOf(unidades);
        textounidades = (TextView) findViewById(R.id.textView12);
        textounidades.setText(aux2);

        bitMapData = c.getBlob(2);
        Bitmap bm = BitmapFactory.decodeByteArray(c.getBlob(2), 0, c.getBlob(2).length);
        foto = (ImageView) findViewById(R.id.imageView3);
        foto.setImageBitmap(bm);

        botoneliminar = (Button) findViewById(R.id.button12);
        botoneliminar.setOnClickListener(this);

        botonreponer = (Button) findViewById(R.id.button9);
        botonreponer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button12){
            manager.eliminar(textonombre.getText().toString());
            startActivity(new Intent(Consultar_Producte.this, Productes.class));
        }
        if (view.getId() == R.id.button9){
            Cursor c = manager.buscarProducte(textonombre.getText().toString());
            c.moveToFirst();
            manager.modificarUnitats(textonombre.getText().toString(), c.getBlob(2), c.getDouble(3), 10);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

}
