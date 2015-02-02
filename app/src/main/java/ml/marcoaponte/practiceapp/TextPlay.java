package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by marco on 22/12/14.
 */
public class TextPlay extends Activity implements View.OnClickListener{
    Button chkCmd;
    EditText input;
    TextView display;
    ToggleButton passTog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text); //setea el contView al xml "text"

        linkearVariables();
        passTog.setOnClickListener(this/*new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }*/);
        // cambiamos el onclickListener que tenía en cada uno por el que implementa nuestra clase
        // y lo que estaba dentro del onClick se pone en un switch para que sea más ordenado
        chkCmd.setOnClickListener(/*new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
            }*/this);
    }

    private void linkearVariables() {
        chkCmd = (Button) findViewById(R.id.bResults); // se hace referencia al id del botón en el xml
        passTog = (ToggleButton) findViewById(R.id.tbPassword); // se hace referencia al id del tog botón en el xml
        input = (EditText) findViewById(R.id.etCommands); // se hace referencia al id del edit text n en el xml
        display = (TextView) findViewById(R.id.tvResults); // se hace referencia al id del textview en el xml
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bResults:

                String check = input.getText().toString();
                if(check.contentEquals("left")){ // un if y else ifs para lo que se escriba en el editText
                    display.setGravity(Gravity.LEFT);
                } else if(check.contentEquals("right")){
                    display.setGravity(Gravity.RIGHT); // cambia alineación a izquierda derecha o centrado
                } else if(check.contentEquals("center")){
                    display.setGravity(Gravity.CENTER);
                }  else if(check.contentEquals("blue")){
                    display.setText("blue");
                    display.setTextColor(Color.BLUE); // cambia el color del texto
                    display.setGravity(Gravity.CENTER);
                } else if(check.contentEquals("WTF")){
                    Random crazy = new Random(); // crea variable random para asignar luego
                    display.setText("WTF!!!"); // setea el valor del Texto
                    display.setTextSize(crazy.nextInt(75)); // setea el tamaño del texto
                    display.setTextColor(Color.rgb(crazy.nextInt(256), crazy.nextInt(256), crazy.nextInt(256)));
                    display.setGravity(Gravity.CENTER);
                    switch (crazy.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setGravity(Gravity.RIGHT);
                            break;
                        case 2:
                            display.setTextColor(Color.BLUE);
                            break;
                    }
                } else{
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.RED);
                }
                break;

            case R.id.tbPassword:
                if(passTog.isChecked()){ //se ve si esta checkado o no, y se le cambia a passwd no se xq el |
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else{
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;

        }
    }
}
