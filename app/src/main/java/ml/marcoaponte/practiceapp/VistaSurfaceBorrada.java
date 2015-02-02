package ml.marcoaponte.practiceapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** metimos esta clase dentro de la otra para poder usar sus variables
 * Created by marco on 13/01/15.
 */
public class VistaSurfaceBorrada extends SurfaceView implements Runnable{

    SurfaceHolder ourHolder;
    Thread ourThread = null;
    boolean isRunning = false;
    private float x, y = 0;

    public VistaSurfaceBorrada(Context context) {
        super(context);
        ourHolder = getHolder(); // obtenemos un holder para utilizar sus métodos
/*        ourThread = new Thread(this); // se le pasa el contexto que es this
        ourThread.start();    sacamos del constructor y ponemos en resume*/
    }

    public void pause(){
        isRunning = false; // se setea a  false variable bandera
        while (true){
            try {
                ourThread.join(); // que se bloquee el thread hasta que el receptor termine su ejecución
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        ourThread = null;
    }

    public void resume(){
        isRunning = true; // se setea a true variable bandera
        ourThread = new Thread(this); // se le pasa el contexto que es this, creando el Thread
        ourThread.start();
    }
    @Override
    public void run() {
        while (isRunning){
            if (!ourHolder.getSurface().isValid()) // si es inválida la superficie, continuar
                continue;

            Canvas canvas = ourHolder.lockCanvas(); // bloqueas el canvas
            canvas.drawRGB(02, 2, 150);
            if (x != 0 && y != 0){
                Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
                canvas.drawBitmap(test, getEquis()-test.getWidth()/2, getYGriega()-test.getHeight()/2,null);
            }
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private float getEquis() {
        return x;
    }

    private float getYGriega() {
        return y;
    }

    public void setEquis(float eventX) {
        x = eventX;
    }

    public void setYGriega(float eventY) {
        y = eventY;
    }
}
