package com.example.iniciosesion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {

    private EditText consulta;
    private TextView tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar2);

        consulta=(EditText)findViewById(R.id.txtconsulta);
        tabla=(TextView)findViewById(R.id.texto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if (id==R.id.principal){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

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

        }
        else
        if (id==R.id.salir){
            Intent intent = new Intent(this, Sesion.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnconsulta(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase baseDatabase = admin.getWritableDatabase();

        String con= consulta.getText().toString();

        if(!con.isEmpty()){

            Cursor c = baseDatabase.rawQuery
                    ("Select u.nombre, u.apellido, c.edad, c.prenatal from usuario as u INNER JOIN china as c on u.codigo=c.codigo where u.dni='"+con+"'"
                            , null);

            if (c.moveToFirst()){
                String nom= c.getString(0);
                String ape= c.getString(1);
                String edad= c.getString(2);
                String prenatal= c.getString(3);
                tabla.setText("Nombre: "+nom+"\n"+ "Apellido: "+ape+"\n"+"Edad: "+edad+" años"+"\n"+"Etapa Prenatal: "+prenatal+" Semanas");

            } else {
                Toast.makeText(this, "No se encuentra Registro", Toast.LENGTH_LONG).show();
                tabla.setText("");
            }

        } else {
            Toast.makeText(this, "Ingrese DNI", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(View view){
        consulta.setText("");
        tabla.setText("");
    }
  }
