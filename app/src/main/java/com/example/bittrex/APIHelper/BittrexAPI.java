package com.example.bittrex.APIHelper;

import com.example.bittrex.Model.BittrexPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BittrexAPI {

    @GET("public/getcurrencies")
    Call<BittrexPOJO> getData();
}
