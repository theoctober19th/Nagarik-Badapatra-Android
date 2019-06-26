package com.smithereens.nagarikbadapatra.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static String IP_ADDRESS = "10.5.50.158";
    private static String PORT_NUMBER = "8000";
    private static String ROOT_DIRECTORY = "nagarikbadapatra";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setIpAddress(String ipaddress){
        IP_ADDRESS = ipaddress;
    }

    public static String getBaseUrl(){
        return "https://" + IP_ADDRESS + ":" + PORT_NUMBER + "/" + ROOT_DIRECTORY + "/";
    }
}