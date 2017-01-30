package com.example.alvaromartinezarroyo.comandescambrer;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MacBookProAlvaro on 28/12/15.
 */
public class ItemListAdapter extends CursorAdapter {

    private DBManager manager;

    private TextView texto;
    private ImageView foto;
    private LayoutInflater inflater;

    private boolean first = true;

    public ItemListAdapter(Context context, Cursor c) {
        super(context, c, true);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.itemlistadapter, viewGroup, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        if(first)cursor.moveToFirst();

        TextView texto = (TextView) view.findViewById(R.id.textolistview);
        ImageView foto = (ImageView) view.findViewById(R.id.fotolistview);

        //texto.setText(cursor.getString(cursor.getColumnIndex("CN_NOM")));
        int index2 = cursor.getColumnIndex(manager.CN_NOM);
        texto.setText(cursor.getString(index2));
        //texto.setText(cursor.getColumnIndex(manager.CN_NOM));
        int index = cursor.getColumnIndex(manager.CN_FOTO);
        Bitmap n = BitmapFactory.decodeByteArray(cursor.getBlob(index), 0, cursor.getBlob(index).length);
        foto.setImageBitmap(n);
        first = false;
        cursor.moveToNext();
    }
}
