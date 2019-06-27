package com.smithereens.nagarikbadapatra.api;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.smithereens.nagarikbadapatra.MyApplication;
import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.custom.TinyDB;
import com.smithereens.nagarikbadapatra.custom.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    private static Retrofit retrofit;
   // public static String IP_ADDRESS = "192.168.1.100";
    private static String PORT_NUMBER = "8080";
    private static String ROOT_DIRECTORY = "badapatra";
    private static Context mContext;

    public static Retrofit getRetrofitInstance(String ipaddress) {
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(getBaseUrl(ipaddress))
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static String getBaseUrl(String ipaddress){
        String s =  "http://" + ipaddress + ":" + PORT_NUMBER + "/" + ROOT_DIRECTORY + "/";
        Log.d("MYAPI", s);
        return s;
    }
}