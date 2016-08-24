package org.mariotaku.kpreferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by mariotaku on 16/8/24.
 */
class KPreferences(internal val preferences: SharedPreferences) {
    operator fun <T> get(key: KPreferenceKey<T>): T {
        return key.read(preferences)
    }

    operator fun <T> set(key: KPreferenceKey<T>, value: T) {
        val editor = preferences.edit()
        key.write(editor, value)
        editor.apply()
    }

    operator fun <T> contains(key: KPreferenceKey<T>): Boolean {
        return key.contains(preferences)
    }

    operator fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    fun edit(action: Editor.() -> Unit) {
        val editor = Editor(preferences.edit())
        action(editor)
        editor.apply()
    }

    class Editor(internal val editor: SharedPreferences.Editor) {

        operator fun <T> set(key: KPreferenceKey<T>, value: T) {
            key.write(editor, value)
        }

        fun apply() {
            editor.apply()
        }
    }

    companion object {
        fun get(context: Context, name: String, mode: Int): KPreferences {
            return KPreferences(context.getSharedPreferences(name, mode))
        }
    }
}