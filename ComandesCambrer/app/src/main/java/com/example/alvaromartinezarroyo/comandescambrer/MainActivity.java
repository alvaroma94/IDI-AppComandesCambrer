package com.example.alvaromartinezarroyo.comandescambrer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.ByteArrayOutputStream;


public class MainActivity extends ActionBarActivity {

    private DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DBManager(this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bravas2);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitMapData = stream.toByteArray();

        manager.insertar("Patatas Bravas", bitMapData, 3.50, 10);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.pincho);
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        byte[] bitMapData1 = stream1.toByteArray();

        manager.insertar("Pincho de Tortilla", bitMapData1, 1.80, 10);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.jamon);
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] bitMapData2 = stream2.toByteArray();

        manager.insertar("Jamón Ibérico", bitMapData2, 6.00, 10);

        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.refrescos);
        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
        bitmap3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
        byte[] bitMapData3 = stream3.toByteArray();

        manager.insertar("Refresco", bitMapData3, 1.50, 10);

        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.cerveza3);
        ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
        bitmap4.compress(Bitmap.CompressFormat.PNG, 100, stream4);
        byte[] bitMapData4 = stream4.toByteArray();

        manager.insertar("Cerveza", bitMapData4, 1.20, 10);

        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.agua);
        ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
        bitmap5.compress(Bitmap.CompressFormat.PNG, 100, stream5);
        byte[] bitMapData5 = stream5.toByteArray();

        manager.insertar("Agua", bitMapData5, 1.00, 10);

        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Comanda.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Productes.class));
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Fer_caixa.class));
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Historial_comandes.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_info){
                startActivity(new Intent(MainActivity.this, Informacio.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
