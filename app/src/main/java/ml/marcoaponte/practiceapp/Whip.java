package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by marco on 17/12/14.
 */
public class Whip extends Activity{
    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) { // se ejecuta al abrir el activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whip); //establece el content view = al nombre del layout .xml
        ourSong = MediaPlayer.create(Whip.this, R.raw.bond); // asigna el recurso

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //obtiene el shared preference del base context
        boolean music = getPrefs.getBoolean("checkbox", true); // lee el boolean del shared preference
        //que tiene el key "checkbox" como definimos en el xml, y en caso de que no exista es true
        if(music){
            ourSong.start();
        }


        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1000); // le hace dormir por 5000ms
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent openStartingPoint = new Intent("ml.marcoaponte.practiceapp.MENU");
                    startActivity(openStartingPoint); // inicia la actividad startingPoint asignada arriba
                }
            }
        };
        timer.start(); // inicia el timer
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release(); // libera el recurso cuando se cambia de Activity
        finish(); // mata (termina) la actividad
    }
}
