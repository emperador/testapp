package ml.marcoaponte.practiceapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by marco on 19/01/15.
 */
public class Tabs extends Activity implements View.OnClickListener {

    TabHost th;
    TextView showResults;
    long start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button bStart = (Button) findViewById(R.id.bStartWatch);
        Button bStop = (Button) findViewById(R.id.bStopWatch);
        showResults = (TextView) findViewById(R.id.tvShowResults);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        newTab.setOnClickListener(this);

        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Pestaña 2");
        th.addTab(specs);
        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a tab");
        th.addTab(specs);

        start = 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAddTab: // CON ESTO SE CREA UNA NUEVA PESTAÑA EN JAVA
                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory(){

                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("Creaste  una nueva pestaña locoh!");
                        return text; // agrega el TextView a la nueva pestaña
                    }
                });
                ourSpec.setIndicator("New"); // este sería el Title de tu pestaña
                th.addTab(ourSpec); // se agrega el nuevo tab al tabHost
                break;
            case R.id.bStartWatch:
                start = System.currentTimeMillis(); //se obtiene el tiempo en milisec. del sistema
                break;
            case R.id.bStopWatch: // podes usar para calcular la velocidad por ejemplo
                stop = System.currentTimeMillis();
                if(start != 0){
                    Date red = new Date(stop-start);// se puede usar tambiién en vez de ese cálculo
                    long result = stop - start;
                    int millis = (int) result;
                    int seconds = (int) result / 1000; // formateamos en minutos, segundos y milisegundos
                    int minutes = seconds / 60;
                    millis = millis % 1000; // hacemos el resto para que no aparezca 3210 milisegundos y segundos
                    seconds = seconds % 60;
                    showResults.setText(String.format("%d:%02d:%02d",minutes,seconds,millis)); // formatea la cadena como si fuera la hora
                }
                break;
        }
    }
}
