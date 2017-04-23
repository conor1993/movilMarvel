package com.servman.zero.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class verComicsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBuscar;
    EditText txtnombrePer;
    JSONObject jsonObject = new JSONObject();
    JSONObject info;
    String nombrePersonaje;
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_comics);

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
            JSONArray comics= new JSONArray();
            try{

                JSONObject personaje = success.getJSONObject("data");
                //si el arreglo trae mas de cero entonses encontro al personaje
                if(personaje.getString("count") != "0"){

                    JSONArray datos = personaje.getJSONArray("results");

                    for (int i=0;i<datos.length();i++){
                        comics =  datos.getJSONObject(i).getJSONObject("comics").getJSONArray("items");
                    }
                    String[] nombreComics = new String[comics.length()];
                    for (int j=0;j<comics.length();j++){
                        nombreComics[j]  = comics.getJSONObject(j).getString("name");
                    }


                    Intent i = new Intent(verComicsActivity.this, mostrarComics.class);
                    i.putExtra("listacomics",nombreComics);
                    startActivity(i);

                }else{

                    Toast.makeText(verComicsActivity.this, "No se encontro el Personaje", Toast.LENGTH_SHORT).show();
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
