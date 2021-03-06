# KPreferences

Better SharedPreferences wrapper, like [SwiftyUserDefaults](https://github.com/radex/SwiftyUserDefaults)

---

[![](https://jitpack.io/v/mariotaku/KPreferences.svg)](https://jitpack.io/#mariotaku/KPreferences)

KPreferences is a Kotlin wrapper for Android SharedPreferences, to make it simpler and safer (avoiding null problems at compile time thanks to Kotlin)

## Features

### Get value

````kotlin
val stringPrefs = KStringKey("name", "def")
val str = prefs[stringPrefs] // This is a string
val intPrefs = KIntKey("name", 1)
val int = prefs[stringPrefs] // This is an integer
````

### Set value

````kotlin
val stringPrefs = KStringKey("name", "def")
prefs[stringPrefs] = "foo" // Calls SharedPreferences.Editor#apply() immediately
prefs.edit {
    this[stringPrefs] = "bar"
} // Calls SharedPreferences.Editor#apply() after block executed
````

### Check existence

````kotlin
val stringPrefs = KStringKey("name", "def")
if (stringPrefs in prefs) {
    // Check if SharedPreferences contains this key
}
if ("keyString" in prefs) {
    // Check if SharedPreferences contains this key
}
````

### Nullable value

````kotlin
val nullableStringPrefs = KNullableStringKey("name", null)
val str = prefs[nullableStringPrefs] // This is a nullable string
````

### Custom type

````kotlin
class KDateKey(key: String, def: Date) : KSimpleKey<Date>(key, def) {
    override fun write(editor: SharedPreferences.Editor, value: Date): Boolean {
        editor.putLong(key, value.time)
        return true
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

## License

KPreferences is available under the Apache license, version 2. See the LICENSE file for more info.


## Donation

**Donation methods**

PayPal & AliPay: `val email = "mariotaku.lee@gmail.com"`

Bitcoin: `1Ag37rPeVUKPHZa6RrsnbkCCz1Envx8xxZ`

Buy me a ~~bread~~ [game](http://steamcommunity.com/id/mariotaku/wishlist) or anything you want :)

[帮我支付宝账户里随便加点钱](https://twitter.com/xmxsuperstar/status/724094631621750785)

---

Made with ❤️ by <a href="https://mariotaku.org/"><img src="resources/nyan_sakamoto_icon.png" height="20"></a>
