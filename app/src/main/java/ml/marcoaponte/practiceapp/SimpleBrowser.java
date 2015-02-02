package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by marco on 22/01/15.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {

    EditText url;
    WebView ourBrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrow = (WebView) findViewById(R.id.wvBrowser);

        ourBrow.getSettings().setJavaScriptEnabled(true); // habilita el JavaScript para Youtube por ejemplo
        ourBrow.getSettings().setLoadWithOverviewMode(true); // hace que el zoom sea mínimo
        ourBrow.getSettings().setUseWideViewPort(true); // te muestra como si fuera un navegador normal
        ourBrow.setWebViewClient(new ourViewClient());

        try{
            ourBrow.loadUrl("http://marcoaponte.ml"); // carga la URL entre comillas
        } catch (Exception e){
            e.printStackTrace();
        }

        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button forward = (Button) findViewById(R.id.bForward);
        Button clearHistory = (Button) findViewById(R.id.bHistory);
        url = (EditText) findViewById(R.id.etURL);
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                ourBrow.loadUrl(theWebsite); // carga la página del editText
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // si queremos que se vea desde todas las clases, definimos arriba y listo
                imm.hideSoftInputFromWindow(url.getWindowToken(),0); // esconde el teclado despues de apretar el boton
                break;
            case R.id.bBack:
                if(ourBrow.canGoBack()) // chequea si puede ir atras
                ourBrow.goBack(); // vá atrás
                break;
            case R.id.bForward:
                if(ourBrow.canGoForward()) // chequea si puede ir hacia adelante
                    ourBrow.goForward(); // va hacia adelante
                break;
            case R.id.bRefresh:
                ourBrow.reload(); // recarga la página
                break;
            case R.id.bHistory:
                ourBrow.clearHistory(); // borra el historial
                break;
        }
    }

}
