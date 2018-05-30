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
import android.widget.Toast;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;
import com.example.asus.sqlite_bd.entidades.Usuario;
import com.example.asus.sqlite_bd.utilidades.Utilidades;

import java.util.ArrayList;

public class CUserListViewActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String>  listaInformaciones;
    ArrayList<Usuario> usuarios;
    ConexionSQLiteHelper conex;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuser_list_view);

        listView = findViewById(R.id.listViewUser);

        conex = new ConexionSQLiteHelper(this, "bd_prueba", null, 1);
        
        consultarListPersonas();

        ArrayAdapter adapter = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1,listaInformaciones);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String infomacion = " Id: "+usuarios.get(position).getId().toString()+"\n";
                infomacion+="Nombre: "+usuarios.get(position).getNombre().toString()+"\n";
                infomacion+="Telefono: "+usuarios.get(position).getTelefono().toString();

                Toast.makeText(getApplicationContext(), infomacion,Toast.LENGTH_SHORT).show();
                Usuario user = usuarios.get(position);

                Intent intent = new Intent(CUserListViewActivity.this, DetalleListVActivity.class);

                Bundle  bundle=new Bundle();
                bundle.putSerializable("usuario", user);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void consultarListPersonas() {
        SQLiteDatabase database = conex.getReadableDatabase();
        usuario=null;

        usuarios = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            usuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformaciones = new ArrayList<>();

        for (int i=0; i<usuarios.size(); i++){
            listaInformaciones.add(usuarios.get(i).getNombre());
        }
    }
}
