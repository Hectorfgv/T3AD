package com.example.danielbc.logyreg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegAsignaturas extends AppCompatActivity {

    private EditText asgNom, asgAlu;

    //SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BD1", null, 1);
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reg_asignaturas);
        asgNom = (EditText) findViewById(R.id.asgNombre);
        asgAlu = (EditText) findViewById(R.id.asgAlumnos);





        dbAdapter = new MyDBAdapter(this);
        dbAdapter.abrirBD();




    }

    public void bRegistroPuls(View V) {
        String nombre = asgNom.getText().toString();
        String cantidad = asgAlu.getText().toString();


        if ((nombre.compareTo("") != 0) && (cantidad.compareTo("") != 0)) {

            int cant = Integer.parseInt(cantidad);

            //METODO GUARDAR ALUMNO

            dbAdapter.abrirBD();
            dbAdapter.insertarAsignatura(nombre, cant);
            //dbAdapter.cerrarBD();

            Toast creada =
                    Toast.makeText(getApplicationContext(),
                            "Asignatura ingresada en la tabla", Toast.LENGTH_SHORT);
            creada.show();

        } else {

            Toast error =
                    Toast.makeText(getApplicationContext(),
                            "Completa todos los campos :)", Toast.LENGTH_SHORT);

            error.show();

        }

    }
}
