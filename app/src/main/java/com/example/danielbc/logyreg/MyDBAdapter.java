package com.example.danielbc.logyreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {

    private static final String DATABASE_NAME = "DB3.db";
    private static final String DATABASE_AL = "alumnos";
    private static final String DATABASE_PRO = "profesores";
    private static final String DATABASE_ASG = "asignaturas";
    private static final String DATABASE_PKM = "pokemon";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_AL = "CREATE TABLE " + DATABASE_AL + " (_ID integer primary key autoincrement, Nombre text, Apellido text, Edad text, Ciclo text, Curso text, Media text);";
    private static final String DATABASE_CREATE_PRO = "CREATE TABLE " + DATABASE_PRO + " (_ID integer primary key autoincrement, Nombre text, Apellido text, Edad text, Ciclo text, Tutor text, Despacho text);";

    //EXAMEN CREAR TABLA ASIGNATURAS
    private static final String DATABASE_CREATE_ASG = "CREATE TABLE " + DATABASE_ASG + " (_ID integer primary key autoincrement, Nombre text, Alumnos integer);";
    private static final String DATABASE_CREATE_PKM = "CREATE TABLE " + DATABASE_PKM + " (_ID integer primary key autoincrement, Nombre text, Tipo text);";

    private static final String DATABASE_DROP_AL = "DROP TABLE IF EXISTS " + DATABASE_AL + ";";
    private static final String DATABASE_DROP_PRO = "DROP TABLE IF EXISTS " + DATABASE_PRO + ";";

    //EXAMEN
    private static final String DATABASE_DROP_ASG = "DROP TABLE IF EXISTS " + DATABASE_ASG + ";";
    private static final String DATABASE_DROP_PKM = "DROP TABLE IF EXISTS " + DATABASE_PKM + ";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;

    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;

    // INSTANCIA DE LA BBDD
    private SQLiteDatabase db;

    public MyDBAdapter(Context c) {

        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //METODO ABRIR BBDD
    public void abrirBD() {

        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }

    }
    public void insertarPokemon(String nom, String tipo) {

        ContentValues valores4 = new ContentValues();

        valores4.put("Nombre", nom);
        valores4.put("Tipo", tipo);



        db.insert(DATABASE_PKM, null, valores4);

    }




    //EXAMEN -- METODO DE INSERTAR DE ASIGNATURAS
    public void insertarAsignatura(String nom, Integer alum) {

        ContentValues valores3 = new ContentValues();

        valores3.put("Nombre", nom);
        valores3.put("Alumnos", alum);


        db.insert(DATABASE_ASG, null, valores3);

    }

    //EXAMEN -- METODO DE RECUPERACION DE ALUMNOS NOMBRE
    public ArrayList<String> recuperarAlumnosNombre() {

        ArrayList<String> alumnos = new ArrayList<String>();

        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_AL, null, null,
                null, null, null, null);

        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()) {

            do {

                alumnos.add(cursor.getString(1));

            } while (cursor.moveToNext());

        }
        return alumnos;
    }

    public ArrayList<String> recuperarPokemon() {

        ArrayList<String> pokes = new ArrayList<String>();

        //RECUPERAMOS EN UN CURSOR LA CONSULTA REALIZADA
        Cursor pokemons = db.query(DATABASE_PKM, null, null,
                null, null, null, null);

        //RECORREMOS EL CURSOR
        if (pokemons != null && pokemons.moveToFirst()) {

            do {
                pokes.add("Nombre: "+pokemons.getString(1) + " Tipo: " + pokemons.getString(2));
            } while (pokemons.moveToNext());

           /* int index;

            index = cursor.getColumnIndexOrThrow("first_name");
            String firstName = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("last_name");
            String lastName = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("id");
            long id = cursor.getLong(index);
            */
        }
        return pokes;
    }

    //EXAMEN -- METODO DE RECUPERACION DE ASG
    public ArrayList<String> recuperarAsignaturas(String query) {

        ArrayList<String> ASG = new ArrayList<>();

        String Nombre = query;

        Cursor asignaturas = db.query(DATABASE_ASG, null, "Nombre=?", new String[]{Nombre}, null, null, null);

        if (asignaturas != null && asignaturas.moveToFirst()) {
            do {
                ASG.add(asignaturas.getString(1) + " " + asignaturas.getString(2));
            } while (asignaturas.moveToNext());
        }
        return ASG;
    }

    public ArrayList<String[]> recuperarAlumnosEdad(){
        //Devuelvo una lista de arrays de Strings no una lista de Strings, cada array, es un alumno
        ArrayList<String[]> est = new ArrayList<String[]>();

        Cursor cursor = db.rawQuery("select * from alumnos where (EDAD BETWEEN 20 AND 25) order by ciclo", null);
        // Cursor cursor = db.rawQuery("select * from estudiantes where (ciclo='DAM' or ciclo='DAW') order by ciclo", null);
        if (cursor != null && cursor.moveToFirst()){
            do{
                //Cada columna es un valor del array
                String [] valores = new String [5];
                valores[0]=(cursor.getString(1));
                valores[1]=(cursor.getString(2));
                valores[2]=(cursor.getString(3));
                valores[3]=(cursor.getString(4));
                valores[4]=(cursor.getString(5));

                est.add(valores);
            }while (cursor.moveToNext());
        }

        return est;
    }




    //EXAMEN -- METODO DE RECUPERACION DE ALUMNOS EDAD

/*
    public ArrayList<String> recuperarAlumnosEdad() {

        ArrayList<String> alumnos = new ArrayList<String>();



        for (int i = 20; i <= 25; i++) {

            String edad = Integer.toString(i);

            Cursor cursorEdad  = db.query(DATABASE_AL, null, "edad=?", new String[]{edad}, null, null, null);




        //Recorremos el cursor
        if (cursorEdad != null && cursorEdad.moveToFirst()) {

            do {

                alumnos.add("* " + cursorEdad.getString(1) + " " +
                        cursorEdad.getString(2) + "\n" + cursorEdad.getString(3) + " años, " +
                        cursorEdad.getString(5) + " " + cursorEdad.getString(4) + ", Nota media: " +
                        cursorEdad.getString(6));

            } while (cursorEdad.moveToNext());

        }}
        return alumnos;
    }

*/

    //METODO DE INSERTAR ALUMNOS
    public void insertarAlumno(String nom, String ape, String eda, String cic, String curs, String med) {

        ContentValues valores = new ContentValues();

        valores.put("Nombre", nom);
        valores.put("Apellido", ape);
        valores.put("Edad", eda);
        valores.put("Ciclo", cic);
        valores.put("Curso", curs);
        valores.put("Media", med);

        db.insert(DATABASE_AL, null, valores);

    }

    //METODO DE INSERTAR DE PROFESORES
    public void insertarProfesor(String nom, String ape, String eda, String cic, String tut, String desp) {

        ContentValues valores2 = new ContentValues();

        valores2.put("Nombre", nom);
        valores2.put("Apellido", ape);
        valores2.put("Edad", eda);
        valores2.put("Ciclo", cic);
        valores2.put("Tutor", tut);
        valores2.put("Despacho", desp);

        db.insert(DATABASE_PRO, null, valores2);

    }

    //METODO DE RECUPERACION DE ALUMNOS
    public ArrayList<String> recuperarAlumnos() {

        ArrayList<String> alumnos = new ArrayList<String>();

        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_AL, null, null,
                null, null, null, null);

        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()) {

            do {

                alumnos.add("* " + cursor.getString(1) + " " +
                        cursor.getString(2) + "\n" + cursor.getString(3) + " años, " +
                        cursor.getString(5) + " " + cursor.getString(4) + ", Nota media: " +
                        cursor.getString(6));

            } while (cursor.moveToNext());

        }
        return alumnos;
    }

    //METODO DE RECUPERACION DE PROFESORES
    public ArrayList<String> recuperarProfesores() {

        ArrayList<String> profesores = new ArrayList<String>();

        //RECUPERAMOS EN UN CURSOR LA CONSULTA REALIZADA
        Cursor cursor = db.query(DATABASE_PRO, null, null,
                null, null, null, null);

        //RECORREMOS EL CURSOR
        if (cursor != null && cursor.moveToFirst()) {

            do {

                profesores.add("* " + cursor.getString(1) + " " +
                        cursor.getString(2) + "\n" + cursor.getString(3) + " años, " +
                        cursor.getString(5) + " " + cursor.getString(4) + ", Nota media: " +
                        cursor.getString(6));

            } while (cursor.moveToNext());

        }
        return profesores;
    }

    //METODO DE RECUPERACION DE TODOS
    public ArrayList<String> recuperarTodo() {

        ArrayList<String> todo = new ArrayList<String>();

        //RECUPERAMOS EN UN CURSOR LA CONSULTA REALIZADA
        Cursor cursorPRO = db.query(DATABASE_PRO, null, null,
                null, null, null, null);

        //RECORREMOS EL CURSOR
        if (cursorPRO != null && cursorPRO.moveToFirst()) {

            do {

                todo.add("* " + cursorPRO.getString(1) + " " +
                        cursorPRO.getString(2) + "\n" + cursorPRO.getString(3) + " años, " +
                        cursorPRO.getString(5) + " " + cursorPRO.getString(4) + ", Nota media: " +
                        cursorPRO.getString(6));

            } while (cursorPRO.moveToNext());

        }


        //Recuperamos en un cursor la consulta realizada
        Cursor cursorAL = db.query(DATABASE_AL, null, null,
                null, null, null, null);

        //Recorremos el cursor
        if (cursorAL != null && cursorAL.moveToFirst()) {

            do {

                todo.add("* " + cursorAL.getString(1) + " " +
                        cursorAL.getString(2) + "\n" + cursorAL.getString(3) + " años, " +
                        cursorAL.getString(5) + " " + cursorAL.getString(4) + ", Nota media: " +
                        cursorAL.getString(6));

            } while (cursorAL.moveToNext());

        }
        return todo;
    }

    //METODO DE RECUPERACION ALUMNO POR CICLO
    public ArrayList<String> recuperarAlumnoCiclo(String query) {

        ArrayList<String> alumnoPorCiclo = new ArrayList<>();

        String ciclo = query;

        Cursor cursorAlumnosPorCiclo = db.query(DATABASE_AL, null, "ciclo=?", new String[]{ciclo}, null, null, null);

        if (cursorAlumnosPorCiclo != null && cursorAlumnosPorCiclo.moveToFirst()) {
            do {
                alumnoPorCiclo.add(cursorAlumnosPorCiclo.getString(1) + " " + cursorAlumnosPorCiclo.getString(2)
                        + " \n " + cursorAlumnosPorCiclo.getString(3) + " " + cursorAlumnosPorCiclo.getString(4)
                        + " \n " + cursorAlumnosPorCiclo.getString(5) + " \n " + cursorAlumnosPorCiclo.getString(6));
            } while (cursorAlumnosPorCiclo.moveToNext());
        }
        return alumnoPorCiclo;
    }

    //IMPLEMENTAMOS EL HELPER
    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE_AL);
            db.execSQL(DATABASE_CREATE_PRO);
            db.execSQL(DATABASE_CREATE_ASG);
            db.execSQL(DATABASE_CREATE_PKM);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(DATABASE_DROP_AL);
            db.execSQL(DATABASE_DROP_PRO);
            db.execSQL(DATABASE_DROP_ASG);
            db.execSQL(DATABASE_DROP_PKM);
            onCreate(db);

        }

    }
}