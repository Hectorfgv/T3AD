package com.example.danielbc.logyreg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegProfesor extends AppCompatActivity {
    private EditText proNombre, proApellido, proEdad, proCiclo, proCurso, proDespacho;

    //SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BD1", null, 1);
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_profesor);

        proNombre = (EditText) findViewById(R.id.etProNombre);
        proApellido = (EditText) findViewById(R.id.etProApellido);
        proEdad = (EditText) findViewById(R.id.etProEdad);
        proCiclo = (EditText) findViewById(R.id.etProCiclo);
        proCurso = (EditText) findViewById(R.id.etProCurso);
        proDespacho = (EditText) findViewById(R.id.etProDespacho);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.abrirBD();

    }


    public void bRegistroPuls(View V) {
        String nombre = proNombre.getText().toString();
        String apellido = proApellido.getText().toString();
        String edad = proEdad.getText().toString();
        String ciclo = proCiclo.getText().toString();
        String curso = proCurso.getText().toString();
        String despacho = proDespacho.getText().toString();

        if ((nombre.compareTo("") != 0) && (apellido.compareTo("") != 0) &&
                (edad.compareTo("") != 0) && (ciclo.compareTo("") != 0) &&
                (curso.compareTo("") != 0) && (despacho.compareTo("") != 0)) {


            //METODO GUARDAR ALUMNO

            dbAdapter.abrirBD();
            dbAdapter.insertarProfesor(nombre, apellido, edad, ciclo, curso, despacho);
            //dbAdapter.cerrarBD();

            Toast creada =
                    Toast.makeText(getApplicationContext(),
                            "Profesor ingresado en la tabla", Toast.LENGTH_SHORT);
            creada.show();

        } else {

            Toast error =
                    Toast.makeText(getApplicationContext(),
                            "Completa todos los campos :)", Toast.LENGTH_SHORT);

            error.show();

        }

    }


    public void bCancelarPuls(View V) {
        Intent cancelar = new Intent(getApplicationContext(), AlumnoProfesor.class);
        startActivity(cancelar);


    }


}
