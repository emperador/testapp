package ml.marcoaponte.practiceapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by marco on 22/01/15.
 */
public class ourViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url); // CREAMOS ESTE WEBVIEW PARA SOBREESCRIBIR EL OTRO QUE ABRE UN INTENT CON EL NAVEGADOR
        // DEL SMARTPHONE PERO QUEREMOS QUE ABRA TODO EN NUESTRO NAVEGADOR
        return true;
    }
}
