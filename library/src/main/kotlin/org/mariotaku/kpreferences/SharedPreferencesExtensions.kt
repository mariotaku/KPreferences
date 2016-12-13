package org.mariotaku.kpreferences

import android.content.SharedPreferences

/**
 * Created by mariotaku on 2016/12/13.
 */
operator fun <T> SharedPreferences.get(key: KPreferenceKey<T>): T {
    return key.read(this)
}

operator fun <T> SharedPreferences.set(key: KPreferenceKey<T>, value: T) {
    val editor = this.edit()
    if (key.write(editor, value)) {
        editor.apply()
    }
}

operator fun <T> SharedPreferences.Editor.set(key: KPreferenceKey<T>, value: T) {
    key.write(this, value)
}