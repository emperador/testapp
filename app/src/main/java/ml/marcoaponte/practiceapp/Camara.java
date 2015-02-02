package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by marco on 26/12/14.
 */
public class Camara extends Activity implements View.OnClickListener {
    ImageButton ib;
    ImageView iv;
    Button b;
    Intent i;
    final static int cameraData = 0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        inicializarVariables();
        InputStream is = getResources().openRawResource(R.raw.ic_launcher); //toma el input stream
        bmp = BitmapFactory.decodeStream(is); // decodifica y le asigna al bitmap
    }

    private void inicializarVariables() { // inicializa las variables
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        iv = (ImageView) findViewById(R.id.ivReturnedPic);
        b = (Button) findViewById(R.id.bSetWall);
        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSetWall:
                try {
                    getApplicationContext().setWallpaper(bmp); // setea el wallpaper
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.ibTakePic:
                i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras(); // obtiene los extras
            bmp = (Bitmap) extras.get("data"); // obtiene la imagen de la camara
            iv.setImageBitmap(bmp); //setea la imagen obtenida en el image view
        }
    }
}
