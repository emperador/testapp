package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by marco on 01/02/15.
 */
public class SQLiteView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteview);

        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
        HotOrNot info = new HotOrNot(this); // en realidad info es tu tabla
        try {
            info.openWritable(); // abrimos la conexión a la bd
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String data = info.getData(); // convierte a String la tabla
        info.closeWritable(); // cerramos la conexión a la bd
        tv.setText(data); // seteamos el tv a los datos

    }
}
