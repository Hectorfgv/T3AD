package com.example.danielbc.logyreg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends Activity {
    private EditText etNombre, etApellido, etCorreo, etTelefono, etUserName, etPassword;
    private RadioButton rButtonH, rButtonM;
    private Button bRegistro, bCancelar;
    private View.OnClickListener radioL;
    private TextView seleccion;
    public String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        rButtonH = (RadioButton) findViewById(R.id.rbHombre);
        rButtonM = (RadioButton) findViewById(R.id.rbMujer);

        bRegistro = (Button) findViewById(R.id.bRegistro);
        bCancelar = (Button) findViewById(R.id.bCancelar);


    }


    public void bRegistroPuls(View v) {

        guardarRegistro();

        Intent r = new Intent(Registro.this, LogIn.class);
        startActivity(r);
    }

    public void bCancelarPuls(View v) {
        Intent lr = new Intent(Registro.this, LogIn.class);
        startActivity(lr);
    }

    private void guardarRegistro() {

        SharedPreferences guardarPref = getSharedPreferences
                ("preferencias", Context.MODE_PRIVATE);

        String sexo;
        if (rButtonH.isChecked() == true) {
            sexo = "Hombre";
        } else {
            sexo = "Mujer";
        }

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String correo = etCorreo.getText().toString();
        String telefono = etTelefono.getText().toString();
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();


        SharedPreferences.Editor editor1 = guardarPref.edit();

        editor1.putString("nombre", nombre);
        editor1.putString("apellido", apellido);
        editor1.putString("correo", correo);
        editor1.putString("telefono", telefono);
        editor1.putString("userName", userName);
        editor1.putString("password", password);
        editor1.putString("sexo", sexo);

        editor1.commit();


    }


}
