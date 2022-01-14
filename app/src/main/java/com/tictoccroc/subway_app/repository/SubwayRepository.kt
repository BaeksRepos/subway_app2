package com.tictoccroc.subway_app.repository

import android.content.Context
import com.tictoccroc.subway_app.entitiy.SubwayLineEntity
import com.tictoccroc.subway_app.entitiy.SubwayRoomDatabase
import com.tictoccroc.subway_app.entitiy.SubwayStationEntity
import com.tictoccroc.subway_app.model.SubwayStation
import com.tictoccroc.subway_app.webService.SubwayService

// REST API, DATABASE 별 분리 클래스
class SubwayRepository(context: Context) {
    private val webClient = SubwayService.webClient;
    private val databaseInstance = SubwayRoomDatabase(context).databaseInstance;

    // rest apI
    fun searchSubwayList() = webClient.searchSubWay();

    // database
    fun selectSubwayStation() = databaseInstance!!.subwayDAO().getSubwayStations();
    fun selectSubwayLine() = databaseInstance!!.subwayDAO().getSubwayLines();

    fun insertSubwayStation(station:SubwayStationEntity) = databaseInstance!!.subwayDAO().addSubwayStation(station);
    fun insertSubwayLine(line:SubwayLineEntity) = databaseInstance!!.subwayDAO().addSubwayLine(line);

    fun deleteAllSubwayStation() = databaseInstance!!.subwayDAO().deleteAllSubwayStation();
    fun deleteIdxSubwayStation(idx:Int) = databaseInstance!!.subwayDAO().deleteIdxSubwayStation(idx)
}