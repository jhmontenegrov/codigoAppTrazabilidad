package com.example.eduar.appqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla_Inicial extends AppCompatActivity {


    ImageView imv1;
    TextView tw1, tw2, tw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__inicial);

        //Toast.makeText(getApplicationContext(),"Bienvenido", Toast.LENGTH_LONG).show();

        imv1 = (ImageView) findViewById(R.id.im1);
        tw1 = (TextView) findViewById(R.id.tw1);
        tw2 = (TextView) findViewById(R.id.tw2);
        tw3 = (TextView) findViewById(R.id.tw3);



        Thread logo = new Thread() {

            public void run() {
                try {
                    int tiempo = 0;
                    while (tiempo < 3000) {
                        sleep(100);
                        tiempo = tiempo + 100;
                    }
                    Intent i = new Intent(Pantalla_Inicial.this, Pantalla_Segundaria.class);
                    startActivity(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };

        logo.start();
    }
}