package com.tictoccroc.subway_app.listener

import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation

interface StationListClickListener {
    fun onStationListClick(item:SubwayStation, position:Int, lines:ArrayList<SubwayLine>);
}