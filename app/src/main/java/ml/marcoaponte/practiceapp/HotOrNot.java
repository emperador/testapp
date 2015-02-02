package ml.marcoaponte.practiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by marco on 01/02/15.
 */
public class HotOrNot {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";

    private static final String DATABASE_NAME = "HotOrNotDB";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public HotOrNot(Context c){ // creamos un constructor de HotOrNot que recibe un Context
        ourContext = c;
    }

    public HotOrNot openWritable() throws SQLException{ // es un metodo de clase (?) porque tiene el nombre de la clase en él
        ourHelper = new DBHelper(ourContext); // al instanciar la clase hot or not se establece ourContext que usamos aca
        ourDatabase = ourHelper.getWritableDatabase(); // usamos el metodo getWritable del helper que creamos
        return this; // RETORNA UN HOTORNOT  por eso que en la declaración pone eso
    }

    public void closeWritable(){
        ourHelper.close(); // cierra la bd
    }

    public long createEntry(String name, String hotness) {
        ContentValues cv = new ContentValues(); // es como el bundle en donde guardas cosas para usar despues
        cv.put(KEY_NAME,name); // se guarda el par, clave, valor
        cv.put(KEY_HOTNESS,hotness);
        return ourDatabase.insert(DATABASE_TABLE, null, cv); // retornamos este número para saber si se insertó bien
    }

    public String getData() {
        String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iHotness) + "\n";
        }

        return result;
    }

    private static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) { // En vez de ponerle al super los parametros del constructor
            // solamente le ponemos los nuestros, ya que solo necesitamos el context, el resto ya definimos
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT NOT NULL, " +
                    KEY_HOTNESS + " TEXT NOT NULL);"
            ); // Creamos la tabla en la bd con la sentencia SQL, con las tres columnas y sus tipos

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }


}
