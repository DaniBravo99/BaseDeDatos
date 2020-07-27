package com.example.iniciosesion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class usuario extends AppCompatActivity {

    private EditText codigo, nombre, apellido, dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        codigo= (EditText)findViewById(R.id.txtcodigo);
        nombre= (EditText)findViewById(R.id.txtnombre);
        apellido= (EditText)findViewById(R.id.txtapellido);
        dni=(EditText)findViewById(R.id.txtdni);
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
        else
            if (id==R.id.usuario){

        }
            else
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

    public void agregar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null,
        1);
        SQLiteDatabase BaseDatabase=admin.getWritableDatabase();

      String usu=codigo.getText().toString();
      String dniusu= dni.getText().toString();
      String nom=nombre.getText().toString();
      String ape=apellido.getText().toString();

       if(!usu.isEmpty() && !dniusu.isEmpty() && !nom.isEmpty() && !ape.isEmpty()){

           ContentValues filas= new ContentValues();
           filas.put("codigo", usu);
           filas.put("dni", dniusu);
           filas.put("nombre", nom);
           filas.put("apellido", ape);

           BaseDatabase.insert("usuario", null, filas);
           BaseDatabase.close();
           codigo.setText("");
           dni.setText("");
           nombre.setText("");
           apellido.setText("");
           codigo.setFocusable(true);

           Toast.makeText(this,"Registrado Correctamente", Toast.LENGTH_LONG).show();

       } else {

           Toast.makeText(this, "Completar los campos", Toast.LENGTH_LONG).show();

       }

    }

    public void buscar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null,
                1);
        SQLiteDatabase BaseDatabase=admin.getWritableDatabase();

        String codigousu=codigo.getText().toString();

        if (!codigousu.isEmpty()){
            Cursor bus= BaseDatabase.rawQuery("Select dni,nombre, apellido from usuario where codigo="+codigousu, null);

            if(bus.moveToFirst()){
                dni.setText(bus.getString(0));
                nombre.setText(bus.getString(1));
                apellido.setText(bus.getString(2));

                Toast.makeText(this, "Usuario Encontrado", Toast.LENGTH_LONG).show();
                BaseDatabase.close();
            } else {
                Toast.makeText(this, "Usuario No Encontrado", Toast.LENGTH_LONG).show();
                dni.setText("");
                nombre.setText("");
                apellido.setText("");
            }

        } else {
            Toast.makeText(this, "Ingresar Codigo de Usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void modificar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null,
                1);
        SQLiteDatabase BaseDatabase=admin.getWritableDatabase();

        String codigousu=codigo.getText().toString();
        String dniusu= dni.getText().toString();
        String nom=nombre.getText().toString();
        String ape=apellido.getText().toString();

        if (!codigousu.isEmpty() && !dniusu.isEmpty() && !nom.isEmpty() && !ape.isEmpty()){

            ContentValues filas=new ContentValues();
            filas.put("dni", dniusu);
            filas.put("nombre", nom);
            filas.put("apellido", ape);

            int val=BaseDatabase.update("usuario", filas, "codigo="+codigousu, null);

            if (val==1){
                Toast.makeText(this, "Usuario Modificado", Toast.LENGTH_LONG).show();
                dni.setText("");
                nombre.setText("");
                apellido.setText("");
            } else{
                Toast.makeText(this, "Usuario No Modificado", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Ingresar codigo", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null,
                1);
        SQLiteDatabase BaseDatabase=admin.getWritableDatabase();

        String codigousu=codigo.getText().toString();

        if(!codigousu.isEmpty()){
            int val=BaseDatabase.delete("usuario", "codigo="+codigousu, null);
            codigo.setText("");
            dni.setText("");
            nombre.setText("");
            apellido.setText("");

            if (val==1){
                Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(this, "Usuario No existe", Toast.LENGTH_LONG).show();
                codigo.setText("");
                dni.setText("");
                nombre.setText("");
                apellido.setText("");
            }
        } else {
            Toast.makeText(this, "Ingresar codigo de Usuario", Toast.LENGTH_LONG).show();
        }
    }

}
