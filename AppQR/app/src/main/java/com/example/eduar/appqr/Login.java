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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {
    private EditText usu, clave;
    private Button btn, btn2,btn3;
    private String sn1, sn2, sn3;

    String mid;
    String mnom;
    String mape ;
    String mmail;
    String musu ;


        @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usu = (EditText) findViewById(R.id.USUARIO);
        clave = (EditText) findViewById(R.id.CLAVE);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(usu.getText().toString().isEmpty() || clave.getText().toString().isEmpty() ){//Si estan vacios
                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrecta",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    new ConsultarDatos().execute("http://10.0.2.2/WebService/Consultar_Usuario.php?usuario="+usu.getText().toString()+"&clave="+clave.getText().toString());

                }
            }
        });

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                salir();
            }
        });

        btn3 = (Button) findViewById(R.id.buttonR);
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {Registrar();
            }
        });
    }


    public void enviarmensaje(){

        String musu= usu.getText().toString();
        Intent intent = new Intent(getApplicationContext(),Pantalla_Segundaria.class);
        intent.putExtra("id_user", mid);
        intent.putExtra("nombre", mnom);
        intent.putExtra("apellido", mape);
        intent.putExtra("email", mmail);
        intent.putExtra("usuario", musu);
        startActivity(intent);


    }
    private class ConsultarDatos extends AsyncTask<String, Void, String> {
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
            JSONArray ja = null;
            try {
                ja = new JSONArray(result);

                usu.setText(ja.getString(4));
                clave.setText(ja.getString(5));


                //Validación de contraseña
                if(usu.getText().toString().equals(ja.getString(4)) && clave.getText().toString().equals(ja.getString(5)) ){
                    enviarmensaje();

                }
                else{
                    Toast.makeText(getApplicationContext(), "No se encuentra en la base de datos",
                            Toast.LENGTH_SHORT).show();

                }


            } catch (JSONException e) {
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


    public void Registrar(){
        Intent siguiente2 = new Intent(Login.this,Registro.class);
        startActivity(siguiente2);

    }
    public void salir(){

        //System.exit(0);
        finish();
        //int p=android.os.Process.myPid();
        //android.os.Process.killProcess(p);
    }



}
