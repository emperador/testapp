package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by marco on 29/12/14.
 */
public class Datos extends Activity implements View.OnClickListener{
    Button start, startFor;
    EditText sendET;
    TextView gotAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        inicializarVariables();
    }

    private void inicializarVariables() {
        start = (Button) findViewById(R.id.bSA);
        startFor = (Button) findViewById(R.id.bSAFR);
        sendET = (EditText) findViewById(R.id.etSend);
        gotAnswer = (TextView) findViewById(R.id.tvGot);
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSA:
                String bread = sendET.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("key", bread);
                Intent a = new Intent(Datos.this, OpenedClass.class); // se le pone el contexto Datos.this y la clase que queres abrir
                a.putExtras(basket); // se le coloca como extra el bundle basket
                startActivity(a); // se inicia el intent a
                break;
            case R.id.bSAFR:
                Intent i = new Intent(Datos.this, OpenedClass.class);
                startActivityForResult(i, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle basket = data.getExtras(); //
            String s = basket.getString("answer"); //
            gotAnswer.setText(s); //
        }
    }
}
