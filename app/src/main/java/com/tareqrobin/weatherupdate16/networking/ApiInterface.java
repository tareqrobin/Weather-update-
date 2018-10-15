package com.tareqrobin.weatherupdate16.networking;

import com.tareqrobin.weatherupdate16.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("forecast/17e9b0d3fa5be1ac4ffabdd36e2c02e6/24.7471,90.4203")
    Call<Model> getsubjectData();
}
