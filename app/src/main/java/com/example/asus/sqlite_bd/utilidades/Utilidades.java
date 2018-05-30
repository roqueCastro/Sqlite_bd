package com.example.asus.sqlite_bd.utilidades;

/**
 * Created by ASUS on 22/05/2018.
 */

public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String C_ID = "id";
    public static final String C_NOMBRE = "nombre";
    public static final String C_TELEFONO = "telefono";

    public static final String CREAR_TABLA_USUARIO= "CREATE TABLE "+ TABLA_USUARIO +" ("+ C_ID +"" +
            " Integer, "+ C_NOMBRE +" TEXT, "+ C_TELEFONO +" TEXT)";

    public static final String VERIFICAR_CREACION= "DROP TABLE IF EXISTS";

    //Constantes campos tabla mascota
    public static final String TABLA_MASCOTA = "mascota";
    public static final String C_ID_M = "id_mascota";
    public static final String C_NOMBRE_M = "nombre_mascota";
    public static final String C_RAZA_M = "raza_mascota";
    public static final String C_ID_DUENIO = "id_duenio";

    public static final String CREAR_TABLA_MASCOTA= "CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+C_ID_M+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +C_NOMBRE_M+" TEXT, "+C_RAZA_M+" TEXT, "+C_ID_DUENIO+" INTEGER)";
}
