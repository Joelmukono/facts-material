package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.myapplication.utils.Constants.KEY_API_TOKEN;
import static com.example.myapplication.utils.Constants.KEY_LOGIN_STATE;

public class FactsPreferences {
    private static SharedPreferences preferences;

    public FactsPreferences(Context context) {
        preferences = context.getSharedPreferences("facts-africa", Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_LOGIN_STATE, false);
    }

    public void setLoggedIn(boolean loggedIn) {
        preferences.edit().putBoolean(KEY_LOGIN_STATE, loggedIn).apply();
    }

    public void setApiToken(String token) {
        preferences.edit().putString(KEY_API_TOKEN, token).apply();
    }

    public static String getApiToken() {
        String token = preferences.getString(KEY_API_TOKEN, "");
        return "Bearer " +token;
    }
}
