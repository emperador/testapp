package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by marco on 18/01/15.
 */
public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {

    SoundPool sp;
    int whip = 0;
    MediaPlayer mp; // para clips largos, soundPool para cortos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new  View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0); // inicializa un soundPool
        whip = sp.load(this, R.raw.whip, 1); // carga el sonido
        mp = MediaPlayer.create(this, R.raw.bond);
    }

    @Override
    public void onClick(View v) {
        if (whip!=0) // control de que haya asignado un valor al whip
        sp.play(whip,1,1,0,0,1); // establece valores para que suene lo más fuerte izq y der, sin loop
    }

    @Override
    protected void onPause() { // en la pausa del activity matamos todo
        super.onPause();
        sp.pause(whip);
        sp.release();
        mp.release();
    }

    @Override
    public boolean onLongClick(View v) { // para long clicks
        mp.start();
        return false;
    }
}
