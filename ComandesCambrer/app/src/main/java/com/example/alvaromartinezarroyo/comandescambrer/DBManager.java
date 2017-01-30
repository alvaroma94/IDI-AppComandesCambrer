package com.example.alvaromartinezarroyo.comandescambrer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by MacBookProAlvaro on 5/12/15.
 */
public class DBManager {

    private boolean primer = true;

    //Nombre de la tabla
    public static final String NOM_TAULA="productes";
    //Campos de la tabla --> CN : ColumName
    public static final String CN_ID="_id";
    public static final String CN_NOM="nom";
    public static final String CN_FOTO="foto";
    public static final String CN_PREU="preu";
    public static final String CN_UNITATS="unitats";

    public static final String CREATE_TABLE = "create table " + NOM_TAULA + " ("
            + CN_ID + " integer,"
            + CN_NOM + " text primary key,"
            + CN_FOTO + " blob,"
            + CN_PREU + " double not null,"
            + CN_UNITATS + " integer);";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    private ContentValues crearContentValues (String nom, byte[] imatge, double preu, Integer unitats){
        ContentValues valors = new ContentValues();
        valors.put(CN_NOM, nom);
        valors.put(CN_FOTO, imatge);
        valors.put(CN_PREU, preu);
        valors.put(CN_UNITATS, unitats);

        return valors;
    }

    public void insertar (String nom, byte[] imatge, double preu, Integer unitats){
        //if (primer) {
        //    db.insert(NOM_TAULA, null, crearContentValues(nom, imatge, preu, unitats));
        //    primer = false;
        //}
        //else {
            //Cursor c = db.rawQuery("SELECT nom FROM productes WHERE nom=?", new String[]{nom});
            //Cursor c = db.rawQuery("SELECT * FROM " + NOM_TAULA + " WHERE " + CN_NOM + "=?", new String[]{nom});
            //c.moveToFirst();
            //if (c.getInt(0) == 0){
            //if(c == null) {
                db.insert(NOM_TAULA, null, crearContentValues(nom, imatge, preu, unitats));
            //}
        //}
    }

    //Cursor c = db.rawQuery("SELECT COUNT (descripcio) FROM Receptes WHERE nom=? ", new String[]{nom});

    public void eliminar (String nom){
        db.delete(NOM_TAULA, CN_NOM + "=?", new String[]{nom});
    }

    public void modificarUnitats (String nom, byte[] imatge, double preu, Integer nouUnitats){
        db.update(NOM_TAULA, crearContentValues(nom, imatge, preu, nouUnitats), CN_NOM + "=?", new String[]{nom});
    }

    public Cursor cargarCursorProductes(){
        Cursor c;
        String[] columnes = new String[] {CN_ID, CN_NOM, CN_FOTO, CN_PREU, CN_UNITATS};
        c = db.query(NOM_TAULA, columnes, null, null, null, null, null);


        return c;
    }

    public Cursor buscarProducte(String nom){
        String[] columnes = new String[] {CN_ID, CN_NOM, CN_FOTO, CN_PREU, CN_UNITATS};
        return db.query(NOM_TAULA, columnes, CN_NOM + "=?", new String[] {nom}, null, null, null);
    }

}
