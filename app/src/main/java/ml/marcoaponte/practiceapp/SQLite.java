package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by marco on 01/02/15.
 */
public class SQLite extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView;
    EditText sqlName, sqlHotness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);
        seteos();
    }

    private void seteos() {
        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSQLUpdate:
                boolean funciono = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();
                    HotOrNot entry = new HotOrNot(SQLite.this);
                    entry.openWritable();
                    entry.createEntry(name, hotness);
                    entry.closeWritable();
                } catch (Exception e){
                    funciono = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this); // se le pasa el nombre la clase como parámetro
                    d.setTitle("Negativen!");
                    TextView tv = new TextView(this); // se le pasa el nombre la clase como parámetro
                    tv.setText(error);
                    d.setContentView(tv); // el contentView del dialog es el TextView quee loco no?
                    d.show();
                } finally {
                    if (funciono){
                        Dialog d = new Dialog(this); // se le pasa el nombre la clase como parámetro
                        d.setTitle("Claarens");
                        TextView tv = new TextView(this); // se le pasa el nombre la clase como parámetro
                        tv.setText("Exito");
                        d.setContentView(tv); // el contentView del dialog es el TextView quee loco no?
                        d.show();
                    }
                }
                break;
            case R.id.bSQLopenView:
                break;
        }
    }
}
