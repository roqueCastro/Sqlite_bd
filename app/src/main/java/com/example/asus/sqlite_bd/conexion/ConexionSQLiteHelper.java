package com.example.asus.sqlite_bd.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asus.sqlite_bd.utilidades.Utilidades;


/**
 * Created by ASUS on 22/05/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Utilidades.VERIFICAR_CREACION + " " + Utilidades.TABLA_USUARIO);
        db.execSQL(Utilidades.VERIFICAR_CREACION + " " + Utilidades.TABLA_MASCOTA);
        onCreate(db);
    }
}
