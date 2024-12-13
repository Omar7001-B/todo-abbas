package com.omar.todoabbas.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREF_NAME = "TodoPreferences";
    private static final String KEY_DARK_MODE = "dark_mode";

    private final SharedPreferences preferences;

    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setDarkMode(boolean isDarkMode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_DARK_MODE, isDarkMode);
        editor.apply();
    }

    public boolean isDarkMode() {
        return preferences.getBoolean(KEY_DARK_MODE, false);
    }
} 