package com.example.danielbc.logyreg;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Mostrador extends AppCompatActivity {
    private TextView ensenar;
    private MyDBAdapter dbAdapter;
    private Button alTodos, alCiclo, alCurso, proTodos, Todo, pokemon;
    private EditText buscar;
    private ArrayList<String[]> auxiliar;
    private MyDBAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrador);

        auxiliar = new ArrayList<String[]>();
        buscar =(EditText) findViewById(R.id.etBuscar);
        ensenar = (TextView) findViewById(R.id.ensenar);
        alTodos = (Button) findViewById(R.id.alTodo);
        alCiclo = (Button) findViewById(R.id.alCiclos);
        alCurso = (Button) findViewById(R.id.alCursos);
        proTodos = (Button) findViewById(R.id.proTodo);
        Todo = (Button) findViewById(R.id.ASGbtn);
        pokemon = (Button) findViewById(R.id.bPokemon);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.abrirBD();

        alTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador = new MyDBAdapter(getApplicationContext());
                adaptador.abrirBD();

                auxiliar = adaptador.recuperarAlumnosEdad();

                //Limpio búsquedas anteriores
                ensenar.setText("");
                for (String [] aux: auxiliar)
                {
                    //Veia innecesario hacer un for para recorrer el array debido al "fórmato" que le doy de salida.
                    ensenar.setText(ensenar.getText()+"Nombre: "+aux[0]+"\n");
                    ensenar.setText(ensenar.getText()+"Edad: "+aux[1]+"\n");
                    ensenar.setText(ensenar.getText()+"Ciclo: "+aux[2]+"\n");
                    ensenar.setText(ensenar.getText()+"Curso: "+aux[3]+"\n");
                    ensenar.setText(ensenar.getText()+"Nota Media: "+aux[4]+"\n");

                    ensenar.setText(ensenar.getText()+"\n"+"\n");
                }
            }
        });
    }

    //BOTON TODOS LOS ALUMNOS
    /*public void alTodoPuls(View V) {

        ensenar.setText("Alumnos \n");

        ArrayList<String> alumnos = dbAdapter.recuperarAlumnos();

        for (int i = 0; i < alumnos.size(); i++) {

            ensenar.setText(ensenar.getText() + "\n" + alumnos.get(i) + "\n");

        }
    }*/

    public void alTodoPuls(View V) {
        MyDBAdapter adaptador;
        adaptador = new MyDBAdapter(getApplicationContext());
        adaptador.abrirBD();

        auxiliar = adaptador.recuperarAlumnosEdad();
        ensenar.setText("Alumnos \n");


        for (String [] aux: auxiliar)
        {
            //Veia innecesario hacer un for para recorrer el array debido al "fórmato" que le doy de salida.
            ensenar.setText(ensenar.getText()+"Nombre: "+aux[0]+"\n");
            ensenar.setText(ensenar.getText()+"Edad: "+aux[1]+"\n");


            ensenar.setText(ensenar.getText()+"\n"+"\n");
        }
    }

/*
    //EXAMEN
    //MOSTRAR ALUMNOS ENTRE 20 Y 25
    public void alTodoPuls(View V) {

        ensenar.setText("Alumnos \n");

        ArrayList<String> alumnos = dbAdapter.recuperarAlumnosEdad();

        for (int i = 0; i < alumnos.size(); i++) {

            ensenar.setText(ensenar.getText() + "\n" + alumnos.get(i) + "\n");

        }
    }
*/
    public void PKMPuls(View V) {

        ensenar.setText("Pokemon \n");

        ArrayList<String> pokemon = dbAdapter.recuperarPokemon();

        for (int i = 0; i < pokemon.size(); i++) {

            ensenar.setText(ensenar.getText() + "\n" + pokemon.get(i) + "\n");
        }
    }

    //EXAMEN
    //BOTON ALUMNOS Y PROFESORES
    public void ASGPuls(View V) {

        ensenar.setText("Asignaturas \n");

        String query = buscar.getText().toString();
        ArrayList<String> ASG = dbAdapter.recuperarAsignaturas(query);

        for (int i = 0; i < ASG.size(); i++) {

            ensenar.setText(ensenar.getText() + "\n" + ASG.get(i) + "\n");

        }
    }

    //BOTON ALUMNO POR CILO
    public void alCiclosPuls(View V) {

        ensenar.setText("Alumnos \n");

        String query = buscar.getText().toString();
        ArrayList<String> alumnoPorCiclo = dbAdapter.recuperarAlumnoCiclo(query);

        for (int i = 0; i < alumnoPorCiclo.size(); i++) {
            ensenar.setText(ensenar.getText() + " \n " + alumnoPorCiclo.get(i));
        }
    }

    //BOTON ALUMNO POR CURSO
    public void alCursosPuls(View V) {

        Toast t1 = Toast.makeText(getApplicationContext(),
                "Boton en construcción", Toast.LENGTH_SHORT);
        t1.show();
    }

    //BOTON TODOS LOS PROFESORES
    public void proTodoPuls(View V) {

        ensenar.setText("Profesores \n");

        ArrayList<String> profesores = dbAdapter.recuperarProfesores();

        for (int i = 0; i < profesores.size(); i++) {

            ensenar.setText(ensenar.getText() + "\n" + profesores.get(i) + "\n");

        }
    }

    //BOTON ALUMNOS Y PROFESORES
    /*public void alproTodoPuls(View V) {

        ensenar.setText("TODO \n");

        ArrayList<String> todo = dbAdapter.recuperarTodo();

        for (int i = 0; i < todo.size(); i++) {

            ensenar.setText(ensenar.getText() + "\n" + todo.get(i) + "\n");

        }
    }*/
}
