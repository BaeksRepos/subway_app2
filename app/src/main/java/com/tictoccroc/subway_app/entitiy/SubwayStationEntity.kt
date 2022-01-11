package com.tictoccroc.subway_app.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subway_station")
data class SubwayStationEntity(
    @PrimaryKey()
    val idx:Int,
    val name:String

)