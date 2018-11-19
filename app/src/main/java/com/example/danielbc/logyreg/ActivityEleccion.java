package com.example.danielbc.logyreg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityEleccion extends AppCompatActivity {
    private Button b3a, b3b, b3c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion);

        b3a = (Button) findViewById(R.id.ACT3A);
        b3b = (Button) findViewById(R.id.ACT3B);
        b3c = (Button) findViewById(R.id.ACT3C);

    }

    public void act3apuls(View view) {

        Intent a = new Intent(getApplicationContext(), LogIn.class);
        startActivity(a);

    }

    public void act3bpuls(View view) {
        Intent b = new Intent(getApplicationContext(), AlumnoProfesor.class);
        startActivity(b);

    }

    public void act3cpuls(View view) {
        Intent b = new Intent(getApplicationContext(), Mostrador.class);
        startActivity(b);
        //Intent c = new Intent(getApplicationContext(), LogIn.class);
        //startActivity(b);


    }
}
