package org.mariotaku.kpreferences

import android.content.SharedPreferences

/**
 * Created by mariotaku on 16/8/24.
 */
abstract class KSimpleKey<T>(val key: String, val def: T) : KPreferenceKey<T> {

    override fun contains(preferences: SharedPreferences): Boolean {
        return preferences.contains(key)
    }

}

class KBooleanKey(key: String, def: Boolean) : KSimpleKey<Boolean>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: Boolean): Boolean {
        editor.putBoolean(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): Boolean {
        return preferences.getBoolean(key, def)
    }

}

class KIntKey(key: String, def: Int) : KSimpleKey<Int>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: Int): Boolean {
        editor.putInt(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): Int {
        return preferences.getInt(key, def)
    }

}

class KLongKey(key: String, def: Long) : KSimpleKey<Long>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: Long): Boolean {
        editor.putLong(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): Long {
        return preferences.getLong(key, def)
    }

}

class KFloatKey(key: String, def: Float) : KSimpleKey<Float>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: Float): Boolean {
        editor.putFloat(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): Float {
        return preferences.getFloat(key, def)
    }
}

class KStringKey(key: String, def: String) : KSimpleKey<String>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: String): Boolean {
        editor.putString(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): String {
        return preferences.getString(key, def) ?: def
    }
}

class KNullableStringKey(key: String, def: String?) : KSimpleKey<String?>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: String?): Boolean {
        editor.putString(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): String? {
        return preferences.getString(key, def)
    }
}

class KStringSetKey(key: String, def: Set<String>) : KSimpleKey<Set<String>>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: Set<String>): Boolean {
        editor.putStringSet(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): Set<String> {
        return preferences.getStringSet(key, def) ?: def
    }
}

class KNullableStringSetKey(key: String, def: Set<String>?) : KSimpleKey<Set<String>?>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: Set<String>?): Boolean {
        editor.putStringSet(key, value)
        return true
    }

    override fun read(preferences: SharedPreferences): Set<String>? {
        return preferences.getStringSet(key, def)
    }
}