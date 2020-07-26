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

        BaseDatabase.execSQL("Create table usuario(codigo text, nombre text, apellido text, primary key (codigo))");
BaseDatabase.execSQL("Create table china(codigo_chi text, sexobebe text,codigo text, edad int, prenatal int, primary key(codigo_chi), foreign key (codigo) references usuario(codigo))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
