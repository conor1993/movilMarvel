package com.servman.zero.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button btnComics,btnPersonaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicar los bonotes
        btnComics = (Button)findViewById(R.id.btnComics);
        btnPersonaje= (Button)findViewById(R.id.btnPersonajes);
        //agregar escuchador
        btnComics.setOnClickListener(this);
        btnPersonaje.setOnClickListener(this);

    }



    //en este lugar ingresan los eventos
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnComics:
                verComics();
                //Toast.makeText(MainActivity.this, "comics", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnPersonajes:
                verPersonaje();
                //Toast.makeText(MainActivity.this, "personajes", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }

    }

    private void verPersonaje() {
        //abrimos la actividad(ventana) verPersonajes
        Intent i = new Intent(MainActivity.this, verPersonajeActivity.class);
        startActivity(i);
    }

    private void verComics() {
        //abrimos la actividad(ventana) verComics
        Intent i = new Intent(MainActivity.this, verComicsActivity.class);
        startActivity(i);
    }
}
