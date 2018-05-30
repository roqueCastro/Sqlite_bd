package com.example.asus.sqlite_bd.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;
import com.example.asus.sqlite_bd.entidades.Usuario;
import com.example.asus.sqlite_bd.utilidades.Utilidades;

import java.util.ArrayList;

public class RegisterMascoActivity extends AppCompatActivity {

    EditText _nomb_M, _tipo_M;
    Spinner _persona_M;
    Button guardar_M;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> usuarios;

    ConexionSQLiteHelper conex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_masco);

        _nomb_M=findViewById(R.id.input_nombreM);
        _tipo_M = findViewById(R.id.inp_tip_M);
        _persona_M = findViewById(R.id.spinnerUserM);
        guardar_M = findViewById(R.id.btn_Guardar_M);

        conex = new ConexionSQLiteHelper(getApplicationContext(), "bd_prueba", null, 1);
        consultarListaPersonas();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaPersonas);
        _persona_M.setAdapter(adapter);
        _persona_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        guardar_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarMascotas();
            }
        });


    }

    private void registrarMascotas() {

        int idCombo = (int) _persona_M.getSelectedItemId();

        if(idCombo != 0){
            try {
                SQLiteDatabase database = conex.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Utilidades.C_NOMBRE_M,_nomb_M.getText().toString());
                values.put(Utilidades.C_RAZA_M,_tipo_M.getText().toString());


                    Log.i("Tamaño: ",usuarios.size()+"");
                    Log.i("idCombo: ",idCombo+"");
                    Log.i("idCombo -1: ",(idCombo-1)+"");
                    int idDuenio = usuarios.get(idCombo-1).getId();
                    Log.i("id Dueño: ", idDuenio+"");

                    values.put(Utilidades.C_ID_DUENIO,idDuenio);
                    Long idResultante = database.insert(Utilidades.TABLA_MASCOTA,Utilidades.C_ID_M,values);

                    Toast.makeText(getApplicationContext(), "id Registro: "+ idResultante, Toast.LENGTH_SHORT).show();
                    database.close();
                    _nomb_M.setText("");
                    _tipo_M.setText("");

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error no registro.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Debes seleccionar un dueño  ", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultarListaPersonas() {
        SQLiteDatabase database = conex.getReadableDatabase();
        Usuario usuario = null;
        usuarios = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO, null);
        while(cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            usuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<>();
        listaPersonas.add("Seleccione dueño");

        for(int i=0; i<usuarios.size(); i++){
            listaPersonas.add(usuarios.get(i).getNombre());
        }
    }
}
