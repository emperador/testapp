package ml.marcoaponte.practiceapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by marco on 22/12/14.
 */
public class Menu extends ListActivity{

    String classes[] = {"startingPoint", "Whip", "TextPlay", "Email", "Camara", "Datos",
                        "GFX", "GFXSurface", "GFXSurface2", "SoundStuff", "Slider", "Tabs",
                        "SimpleBrowser", "Flipper", "SharedPrefs", "InternalData","ExternalData",
                        "SQLite"}; // idéntico al nombre de la clase!!

    @Override
    protected void onCreate(Bundle savedInstanceState) { //se ejecuta al abrir el activity
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //request para fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, // hace que sea pantalla completa
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // hacer siempre antes del setcontView o setListAd
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override // linkea con id en xml
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese = classes[position]; // iguala al valor de la posición del array que fue clickeado
        try {
            Class ourClass = Class.forName("ml.marcoaponte.practiceapp."+cheese); // crea clase que linkea a la clase del array que corresponda
            Intent ourIntent = new Intent(Menu.this, ourClass); //crea un intent con el contexto Menu.this y la clase recien creada
            startActivity(ourIntent); // inicia la actividad con el intent reciien creado
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) { //de la clase View.Menu NO LA CLASE MENU NUESTRA...
        super.onCreateOptionsMenu(menu);
        MenuInflater blowup = getMenuInflater();
        blowup.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){ // de acuerdo al item del menu que fue seleccionado, cada caso
            case R.id.aboutUs:
                Intent i = new Intent("ml.marcoaponte.practiceapp.ABOUTUS");
                startActivity(i);
                break;
            case R.id.Preferences:
                Intent p = new Intent("ml.marcoaponte.practiceapp.PREFERS");
                startActivity(p);
                break;
            case R.id.Exit:
                finish(); // sale de la app
                break;
        }
        return false;
    }
}
