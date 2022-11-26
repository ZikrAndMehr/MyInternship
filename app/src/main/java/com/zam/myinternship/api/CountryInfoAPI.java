package com.zam.myinternship.api;

import com.zam.myinternship.model.CountryInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CountryInfoAPI {
    @GET
    Call<ArrayList<CountryInfo>> getCountryInfo(@Url String url);
}
