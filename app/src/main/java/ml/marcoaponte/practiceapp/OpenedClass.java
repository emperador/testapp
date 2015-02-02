package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by marco on 29/12/14.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView pregunta, prueba;
    Button retornaDatos;
    RadioGroup seleccionado;
    String gotBread, setData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        inicializarVariables();

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String editText = getData.getString("name","Marco está...");
        String values = getData.getString("list","4");
        if(values.contentEquals("1")){
            pregunta.setText(editText);
        }

/*      tenes que comentar porque o sino va a dar un error, porque lees el bundle (?) WTF?? siiii,
        porque al llamarle desde el startActivityForResult no le pasas ningún extra
        que él lee en la siguiente linea y ahi te va a dar un error
        Bundle gotBasket = getIntent().getExtras();
        gotBread = gotBasket.getString("key");
        pregunta.setText(gotBread);*/
    }

    private void inicializarVariables() {
        pregunta = (TextView) findViewById(R.id.tvQuestion);
        prueba = (TextView) findViewById(R.id.tvText);
        retornaDatos = (Button) findViewById(R.id.bVolver);
        retornaDatos.setOnClickListener(this);
        seleccionado = (RadioGroup) findViewById(R.id.rgAnswers); // será que hace falta linkear? CLAARO QUE SI
        seleccionado.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) { // es ell onclick de "retornaDatos" que es el boton volver
        Intent person = new Intent(); // porque el intent es como la persona que lleva datos entre activities, el "pan"
        Bundle backpack = new Bundle(); // porque es como el contenedor o mochila que lleva la persona (intent)
        backpack.putString("answer", setData); // se le coloca el string en el bundle
        person.putExtras(backpack); // se guarda el bundle como extra
        setResult(RESULT_OK, person); //se setea el resultado del ACTIVITY FOR RESULT
        finish(); // le mata al activity
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbLoco:
                setData = "Probablemente";
                break;
            case R.id.rbSexy:
                setData = "Definitivamente";
                break;
            case R.id.rbBoth:
                setData = "Fuck yeah";
                break;
        }
        prueba.setText(setData); // se setea el texto de acuerdo al radioB que se tocó
    }
}
