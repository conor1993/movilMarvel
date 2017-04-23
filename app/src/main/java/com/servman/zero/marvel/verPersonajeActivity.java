package com.servman.zero.marvel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class verPersonajeActivity extends AppCompatActivity  implements View.OnClickListener {

    Button btnBuscar;
    EditText txtnombrePer;
    JSONObject jsonObject = new JSONObject();
    JSONObject info,usrlinfo;
    String nombre,descripcion,urlfoto,nombrePersonaje;


    private UserLoginTask mAuthTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personaje);

        btnBuscar =  (Button)findViewById(R.id.btnBuscarpersonaje);
        btnBuscar.setOnClickListener(this);
        txtnombrePer = (EditText)findViewById(R.id.txtdescripcion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnBuscarpersonaje:
                abrirMostrardatos();
                break;

            default:
                break;

        }
    }

    private void abrirMostrardatos() {

        nombrePersonaje =txtnombrePer.getText().toString();
        mAuthTask = null;
        mAuthTask = new UserLoginTask();
        mAuthTask.execute();

    }


    public class UserLoginTask extends AsyncTask<String,String, JSONObject> {
        private webserviceMarvel web = new webserviceMarvel();


        @Override
        protected JSONObject doInBackground(String... params) {
            jsonObject = web.obtenerdatos(nombrePersonaje);

            return jsonObject;
        }
        @Override
        protected void onPostExecute(final JSONObject success) {

            try{

                JSONObject personaje = success.getJSONObject("data");
               //si el arreglo trae mas de cero entonses encontro al personaje
               if(personaje.getString("count") != "0"){

                JSONArray datos = personaje.getJSONArray("results");
                info= new JSONObject();
                usrlinfo = new JSONObject();

                for (int i=0;i<datos.length();i++){
                    info = datos.getJSONObject(i);

                    nombre=info.getString("name");
                    descripcion =  info.getString("description");
                    usrlinfo =  info.getJSONObject("thumbnail");
                    urlfoto = usrlinfo.getString("path")+"/standard_small."+usrlinfo.getString("extension");
                }

                Intent i = new Intent(verPersonajeActivity.this, mostrarPersonajeActivity.class);
                i.putExtra("nombre",nombre);
                i.putExtra("des",descripcion);
                i.putExtra("urlfoto", urlfoto);
                startActivity(i);

               }else{

                   Toast.makeText(verPersonajeActivity.this, "No se encontro el Personaje", Toast.LENGTH_SHORT).show();
               }


            }catch(Exception e){
                e.printStackTrace();
            }

        }

        @Override
        protected void onCancelled() {

        }
    }
}
