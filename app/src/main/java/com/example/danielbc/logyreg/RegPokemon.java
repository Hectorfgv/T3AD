package com.example.danielbc.logyreg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegPokemon extends AppCompatActivity {

    private EditText pokeNom, pokeTip;

    private MyDBAdapter dbAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reg_pokemon);
        pokeNom = (EditText) findViewById(R.id.NombrePkm);
        pokeTip = (EditText) findViewById(R.id.TipoPkm);


        dbAdapter = new MyDBAdapter(this);
        dbAdapter.abrirBD();


    }
    public void bRegistroPuls(View V) {
        String nombre = pokeNom.getText().toString();
        String tipo = pokeTip.getText().toString();


        if ((nombre.compareTo("") != 0)) {

            //METODO GUARDAR POKEMON

            dbAdapter.abrirBD();
            dbAdapter.insertarPokemon(nombre,tipo);
            //dbAdapter.cerrarBD();

            Toast creada =
                    Toast.makeText(getApplicationContext(),
                            "Pokemon ingresado en la tabla", Toast.LENGTH_SHORT);
            creada.show();

        } else {

            Toast error =
                    Toast.makeText(getApplicationContext(),
                            "Completa todos los campos :)", Toast.LENGTH_SHORT);

            error.show();

        }

    }

}
