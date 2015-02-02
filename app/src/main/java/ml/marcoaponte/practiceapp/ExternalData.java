package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RawRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by marco on 30/01/15.
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView canWrite, canRead;
    private String state;
    boolean canW, canR;
    Spinner spin;
    String[] paths = {"Music", "Pictures", "Downloads"};
    File path = null;
    File file = null;
    EditText saveFile;
    Button confirm, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        saveFile = (EditText) findViewById(R.id.etSaveAs);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        checkState(); //chequea el estado
        // seteamos el arrayadapter con el contexto de la clase, el layout (simple spinner), y el array paths
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state = Environment.getExternalStorageState();// obtiene estado de external storage
        if(state.equals(Environment.MEDIA_MOUNTED)){
            // chequeamos si podemos leer y escribir
            canWrite.setText("true");
            canRead.setText("true");
            canW =canR = true;
        }
        else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            // chequeamos si solo podemos leer
            canWrite.setText("false");
            canRead.setText("true");
            canW = false;
            canR = true;
        }
        else{
            // chequeamos que no se puede ninguno
            canWrite.setText("false");
            canRead.setText("false");
            canW = canR = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int posicionDelItem = spin.getSelectedItemPosition();
        switch (posicionDelItem){
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC); // sirve para guardar cosas
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); // sirve para guardar cosas
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); // sirve para guardar cosas
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSaveFile:
                String f = saveFile.getText().toString(); // leemos el texto del editText
                file = new File(path, f + ".png"); // crea el archivo con nombre f en el path
                // al agregarse el .png le agrega la extension al nombre del archivo

                checkState();
                if(canW && canR){

                    path.mkdirs(); // crea el path si no existe, y si ya existe no hace nada

                    try {
                        InputStream is = getResources().openRawResource(R.raw.greenball); //como input stream cargamos la imagen que esta en la carpeta raw
                        OutputStream os = new FileOutputStream(file); // creamos outputstream con el file
                        byte[] data = new byte[is.available()]; // el tamaño del byte array es de lo disponible por el inputStream
                        is.read(data); // guarda en data el greenball
                        os.write(data); // guarda en el path del archivo el greenball
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(ExternalData.this, "El archivo ha sido grabado", Toast.LENGTH_LONG);
                        t.show(); // muestra el mensajito t en la pantalla

                        // actualiza los archivos para que el usuario use
                        MediaScannerConnection.scanFile(ExternalData.this, new String[] {file.toString()},null,
                                new MediaScannerConnection.OnScanCompletedListener() {
                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {
                                        Toast t = Toast.makeText(ExternalData.this, "scan completo",Toast.LENGTH_SHORT);
                                        t.show();
                                    }
                                }); //el cuarto parámetro es el callback, osea que va a hacer cuando vuelva
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE); // pone visible el boton de save
                break;
        }
    }
}
