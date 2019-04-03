package com.example.eduar.appqr;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import org.w3c.dom.Text;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Pantalla_Segundaria extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView ScannerView;
    TextView tw1, codigo;
    TextView nomact,text2;
    String id="";
    String nombres="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //ocultar la barra de accion azul de android studio
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__segundaria);


        tw1 = (TextView) findViewById(R.id.tv1);
        text2=(TextView) findViewById(R.id.nusu);
        nomact=(TextView) findViewById(R.id.usu);

        //Mostrar encabezado
        Intent intent2 = getIntent();
        String nu = intent2.getStringExtra("usuario");

        nomact.setText("Inicio");
        text2.setText(nu);//Escribir el nombre del usuario en el encabezado

    }


    public void Qr(View v) {

        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();

    }



    @Override
    public void handleResult(Result result) {

        //Log.v("HandleResult",result.getText());
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Resultado del Scan");
        //builder.setMessage(result.getText());
        //AlertDialog alertDialog = builder.create();
        //alertDialog.show();
        String dato=result.getText();
        setContentView(R.layout.datos_qr);
        ScannerView.stopCamera();
        codigo = (TextView) findViewById(R.id.textView2);
        codigo.setText(dato);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent passIntent = new Intent();
            passIntent.setClass(Pantalla_Segundaria.this,Pantalla_Inicial.class);
            startActivity(passIntent);

            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}