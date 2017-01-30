package com.example.alvaromartinezarroyo.comandescambrer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;

public class Comanda extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);

        //Botón Atrás
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
