package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by marco on 04/01/15.
 */
public class GFX extends Activity {
    Vista ourView; // definimos nuestra propia clase personalizada para la vista
    PowerManager.WakeLock wL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // wake-lock hace que se quede la pantalla prendida
        PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE); // setea el power manager
        wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "loquesea");
        super.onCreate(savedInstanceState);
        wL.acquire(); // activa el wakelock
        ourView = new Vista(this);
        setContentView(ourView); //seteas tu content view a la variable de tu clase personalizada
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release(); // libera el wakelock
    }
}
