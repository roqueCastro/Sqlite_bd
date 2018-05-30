package com.example.asus.sqlite_bd.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;
import com.example.asus.sqlite_bd.utilidades.Utilidades;

public class RegisterUserActivity extends AppCompatActivity {

    EditText _id,_nombre,_telefono;
    Button _guardarUser;
    ConexionSQLiteHelper conex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        conex = new ConexionSQLiteHelper(this, "bd_prueba", null, 1);

        _id = findViewById(R.id.inp_id);
        _nombre = findViewById(R.id.input_nombre);
        _telefono = findViewById(R.id.inp_telefono);

        _guardarUser=findViewById(R.id.btn_Guardar);

        _guardarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registarUsuarios();
            }
        });
    }

    private void registarUsuariosSql() {
        SQLiteDatabase database = conex.getWritableDatabase();
        String insert = "INSERT INTO "+Utilidades.TABLA_USUARIO+" ("+Utilidades.C_ID+","+Utilidades.C_NOMBRE+ ","+
                Utilidades.C_TELEFONO+ ") VALUES ("+
                _id.getText().toString()+",'"+
                _nombre.getText().toString()+"','"+
                _telefono.getText().toString()+ "')";
        database.execSQL(insert);
        database.close();
    }

    private void registarUsuarios() {
        try{
            SQLiteDatabase database = conex.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utilidades.C_ID, _id.getText().toString());
            values.put(Utilidades.C_NOMBRE, _nombre.getText().toString());
            values.put(Utilidades.C_TELEFONO, _telefono.getText().toString());

            Long idResultado = database.insert(Utilidades.TABLA_USUARIO,Utilidades.C_ID,values);
            Toast.makeText(getApplicationContext(), "Id registro: "+ idResultado,Toast.LENGTH_SHORT).show();
            limpiar();

            database.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error no registro",Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar() {
        _id.setText("");
        _nombre.setText("");
        _telefono.setText("");
    }
}
