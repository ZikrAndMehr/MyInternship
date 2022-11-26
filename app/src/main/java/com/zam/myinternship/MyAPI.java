package com.zam.myinternship;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface MyAPI {
    @GET
    Call<ArrayList<RandomAddress>> getRandomAddresses(@Url String url);
}
