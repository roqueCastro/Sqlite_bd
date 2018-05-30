package com.example.asus.sqlite_bd.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.entidades.Usuario;

public class DetalleListVActivity extends AppCompatActivity {

    TextView _id,_nombre,_telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_list_v);
        _id = findViewById(R.id.id_);
        _nombre = findViewById(R.id.nombre_);
        _telefono = findViewById(R.id.telefono_);

        Bundle objetoEnviado=getIntent().getExtras();
        Usuario usuario = null;

        if(objetoEnviado!=null){
            usuario= (Usuario) objetoEnviado.getSerializable("usuario");

            _id.setText(usuario.getId().toString());
            _nombre.setText(usuario.getNombre().toString());
            _telefono.setText(usuario.getTelefono().toString());
        }
    }
}
