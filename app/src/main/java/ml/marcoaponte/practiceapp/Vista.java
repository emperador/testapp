package ml.marcoaponte.practiceapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.graphics.Rect;
/**
 * Created by marco on 04/01/15.
 */
public class Vista extends View{

    Bitmap gBall;
    float changingY;
    Typeface font;

    public Vista(Context context) { // el constructor tiene el mismo nombre que la clase
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(), R.drawable.greenball); // toma del bitmap factory la pelota
        changingY = 0; // hace que no se mueva y
        font = Typeface.createFromAsset(context.getAssets(), "G-Unit.ttf"); // toma los assets
        // del context que recibe el constructor
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // al pintar primero la pelota y el rectangulo luego, el rectangulo que da encima
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE); //define el background color blanco

        Paint textPaint = new Paint();
        textPaint.setARGB(50, 254, 10, 50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        textPaint.setTypeface(font);
        canvas.drawText("mybringback", canvas.getWidth()/2, 200, textPaint);


        canvas.drawBitmap(gBall, canvas.getWidth()/2, changingY, null); //coloca la bola
        if(changingY < canvas.getHeight()){
            changingY += 10;
        } else{
            changingY = 0;
        }
        Rect middleRect = new Rect(); // creamos un rectangulo
        middleRect.set(0, 400, canvas.getWidth(),500); //establecemos el tamaño
        Paint ourBlue = new Paint(); // otra forma de pintar de azul
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);
        invalidate(); // hace que se repita el onDraw como si fuera un while
    }
}
