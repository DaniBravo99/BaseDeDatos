package com.example.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sesion extends AppCompatActivity {

    private EditText mail, contra;
    private SharedPreferences preferences, preferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        mail=(EditText)findViewById(R.id.txtmail);
        contra=(EditText)findViewById(R.id.txtcontra);

        preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);

        mail.setText(preferences.getString("mail", ""));
        contra.setText(preferences.getString("contra", ""));

    }

    public void btningresar(View view)
    {

        preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        if(mail.getText().toString().isEmpty() && contra.getText().toString().isEmpty()){

            Toast.makeText(this, "Ingrese Email y Contraseña", Toast.LENGTH_SHORT).show();

        } else
        if(mail.getText().toString().isEmpty()){

            Toast.makeText(this, "Ingresar Email", Toast.LENGTH_SHORT).show();

        } else
        if (contra.getText().toString().isEmpty()){

            Toast.makeText(this, "Ingresar Contraseña", Toast.LENGTH_SHORT).show();

        } else {

            SharedPreferences.Editor editores = preferencias.edit();
            editores.putString("mail", mail.getText().toString());
            editores.putString("contra", contra.getText().toString());
            editores.commit();

            editores.remove("mail").commit();
            editores.remove("contra").commit();

            String mas="@gmail.com";

            String correo=mail.getText().toString();
            String clave=contra.getText().toString()+mas;

            if (correo.equals(clave)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void btneliminar(View view){

        mail.setText("");
        contra.setText("");
    }

}