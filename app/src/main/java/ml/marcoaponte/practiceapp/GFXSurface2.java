package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by marco on 13/01/15.
 */
public class GFXSurface2 extends Activity implements View.OnTouchListener {

    VistaSurfaceBorrada ourSurfaceView;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new VistaSurfaceBorrada(this); // inicializa el surface con nuestra vistasurface personalizada
        ourSurfaceView.setOnTouchListener(this); // setea el ontouchlistener para el surfaceview
        x = 0;
        y = 0;
        setContentView(ourSurfaceView); //seteas tu content view a la variable de tu clase personalizada
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause(); // usa el metodo pause del ourSurfaceView (vistaSurface)
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();// usa el metodo resume del ourSurfaceView (vistaSurface)
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ourSurfaceView.setEquis(event.getX());
        ourSurfaceView.setYGriega(event.getY());
       // x = event.getX(); // toma del event el valor de la x en px
       // y = event.getY(); // toma del event el valor de la y en px
     /* aca lo que tenía que setear estas variables en vistasurface, pasándole al metodo
     setEquis(event.getX()), misma cosa para Y y de esa forma no hace falta pegar la otra
     clase dentro de esta */
        return true; // al cambiar de false a true, hace que to do el tiempo esté mirando el valor
        // no que solamente una vez haga el ontouch y muera, de esta forma "arrastra la bola"
    }

}
