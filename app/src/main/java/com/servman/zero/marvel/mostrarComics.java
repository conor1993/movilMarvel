package com.servman.zero.marvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class mostrarComics extends AppCompatActivity {

    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_comics);

        list = (ListView)findViewById(R.id.lisaComics);
        String[] nombreComics = (String[]) getIntent().getSerializableExtra("listacomics");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombreComics);
        list.setAdapter(adaptador);
    }




}
