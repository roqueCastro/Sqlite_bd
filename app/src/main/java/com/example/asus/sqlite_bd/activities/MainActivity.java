package com.example.asus.sqlite_bd.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    ConexionSQLiteHelper conex;
    Button _registroUser,_consultaUser, _spinnerC,_cListView, _registroM, _consultaMList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conex = new ConexionSQLiteHelper(this, "bd_prueba", null, 1);
        _registroUser=findViewById(R.id.registrarUsuario);
        _consultaUser=findViewById(R.id.consultaUsuario);
        _spinnerC=findViewById(R.id.consultaUSpinner);
        _cListView=findViewById(R.id.consultaUListView);
        _registroM=findViewById(R.id.registrarMascotas);
        _consultaMList = findViewById(R.id.consultaListViewMasco);

        _registroUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(i);
            }
        });

        _consultaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ConsultaUserActivity.class);
                startActivity(i);
            }
        });

        _spinnerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CSpinnerActivity.class);
                startActivity(i);
            }
        });
        _cListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CUserListViewActivity.class);
                startActivity(i);
            }
        });
        _registroM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterMascoActivity.class);
                startActivity(i);
            }
        });
        _consultaMList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ConsultaMActivity.class);
                startActivity(i);
            }
        });

    }

}
