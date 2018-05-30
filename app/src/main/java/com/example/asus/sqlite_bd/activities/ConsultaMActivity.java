package com.example.asus.sqlite_bd.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;
import com.example.asus.sqlite_bd.entidades.Mascota;
import com.example.asus.sqlite_bd.entidades.Usuario;
import com.example.asus.sqlite_bd.utilidades.Utilidades;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;

public class ConsultaMActivity extends AppCompatActivity {

    ListView mascotasL;

    ArrayList<String> listaMascotas;
    ArrayList<Mascota> mascotas;

    ConexionSQLiteHelper conex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_m);

        mascotasL = findViewById(R.id.listMascotas);
        conex = new ConexionSQLiteHelper(this, "bd_prueba", null, 1);

        consultarMascotas();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaMascotas);
        mascotasL.setAdapter(adapter);
        mascotasL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Mascota mascota = mascotas.get(position);
                Intent intent = new Intent(ConsultaMActivity.this, DetalleMascotasActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("mascota", mascota);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void consultarMascotas() {
        SQLiteDatabase database = conex.getReadableDatabase();
        Mascota mascota = null;

        mascotas = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,null);
        while (cursor.moveToNext()){
            mascota = new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDuenio(cursor.getInt(3));

            mascotas.add(mascota);
        }
        obtenerMascotas();
    }

    private void obtenerMascotas() {
        listaMascotas = new ArrayList<>();
        for(int i=0; i<mascotas.size(); i++){
            listaMascotas.add(mascotas.get(i).getNombreMascota());
        }
    }
}
