package com.tictoccroc.subway_app.repository

import com.tictoccroc.subway_app.webService.SubwayService

class SubwayRepository {
    private val webClient = SubwayService.webClient;
    fun searchSubwayList() = webClient.searchSubWay();
}