package com.tictoccroc.subway_app.entitiy

import android.content.Context
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tictoccroc.subway_app.database.BaseDatabase
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation

@Dao
interface SubwayDAO {
    @Query("SELECT * FROM SubwayStation")
    fun getSubwayStations() : MutableList<SubwayStationEntity>

    @Query("SELECT * FROM SubwayLine")
    fun getSubwayLines() : MutableList<SubwayLineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubwayStation(station:SubwayStationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubwayLine(line:SubwayLineEntity)


}

class SubwayRoomDatabase(context: Context){
    val databaseInstance = BaseDatabase.createRoomDatabase(context);
}