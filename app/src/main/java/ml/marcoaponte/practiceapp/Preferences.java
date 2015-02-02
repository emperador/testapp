package ml.marcoaponte.practiceapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by marco on 04/01/15.
 */
public class Preferences extends PreferenceActivity { // extiende activity especial para preferences
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
