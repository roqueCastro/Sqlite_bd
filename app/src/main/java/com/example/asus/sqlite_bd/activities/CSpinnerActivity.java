package com.example.asus.sqlite_bd.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;
import com.example.asus.sqlite_bd.entidades.Usuario;
import com.example.asus.sqlite_bd.utilidades.Utilidades;

import java.util.ArrayList;

public class CSpinnerActivity extends AppCompatActivity {
    Spinner spinner;
    TextView _docu,_nom,_tel;

    ArrayList<String> listPersonas;
    ArrayList<Usuario> usuarios;

    ConexionSQLiteHelper conex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cspinner);

        conex = new ConexionSQLiteHelper(this, "bd_prueba", null, 1);

        spinner=findViewById(R.id.spinnerC);
        _docu=findViewById(R.id.inp_documento_s);
        _nom=findViewById(R.id.inp_nombre_s);
        _tel=findViewById(R.id.inp_telefono_s);

        consultarListPersonas();

        ArrayAdapter<CharSequence> adapter =  new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listPersonas);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    _docu.setText(usuarios.get(position-1).getId().toString());
                    _nom.setText(usuarios.get(position-1).getNombre().toString());
                    _tel.setText(usuarios.get(position-1).getTelefono().toString());
                }else{
                    _docu.setText("");
                    _nom.setText("");
                    _tel.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListPersonas() {
        SQLiteDatabase database = conex.getReadableDatabase();

        Usuario usuario = null;
        usuarios= new ArrayList<Usuario>();

        Cursor cursor= database.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);
        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            Log.i("id", usuario.getId().toString());
            Log.i("nombre", usuario.getNombre().toString());
            Log.i("telefono", usuario.getTelefono().toString());

            usuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listPersonas = new ArrayList<String>();
        listPersonas.add("Seleccione");
        for(int i=0; i<usuarios.size(); i++){
            listPersonas.add(/*usuarios.get(i).getId()+" - "+*/ usuarios.get(i).getNombre());
        }
    }
}
