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
public class GFXSurface extends Activity implements View.OnTouchListener {

    VistaSurface ourSurfaceView;
    float x, y, sX, sY, fX, fY, dX, dY,aniX, aniY, scaledX, scaledY = 0;
    Bitmap test, plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new VistaSurface(this); // inicializa el surface con nuestra vistasurface personalizada
        ourSurfaceView.setOnTouchListener(this); // setea el ontouchlistener para el surfaceview
        x = 0;
        y = 0;
        test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball); //obtenemos el bitmap
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus); //obtenemos el bitmap
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
        try {
            Thread.sleep(50); // le dormis al thread para que NO use toda la velocidad de proc. disponible
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = event.getX(); // toma del event el valor de la x en px
        y = event.getY(); // toma del event el valor de la y en px
     /* aca lo que tenía que setear estas variables en vistasurface, pasándole al metodo
     setEquis(event.getX()), misma cosa para Y y de esa forma no hace falta pegar la otra
     clase dentro de esta */
        switch (event.getAction()){ // se usa el case para detectar que tipo de toque fue
            // down es al tocar y up es al soltar (bajar y subir el dedo)
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX; // definimos los dX y dY
                dY = fY - sY;
                scaledX = dX / 30; // escalamos para que la diferencia no sea taan grand
                scaledY = dY / 30;
                x = y = 0; // ceramos x e y para que se borre la pelota que se dibuja al comienzo
                break;
        }
        return true; // loopea dentro del ontouch al estar en true
    }

    public class VistaSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public VistaSurface(Context context) {
            super(context);
            ourHolder = getHolder(); // obtenemos un holder para utilizar sus métodos
    /*        ourThread = new Thread(this); // se le pasa el contexto que es this
            ourThread.start();    sacamos del constructor y ponemos en resume*/
        }

        public void pause() {
            isRunning = false; // se setea a  false variable bandera
            while (true) {
                try {
                    ourThread.join(); // que se bloquee el thread hasta que el receptor termine su ejecución
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume() {
            isRunning = true; // se setea a true variable bandera
            ourThread = new Thread(this); // se le pasa el contexto que es this, creando el Thread
            ourThread.start();
        }

        @Override
        public void run() {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid()) // si es inválida la superficie, continuar
                    continue;

                Canvas canvas = ourHolder.lockCanvas(); // bloqueas el canvas
                canvas.drawRGB(02, 2, 150);
                if (x != 0 && y !=0 ){
                    // del bitmap factory, y le pasamos el recurso que queremos (greenball)
//                    canvas.drawBitmap(test, ourSurfaceView.getEquis(), ourSurfaceView.getYGriega(), null);
 /* en vez de pegar la clase acá dentro, podíamos dejarla afuera, pero crearle métodos para acceder a las variables
 *  como debería ser, o bien ponerles static (que les hacen ser globales) para poder usar las variables
 *  de la otra clase sin tener que pegar una clase dentro de la otra. ESTA TO DO MEZCLADO, MAL HECHO*/
                    canvas.drawBitmap(test, x - test.getWidth()/2, y-test.getHeight()/2, null); // dibujamos en el canvas el bitmap test creado
                }
                if(sX != 0 && sY != 0){  // también se debería hacer como en GFX2 con los getters sin clase metida
                    canvas.drawBitmap(plus, sX-plus.getWidth()/2, sY-plus.getHeight()/2,null);
                }
                if(fX != 0 && fY != 0){ // por simplicidad seguimos igual que tutorial nomas ya
                    canvas.drawBitmap(test, fX -test.getWidth()/2-aniX, fY-test.getHeight()/2-aniY, null); // dibujamos en el canvas el bitmap test creado
                    canvas.drawBitmap(plus, fX-plus.getWidth()/2, fY-plus.getHeight()/2,null);
                }
                aniX = aniX + scaledX; // es lo mismo que poner aniX += scaledX y suma la x para animar en cada loop
                aniY += scaledY; //
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
