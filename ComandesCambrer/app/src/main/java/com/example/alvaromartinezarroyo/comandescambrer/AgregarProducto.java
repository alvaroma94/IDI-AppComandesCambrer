package com.example.alvaromartinezarroyo.comandescambrer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AgregarProducto extends ActionBarActivity implements View.OnClickListener{

    private static final int SELECT_PICTURE = 2;
    private Button cargarfoto;
    private Button crearproducto;
    private TextView nombreprod;
    private EditText nombreprodintroducido;
    private TextView precioprod;
    private EditText precioprodintroducido;
    private TextView unidadesprod;
    private EditText unidadesprodintroducido;
    private ImageView fotoprod;

    private DBManager manager;

    private byte[] bitMapData;
    private String aux_nombre;
    private double precio;
    private String aux_precio;
    private String aux_unidades;
    private Bitmap bitmap1;
    private Integer unidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        cargarfoto = (Button) findViewById(R.id.button10);
        cargarfoto.setOnClickListener(this);
        crearproducto = (Button) findViewById(R.id.button6);
        crearproducto.setOnClickListener(this);
        nombreprod = (TextView) findViewById(R.id.textView);
        nombreprodintroducido = (EditText) findViewById(R.id.editText3);
        //precioprod = (TextView) findViewById(R.id.textView2);
        precioprodintroducido = (EditText) findViewById(R.id.editText5);
        unidadesprod = (TextView) findViewById(R.id.textView5);
        unidadesprodintroducido = (EditText) findViewById(R.id.editText2);
        fotoprod = (ImageView) findViewById(R.id.imageView2);

        manager = new DBManager(this);

        //aux_nombre = nombreprodintroducido.getText().toString();

        //aux_precio = precioprodintroducido.getText().toString();

        //aux_unidades = unidadesprodintroducido.getText().toString();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button10){
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_PICTURE);
        }
        if (view.getId() == R.id.button6){
            manager.insertar(nombreprodintroducido.getText().toString(), bitMapData, new Double(precioprodintroducido.getText().toString()), new Integer(unidadesprodintroducido.getText().toString()));
            startActivity(new Intent(AgregarProducto.this, Productes.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImage = data.getData();
        InputStream is;
        try {
            is = getContentResolver().openInputStream(selectedImage);
            BufferedInputStream bis = new BufferedInputStream(is);
            Bitmap bitmap = BitmapFactory.decodeStream(bis);
            fotoprod.setImageBitmap(bitmap);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            bitMapData = stream.toByteArray();
        } catch (FileNotFoundException e) {}
    }
}
