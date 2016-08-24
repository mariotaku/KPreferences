package org.mariotaku.kpreferences.sample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.mariotaku.kpreferences.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val prefs: KPreferences by lazy { KPreferences.get(this, "test", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getValue()
        setValue()
        checkExistence()
        nullableValue()
        customType()
    }

    fun getValue() {
        val stringPrefs = KStringKey("name", "def")
        val str = prefs[stringPrefs] // This is a string
        val intPrefs = KIntKey("name", 1)
        val int = prefs[stringPrefs] // This is an integer
    }

    fun setValue() {
        val stringPrefs = KStringKey("name", "def")
        prefs[stringPrefs] = "foo" // Calls SharedPreferences.Editor#apply() immediately
        prefs.edit {
            this[stringPrefs] = "bar"
        } // Calls SharedPreferences.Editor#apply() after block executed
    }

    fun checkExistence() {
        val stringPrefs = KStringKey("name", "def")
        if (stringPrefs in prefs) {
            // Check if SharedPreferences contains this key
        }
        if ("keyString" in prefs) {
            // Check if SharedPreferences contains this key
        }
    }

    fun nullableValue() {
        val nullableStringPrefs = KNullableStringKey("name", null)
        val str = prefs[nullableStringPrefs] // This is a nullable string
    }

    fun customType() {
        class KDateKey(key: String, def: Date) : KSimpleKey<Date>(key, def) {
            override fun write(editor: SharedPreferences.Editor, value: Date) {
                editor.putLong(key, value.time)
            }

            override fun read(preferences: SharedPreferences): Date {
                return Date(preferences.getLong(key, def.time))
            }

        }

        val datePrefs = KDateKey("name", Date())
        val date = prefs[datePrefs] // This is a date object
    }
}
