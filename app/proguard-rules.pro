# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.google.android.material.**
-dontwarn com.google.android.material.*
-keep class com.google.android.material.bottomappbar.BottomAppBar
-dontwarn com.google.android.material.bottomappbar.BottomAppBar
-keep class com.google.android.material.bottomnavigation.BottomNavigationView
-dontwarn com.google.android.material.bottomnavigation.BottomNavigationView
-keep class com.google.android.material.navigation.NavigationView
-dontwarn com.google.android.material.navigation.NavigationView
-keep class com.google.android.material.navigationrail.NavigationRailView
-dontwarn com.google.android.material.navigationrail.NavigationRailView
-keep class com.google.android.material.tabs.TabItem
-dontwarn com.google.android.material.tabs.TabItem
-keep class com.google.android.material.textfield.TextInputLayout
-dontwarn com.google.android.material.textfield.TextInputLayout

# Lokalise proguard rules
# referenced by https://developers.lokalise.com/docs/android-sdk-v2?#step-3-include-lokalise-sdk-in-your-project
-keep class com.lokalise.** { *; }
-dontwarn com.lokalise.*
-keep @interface io.realm.annotations.RealmModule { *; }
-keep class io.realm.annotations.RealmModule { *; }

# Firebase Crashlytics rules
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception

-keep class com.google.firebase.crashlytics.** { *; }
-dontwarn com.google.firebase.crashlytics.*
