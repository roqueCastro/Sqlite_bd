package com.example.asus.sqlite_bd.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.sqlite_bd.R;
import com.example.asus.sqlite_bd.conexion.ConexionSQLiteHelper;
import com.example.asus.sqlite_bd.entidades.Mascota;
import com.example.asus.sqlite_bd.utilidades.Utilidades;

public class DetalleMascotasActivity extends AppCompatActivity {

    TextView _idM,_NomM,_raza,_idU,_nomUser,_telefonoU;

    ConexionSQLiteHelper conex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascotas);

        conex= new ConexionSQLiteHelper(this, "bd_prueba", null, 1);

        _idM = findViewById(R.id.c_id);
        _NomM = findViewById(R.id.c_nombre_m);
        _raza = findViewById(R.id.c_raza);
        _idU = findViewById(R.id.c_id_user);
        _nomUser = findViewById(R.id.c_nombre_user);
        _telefonoU = findViewById(R.id.c_telefono_user);

        Bundle objetivoEnviado = getIntent().getExtras();
        Mascota mascota = null;

        if(objetivoEnviado != null){
            mascota = (Mascota) objetivoEnviado.getSerializable("mascota");
            _idM.setText(mascota.getIdMascota().toString());
            _NomM.setText(mascota.getNombreMascota().toString());
            _raza.setText(mascota.getRaza().toString());
            consultarInfoPersonas(mascota.getIdDuenio());
        }
    }

    private void consultarInfoPersonas(Integer idDuenio) {
        SQLiteDatabase database = conex.getReadableDatabase();
        String [] parametros = {idDuenio.toString()};
        String [] campos = {Utilidades.C_NOMBRE, Utilidades.C_TELEFONO};

        Toast.makeText(getApplicationContext(), "El documeto: "+ idDuenio.toString(),Toast.LENGTH_SHORT).show();
        try {
            Cursor cursor = database.query(Utilidades.TABLA_USUARIO, campos, Utilidades.C_ID+"=?",
                    parametros, null, null, null);
            cursor.moveToFirst();
            _idU.setText(idDuenio.toString());
            _nomUser.setText(cursor.getString(0));
            _telefonoU.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "El documeto no existe: ",Toast.LENGTH_SHORT).show();
            _nomUser.setText("");
            _telefonoU.setText("");
        }
    }
}
