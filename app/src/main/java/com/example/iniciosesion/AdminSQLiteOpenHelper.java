package com.example.iniciosesion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDatabase) {

        BaseDatabase.execSQL("Create table usuario(codigo int, dni text unique, nombre text, apellido text, primary key(codigo))");
        BaseDatabase.execSQL("Create table china(codigo_chi int, sexobebe text,codigo int, edad int, prenatal int,primary key(codigo_chi) ,foreign key (codigo) references usuario(codigo))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
