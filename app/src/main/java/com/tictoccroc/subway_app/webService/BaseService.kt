package com.tictoccroc.subway_app.webService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseService {
    fun getWebClient(baseURL:String) : Retrofit{
        val retrofitBuilder = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofitBuilder;
    }
}