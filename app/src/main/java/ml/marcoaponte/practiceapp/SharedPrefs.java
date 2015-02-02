package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by marco on 25/01/15.
 */
public class SharedPrefs extends Activity implements View.OnClickListener {

    EditText sharedData;
    TextView dataResults;
    SharedPreferences someData;
    public static String filename= "MySharedString"; // el nombre del archivo a utilizar para identificar el sharedpreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        Button save = (Button) findViewById(R.id.bSavePrefs);
        Button load = (Button) findViewById(R.id.bLoadPrefs);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        someData = getSharedPreferences(filename,0); // aca se usa el filename para obtener en modo privado (0) los datos guardados en filename
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSavePrefs:
                String data = sharedData.getText().toString();
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("sharedString", data); // se guarda el string con el key "sharedString" y el valor de data
                editor.commit();
                break;
            case R.id.bLoadPrefs:
                someData = getSharedPreferences(filename,0); // aca se usa el filename para obtener en modo privado (0) los datos guardados en filename
                String dataReturn = someData.getString("sharedString", "Couldn't load data"); //lee el string con el key sharedString, y si no puede dice Couldn...
                dataResults.setText(dataReturn);
                break;
        }
    }
}
