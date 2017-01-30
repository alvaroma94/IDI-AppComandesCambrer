package com.example.alvaromartinezarroyo.comandescambrer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MacBookProAlvaro on 5/12/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NOM= "productesDefinitivo.sqlite";
    private static final int DB_VERSIO_ESQUEMA= 1;

    public DBHelper(Context context) {
        super(context, DB_NOM, null, DB_VERSIO_ESQUEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
