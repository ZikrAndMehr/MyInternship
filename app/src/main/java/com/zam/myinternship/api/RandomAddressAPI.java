package com.zam.myinternship.api;

import com.zam.myinternship.model.RandomAddress;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RandomAddressAPI {
    @GET
    Call<ArrayList<RandomAddress>> getRandomAddresses(@Url String url);
}
