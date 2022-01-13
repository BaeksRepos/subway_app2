package com.tictoccroc.subway_app.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SubwayLine")
data class SubwayLineEntity(
    @PrimaryKey
    val idx:Int,
    var lineJson:String
)