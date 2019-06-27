package com.smithereens.nagarikbadapatra.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smithereens.nagarikbadapatra.entities.Office;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPrefHelper {

    public static void saveArrayListOffice(ArrayList<Office> list, String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public static ArrayList<Office> getArrayListOffice(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<ArrayList<Office>>() {}.getType();
        Log.d("MYAPI", json);
        return gson.fromJson( json , type);
    }
}
