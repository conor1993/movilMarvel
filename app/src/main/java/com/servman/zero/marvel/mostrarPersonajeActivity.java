package com.servman.zero.marvel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class mostrarPersonajeActivity extends AppCompatActivity {

    String Nombre,urlimagen,descripcion ,imageHttpAddress;
    TextView lblnombre,lbldescripcion;
    ImageView imageView;
    Bitmap loadedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_personaje);

        lblnombre = (TextView)findViewById(R.id.lblnombre);
        lbldescripcion = (TextView)findViewById(R.id.lbldescripcion);
        imageView = (ImageView) findViewById(R.id.ImgFoto);

        Nombre = getIntent().getExtras().getString("nombre");
        descripcion =getIntent().getExtras().getString("des");
        urlimagen = getIntent().getExtras().getString("urlfoto");

         lblnombre.setText(Nombre);
         lbldescripcion.setText(descripcion);


        cargarImagen(urlimagen);

    }

    private void cargarImagen(String urlimagen) {
        Glide.with(this)
                .load(urlimagen)
                .crossFade()
                .into(imageView);
        System.out.print(urlimagen);

    }


}
