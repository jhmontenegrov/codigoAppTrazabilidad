package com.example.eduar.appqr;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registro extends AppCompatActivity {

    Button registrar;
    Button salir;


    EditText id_U;
    EditText nombres;
    EditText apellidos;
    EditText correo;
    EditText usuario;
    EditText clave;
    String miurl="";


    TextView view;
    TextView nomact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrar = (Button)findViewById(R.id.button);
        salir = (Button)findViewById(R.id.button2);

        id_U = (EditText)findViewById(R.id.ID_PROFE);
        nombres = (EditText)findViewById(R.id.NOMBRES);
        apellidos = (EditText)findViewById(R.id.APELLIDOS);
        correo = (EditText)findViewById(R.id.CORREO);
        usuario = (EditText)findViewById(R.id.USUARIO);
        clave = (EditText)findViewById(R.id.CLAVE);

        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reg();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                salir();
            }
        });



    }
    public void reg(){
        if(id_U.getText().toString().isEmpty() || nombres.getText().toString().isEmpty() || apellidos.getText().toString().isEmpty()|| correo.getText().toString().isEmpty()|| usuario.getText().toString().isEmpty()|| clave.getText().toString().isEmpty() ){//Si estan vacios
            Toast.makeText(getApplicationContext(), "Datos incorrectos",
                    Toast.LENGTH_SHORT).show();
        }
        else{

//modificar la URL
            new CargarDatos().execute("http://10.0.2.2/WebService/Agregar_Usuario.php?id_user="+id_U.getText().toString()+"&nombre="+nombres.getText().toString()+"&apellido="+apellidos.getText().toString()+"&email="+correo.getText().toString()+"&usuario="+usuario.getText().toString()+"&clave="+clave.getText().toString());



        }
    }
    public void salir(){
        finish();

    }

    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                //String cadena=urls[0];
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            try {

                //Toast.makeText(getApplicationContext(), result,
                //        Toast.LENGTH_LONG).show();

                //Validación de unicidad del usuario a registrar
                if (result.contains("false")) {
                    Toast.makeText(getApplicationContext(), "Este nombre de usuario ya existe",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Usuario registrado ",
                            Toast.LENGTH_LONG).show();
                    Intent siguiente = new Intent(Registro.this, Login.class);
                    startActivity(siguiente);

                }
            }


            catch(Exception e){// (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch(IOException e){

            return e.getMessage();
        }finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent passIntent = new Intent();
            passIntent.setClass(Registro.this,Login.class);
            startActivity(passIntent);

            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}

