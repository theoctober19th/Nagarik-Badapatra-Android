package com.smithereens.nagarikbadapatra.api;

import com.smithereens.nagarikbadapatra.entities.Information;
import com.smithereens.nagarikbadapatra.entities.Office;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiDataService {

    @GET("info/getall")
    Call<List<Information>> getAllInfoOfOffice(@Query("id")String officeID);

    @GET("office/getinfo")
    Call<Office> getOfficeDetail(@Query("id")String officeID);

    @GET("office/getall")
    Call<List<Office>> getAllOffice();

}
