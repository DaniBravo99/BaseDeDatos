package com.example.iniciosesion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();

            if (id==R.id.principal){

            }
        else if (id==R.id.usuario){

          Intent intent = new Intent(this, usuario.class);
            startActivity(intent);


        } else
        if (id==R.id.china){
            Intent intent = new Intent(this, TablaChina.class);
            startActivity(intent);
        }
        else
        if (id==R.id.consulta) {
             Intent intent = new Intent(this, Consultar.class);
             startActivity(intent);
        }
        else
           if (id==R.id.salir){
               Intent intent = new Intent(this, Sesion.class);
               startActivity(intent);
           }

        return super.onOptionsItemSelected(item);
    }
}





