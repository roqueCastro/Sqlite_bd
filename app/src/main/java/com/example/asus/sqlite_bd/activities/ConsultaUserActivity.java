package com.example.asus.sqlite_bd.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;
import com.example.asus.sqlite_bd.utilidades.Utilidades;

public class ConsultaUserActivity extends AppCompatActivity {

    Button _btnBuscar,_btnActu,_btnEli;
    EditText _documento,_nombre,_tele;

    ConexionSQLiteHelper conex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_user);

        conex = new ConexionSQLiteHelper(this, "bd_prueba", null, 1);

        _documento = findViewById(R.id.c_documento);
        _nombre = findViewById(R.id.c_nombre);
        _tele = findViewById(R.id.c_telefono);

        _btnBuscar = findViewById(R.id.c_btn_buscar);
        _btnActu = findViewById(R.id.c_btn_actualizar);
        _btnEli = findViewById(R.id.c_btn_eliminar);

        _btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarDatos();
            }
        });

        _btnActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuario();
            }
        });

        _btnEli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUsuario();
            }
        });
    }

    private void eliminarUsuario() {
        SQLiteDatabase database = conex.getWritableDatabase();

        String[] parametros = {_documento.getText().toString()};

        database.delete(Utilidades.TABLA_USUARIO,Utilidades.C_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se elimino el usuario",Toast.LENGTH_SHORT).show();
        limpiar();
        database.close();
    }

    private void actualizarUsuario() {
        SQLiteDatabase database = conex.getWritableDatabase();

        String[] parametros = {_documento.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.C_NOMBRE,_nombre.getText().toString());
        values.put(Utilidades.C_TELEFONO,_tele.getText().toString());

        database.update(Utilidades.TABLA_USUARIO,values,Utilidades.C_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizo",Toast.LENGTH_SHORT).show();
        limpiar();
        database.close();
    }

    private void ConsultarDatos() {
        SQLiteDatabase database = conex.getWritableDatabase();

        String[] parametros = {_documento.getText().toString()};
        String[] campos = {Utilidades.C_NOMBRE,Utilidades.C_TELEFONO};

        try {
            Cursor cursor = database.query(Utilidades.TABLA_USUARIO, campos,Utilidades.C_ID+"=?"
                    ,parametros,null,null,null);
            cursor.moveToFirst();
            _nombre.setText(cursor.getString(0));
            _tele.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            _documento.setError("Documento no existe");
            limpiar();
        }


    }

    private void limpiar() {
        _documento.setText("");
        _nombre.setText("");
        _tele.setText("");
    }
}
