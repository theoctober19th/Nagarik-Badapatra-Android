package com.smithereens.nagarikbadapatra.api;

import com.smithereens.nagarikbadapatra.entities.Information;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDataService {

    @GET("info/getall")
    Call<List<Information>> getAllInfoOfOffice();
}
