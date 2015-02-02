package ml.marcoaponte.practiceapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class startingPoint extends ActionBarActivity {
    // se inicializan las variables
    Float total,km,l = Float.valueOf(0);
    Button calcular;
    TextView display;
    EditText kilometros, litros;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // se ejecuta al abrir el Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_point); // setea contView al mismo nombre del .xml
        total = Float.valueOf(0); // setea a 0
        calcular = (Button) findViewById(R.id.bTotal); // linkea con id en xml
        display = (TextView) findViewById(R.id.tvDisplay); // linkea con id en xml
        kilometros = (EditText) findViewById(R.id.etKms); // linkea con id en xml
        litros = (EditText) findViewById(R.id.etLitros); // linkea con id en xml

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                km = Float.parseFloat(kilometros.getText().toString()); // parsea el editview
                l = Float.parseFloat(litros.getText().toString()); // paresea el editView
                total = Float.valueOf((float) 0.95)*100*l/km; // calcula el total
                display.setText("Tu consumo fue de: " + total); // actualiza el textView
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starting_point, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
