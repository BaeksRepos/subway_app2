package com.tictoccroc.subway_app.repository

import android.content.Context
import com.tictoccroc.subway_app.entitiy.SubwayRoomDatabase
import com.tictoccroc.subway_app.webService.SubwayService

class SubwayRepository(context: Context) {
    private val webClient = SubwayService.webClient;
    private val databaseInstance = SubwayRoomDatabase(context).databaseInstance;

    fun searchSubwayList() = webClient.searchSubWay();

    //fun selectSubwayList() = databaseInstance!!.subwayDAO().getSubway();
}