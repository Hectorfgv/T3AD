package com.example.danielbc.logyreg;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogyReg extends AppCompatActivity {

    private TextView txtNombre, txtApellido, txtCorreo, txtTelefono, txtUserName, txtSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_y_reg);


        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellido = (TextView) findViewById(R.id.txtApellido);
        txtCorreo = (TextView) findViewById(R.id.txtCorreo);
        txtTelefono = (TextView) findViewById(R.id.txtTelefono);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtSexo = (TextView) findViewById(R.id.txtSexo);

        cargarRegistro();

    }


    private void cargarRegistro() {
        SharedPreferences guardarPref = getSharedPreferences
                ("preferencias", Context.MODE_PRIVATE);
        String name = guardarPref.getString("nombre", "0");
        String surname = guardarPref.getString("apellido", "0");
        String mail = guardarPref.getString("correo", "0");
        String phone = guardarPref.getString("telefono", "0");
        String user = guardarPref.getString("userName", "0");
        String sexo = guardarPref.getString("sexo", "0");

        txtNombre.setText(name);
        txtApellido.setText(surname);
        txtCorreo.setText(mail);
        txtTelefono.setText(phone);
        txtUserName.setText(user);
        txtSexo.setText(sexo);
    }
}
