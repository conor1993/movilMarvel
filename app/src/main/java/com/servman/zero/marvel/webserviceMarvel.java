package com.servman.zero.marvel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zero on 18/04/2017.
 */
public class webserviceMarvel {

    protected JSONObject obtenerdatos(String nombre) {


        JSONObject jsonObject = new JSONObject();

        //se reemplazan los espacios en el nombre con %20 que es como se codiica el espacion en xml
        nombre=nombre.replaceAll(" ", "%20");

        String cadena = "\n" +
                "http://gateway.marvel.com/v1/public/characters?ts=1&apikey=c498aca0e2103b4b54fe07acaa68519c&hash=2b938194cfa1184438b39b1cff0577ed&limit=1&nameStartsWith="+nombre;


        StringBuilder result = new StringBuilder();

        URL url = null; // Url de donde queremos obtener información
        try {
            url = new URL(cadena);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
            connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                    " (Linux; Android 1.5; es-ES) Ejemplo HTTP");


            int respuesta = connection.getResponseCode();


            if (respuesta == HttpURLConnection.HTTP_OK){


                InputStream in = new BufferedInputStream(connection.getInputStream());  // cadena de entrada

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader



                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);        // Paso toda la entrada al StringBuilder
                }
                jsonObject = new JSONObject(result.toString());
                //json_array = new JSONArray(result.toString());



            }


        } catch (MalformedURLException e) {
            System.out.print("error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
