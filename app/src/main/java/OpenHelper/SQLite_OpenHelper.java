package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLite_OpenHelper extends SQLiteOpenHelper {


    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryAl = "create table alumnos(_ID integer primary key autoincrement, Nombre text, Apellido text, Edad text, Ciclo text, Curso text, Media text);";
        String queryPro = "create table profesores(_ID integer primary key autoincrement, Nombre text, Edad text, Ciclo text, Tutor text, Despacho text);";

        db.execSQL(queryAl);
        db.execSQL(queryPro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insertar Alumnos

    public void insertarAlumno(String nom, String ape, String eda, String cic, String curs, String med) {

        ContentValues valores = new ContentValues();

        valores.put("Nombre", nom);
        valores.put("Apellido", ape);
        valores.put("Edad", eda);
        valores.put("Ciclo", cic);
        valores.put("Curso", curs);
        valores.put("Media", med);

        this.getWritableDatabase().insert("alumnos", null, valores);

    }

    public void insertarProfesor(String nom, String eda, String cic, String tut, String desp) {

        ContentValues valores = new ContentValues();

        valores.put("Nombre", nom);
        valores.put("Edad", eda);
        valores.put("Ciclo", cic);
        valores.put("Tutor", tut);
        valores.put("Despacho", desp);

        this.getWritableDatabase().insert("profesores", null, valores);

    }

    //Abrir BBDD
    public void abrirBD() {

        this.getWritableDatabase();

    }

    //Cerar BBDD
    public void cerrarBD() {

        this.close();

    }
}
