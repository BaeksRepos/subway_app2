package com.tictoccroc.subway_app.webService

import com.tictoccroc.subway_app.model.Subway
import retrofit2.Call
import retrofit2.http.GET


interface SubWayWebInterface{
    @GET("/v1/filter/subway/version/1")
    fun searchSubWay() : Call<Subway>

}

object SubwayService{
    private val baseURL = "https://teacher.tictoccroc-devtest.com";
    val webClient = BaseService().getWebClient(baseURL).create(SubWayWebInterface::class.java);
}