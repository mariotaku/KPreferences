# KPreferences

Better SharedPreferences wrapper

---

[![](https://jitpack.io/v/mariotaku/KPreferences.svg)](https://jitpack.io/#mariotaku/KPreferences)

KPreferences is a Kotlin wrapper for Android SharedPreferences, to make it simpler and safer (avoiding null problems at compile time thanks to Kotlin)

## Features

### Get value

````
val stringPrefs = KStringKey("name", "def")
val str = prefs[stringPrefs] // This is a string
val intPrefs = KIntKey("name", 1)
val int = prefs[stringPrefs] // This is an integer
````

### Set value

````
val stringPrefs = KStringKey("name", "def")
prefs[stringPrefs] = "foo" // Calls SharedPreferences.Editor#apply() immediately
prefs.edit {
    this[stringPrefs] = "bar"
} // Calls SharedPreferences.Editor#apply() after block executed
````

### Check existence

````
val stringPrefs = KStringKey("name", "def")
if (stringPrefs in prefs) {
    // Check if SharedPreferences contains this key
}
if ("keyString" in prefs) {
    // Check if SharedPreferences contains this key
}
````

### Nullable value

````
val nullableStringPrefs = KNullableStringKey("name", null)
val str = prefs[nullableStringPrefs] // This is a nullable string
````

### Custom type

````
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
````

## Get it

Add JitPack repository in your build.gradle file

````
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
````

Add this library

````
dependencies {
    compile 'com.github.mariotaku:KPreferences:(latest version)'
}
````