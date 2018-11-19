package com.example.danielbc.logyreg;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class LogIn extends Activity {
    private Button bLogin, bReg;
    private EditText etUserName, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bReg = (Button) findViewById(R.id.bReg);

    }


    public void bRegPuls(View v) {

        Intent r = new Intent(getApplicationContext(), Registro.class);
        startActivity(r);
    }

    public void bLoginPuls(View v) {

        compararLog();

    }


    private void compararLog() {

        SharedPreferences guardarPref = getSharedPreferences
                ("preferencias", Context.MODE_PRIVATE);

        String user = guardarPref.getString("userName", "0");
        String pass = guardarPref.getString("password", "0");

        String userName = etUserName.getText().toString();
        String passW = etPassword.getText().toString();


        if (userName.compareTo(user) == 0 && passW.compareTo(pass) == 0) {

            Intent lr = new Intent(LogIn.this, LogyReg.class);
            startActivity(lr);

        } else {

            Toast t1 = Toast.makeText(getApplicationContext(),
                    "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT);
            t1.show();

        }
    }


}
