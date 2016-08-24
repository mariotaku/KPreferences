package org.mariotaku.kpreferences

import android.content.SharedPreferences

/**
 * Created by mariotaku on 16/8/24.
 */
interface KPreferenceKey<T> {
    fun write(editor: SharedPreferences.Editor, value: T): Boolean

    fun read(preferences: SharedPreferences): T

    fun contains(preferences: SharedPreferences): Boolean
}