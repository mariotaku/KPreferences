package org.mariotaku.kpreferences.sample

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.mariotaku.kpreferences.KIntKey
import org.mariotaku.kpreferences.KPreferences
import org.mariotaku.kpreferences.KStringKey

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prefs = KPreferences.get(this, "test", Context.MODE_PRIVATE)
        val stringPrefs = KStringKey("name", "def")
        val intPrefs = KIntKey("name", 1)
        prefs.edit {
            this[stringPrefs] = "foo"
            this[intPrefs] = 2
        }
        val str = prefs[stringPrefs]
        if (stringPrefs in prefs) {

        }
    }
}
