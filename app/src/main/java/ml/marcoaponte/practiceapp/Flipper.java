package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by marco on 25/01/15.
 */
public class Flipper extends Activity implements View.OnClickListener {
    ViewFlipper flippy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flippy = (ViewFlipper) findViewById(R.id.viewFlipper);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(500); // hace un flip al medio segundo
        flippy.startFlipping();
    }

    @Override
    public void onClick(View v) {
        flippy.showNext(); // muestra el siguiente dentro del flipper al hacer click
    }
}
