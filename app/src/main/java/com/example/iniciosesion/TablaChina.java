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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TablaChina extends AppCompatActivity {

    private Spinner sexobb;
    private EditText codigochi, codiusu, edad, prenatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_china);

        codigochi = (EditText)findViewById(R.id.txtcodi);
        codiusu = (EditText)findViewById(R.id.txtcod_usu);
        sexobb=(Spinner)findViewById(R.id.spinner);
        edad=(EditText)findViewById(R.id.txtedad);
        prenatal=(EditText)findViewById(R.id.txtprenatal);

        String val[]={"Seleccionar", "Femenino", "Masculino"};
        ArrayAdapter<String> opcion= new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, val);
        sexobb.setAdapter(opcion);

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
            Intent intent = new Intent(this, usuario.class);
            startActivity(intent);
        }
        else
        if (id==R.id.china){

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

    public static int Posicion(Spinner valores, String inicializar) {

        int posicion = 0;
        int i;

        for (i = 0; i < valores.getCount(); i++) {
            if (valores.getItemAtPosition(i).toString().equalsIgnoreCase(inicializar)) {
                posicion = i;
            }
        }

        return posicion;
    }

    public void btnagre(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase baseDatabase = admin.getWritableDatabase();

        String codigochina = codigochi.getText().toString();
        String sexo=sexobb.getSelectedItem().toString();
        String codgoiusu = codiusu.getText().toString();
        String edadusu = edad.getText().toString();
        String prenatalusu = prenatal.getText().toString();

        String inicializar="Seleccionar";


        if(!codigochina.isEmpty() && !sexo.isEmpty() && !codgoiusu.isEmpty() && !edadusu.isEmpty() && !prenatalusu.isEmpty()){
            ContentValues filas = new ContentValues();

            filas.put("codigo_chi", codigochina);
            filas.put("sexobebe", sexo);
            filas.put("codigo", codgoiusu);
            filas.put("edad", edadusu);
            filas.put("prenatal", prenatalusu);
            baseDatabase.insert("china", null, filas);
            baseDatabase.close();
            codigochi.setText("");
            sexobb.setSelection(Posicion(sexobb, inicializar));
            codiusu.setText("");
            edad.setText("");
            prenatal.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Completar los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnbus(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase baseDatabase = admin.getWritableDatabase();

        String codigochina = codigochi.getText().toString();

        if(!codigochina.isEmpty()){
            Cursor fila = baseDatabase.rawQuery
         ("select sexobebe, codigo, edad, prenatal from china where codigo_chi ='"+codigochina+"'", null);

            if(fila.moveToFirst()){
                String sexo= fila.getString(0);
                sexobb.setSelection(Posicion(sexobb, sexo));
                codiusu.setText(fila.getString(1));
                edad.setText(fila.getString(2));
                prenatal.setText(fila.getString(3));
                baseDatabase.close();

                Toast.makeText(this,"Registro Encontrado", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this,"Registro No existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Introducir código ", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnelim(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase baseDatabase = admin.getWritableDatabase();

        String codigochina = codigochi.getText().toString();
        String inicializar="Seleccionar";

        if(!codigochina.isEmpty()){

            int val = baseDatabase.delete("china", "codigo='"+codigochina+"'", null);
            baseDatabase.close();

            codigochi.setText("");
            sexobb.setSelection(Posicion(sexobb, inicializar));
            codiusu.setText("");
            edad.setText("");
            prenatal.setText("");

            if(val == 1){
                Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registro No existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Introducir codigo", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnmodi(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase baseDatabase = admin.getWritableDatabase();

        String codigochina = codigochi.getText().toString();
        String sexo=sexobb.getSelectedItem().toString();
        String codgoiusu = codiusu.getText().toString();
        String edadusu = edad.getText().toString();
        String prenatalusu = prenatal.getText().toString();

        if(!codigochina.isEmpty() && !sexo.isEmpty() && !codgoiusu.isEmpty() && !edadusu.isEmpty() && !prenatalusu.isEmpty()){

            ContentValues filas = new ContentValues();
            filas.put("codigo_chi", codigochina);
            filas.put("sexobebe", sexo);
            filas.put("codigo", codgoiusu);
            filas.put("edad", edadusu);
            filas.put("prenatal", prenatalusu);

            int val = baseDatabase.update("china", filas, "codigo='"+codigochina+"'", null);
            baseDatabase.close();

            if(val == 1){
                Toast.makeText(this, "Registro Modificado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registro no Existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Completar los campos", Toast.LENGTH_SHORT).show();
        }
    }

}