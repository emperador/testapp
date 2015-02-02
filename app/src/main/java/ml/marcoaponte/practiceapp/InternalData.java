package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by marco on 25/01/15.
 */
public class InternalData extends Activity implements View.OnClickListener {

    EditText sharedData;
    TextView dataResults;
    FileOutputStream fos; // declaramos el fos para luego inicializar
    String filename = "InternalString"; // el nombre del filename

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
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE); // crea el File output para escribir de modo privado
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSavePrefs:
                String data = sharedData.getText().toString();
                // Grabando datos vía File
/*                File f = new File(filename); // este es el path de tu archivo
                try {
                    fos = new FileOutputStream(f); //abre el output stream para escribir en el archivo f
                    // ACÁ ESCIRIBIS DATOS
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/
                try {
                    fos = openFileOutput(filename, Context.MODE_PRIVATE); // sin file, el fileoutput directo
                    // NO GUARDA LUEGO DE QUE SE CIERRE LA APLICACIÓN
                    fos.write(data.getBytes()); // escribe los bytes
                    fos.close(); // cierra el output stream
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bLoadPrefs:
                new loadSomeStuffAsyncTask().execute(filename);
               //así se hacía antes, ahora se hace con la clase nueva para crear un nuevo thread
               // y que corra en background directamente de form asíncrona
               /* String collected = null;
                FileInputStream fis = null;
                try {
                    fis = openFileInput(filename); // abre el file input stream para leer
                    byte[] dataArray = new byte[fis.available()]; // define del tamaño de lo que el fis tiene disponible
                    while (fis.read(dataArray) != -1){ // significa que leíste tod o del file input stream
                        collected = new String(dataArray);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                        dataResults.setText(collected);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }*/
                break;
        }
    }

    private class loadSomeStuffAsyncTask extends AsyncTask<String, Integer, String>{

        ProgressDialog dialog;

        protected void onPreExecute (){ // a este metodo se le llama antes de ejecutarse nada
            // ejemplo de setear algo
            dialog = new ProgressDialog(InternalData.this); // le ponemos el contexto de esta clase
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100); // la cantidad maxima de datos (100) (?)
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) { // el ... significa que va a ser un array
            String collected = null;
            FileInputStream fis = null;

            for(int i=0 ; i<20; i++){
                publishProgress(5); // le pasa de a 5 al onprogressupdate
                try {
                    Thread.sleep(88); // le duerme para que se vea como crece el progress bar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dialog.dismiss(); // se deshace del diálogo
            try {
                fis = openFileInput(filename); // abre el file input stream para leer
                byte[] dataArray = new byte[fis.available()]; // define del tamaño de lo que el fis tiene disponible
                while (fis.read(dataArray) != -1){ // significa que leíste tod o del file input stream
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    return collected;
                    //dataResults.setText(collected);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) { // sirve para mostrar un progress bar por ejemplo
        //    super.onProgressUpdate(values);
            dialog.incrementProgressBy(progress[0]); // incrementa de a 5 como recibió como parámetro
        }

        @Override
        protected void onPostExecute(String result) { // se ejecuta al terminar la ejecución
            //super.onPostExecute(s);
            dataResults.setText(result);
        }
    }
}
