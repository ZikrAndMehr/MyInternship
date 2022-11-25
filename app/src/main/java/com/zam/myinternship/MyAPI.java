package com.zam.myinternship;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {
    String BASE_URL="https://random-data-api.com/api/";

    @GET("address/random_address?size=10")
    Call<List<RandomAddress>> getRandomAddresses();
}
