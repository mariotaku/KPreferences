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
        if (key.write(editor, value)) {
            editor.apply()
        }
    }

    operator fun <T> contains(key: KPreferenceKey<T>): Boolean {
        return key.contains(preferences)
    }

    operator fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    fun edit(action: Editor.() -> Unit): Boolean {
        val editor = Editor(preferences.edit())
        action(editor)
        return editor.apply()
    }

    class Editor(internal val editor: SharedPreferences.Editor) {

        internal var cancelled: Boolean = false
        internal var changed: Boolean = false

        operator fun <T> set(key: KPreferenceKey<T>, value: T) {
            key.write(editor, value)
            changed = true
        }

        fun apply(): Boolean {
            if (cancelled) return false
            editor.apply()
            return changed
        }

        fun cancel() {
            cancelled = true
        }
    }

    companion object {
        fun get(context: Context, name: String, mode: Int): KPreferences {
            return KPreferences(context.getSharedPreferences(name, mode))
        }
    }
}