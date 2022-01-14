package com.tictoccroc.subway_app.entitiy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.tictoccroc.subway_app.database.BaseDatabase
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation

@Dao
interface SubwayDAO {
    @Query("SELECT * FROM SubwayStation where useYn='Y'")
    fun getSubwayStations() : MutableList<SubwayStationEntity>


    @Query("SELECT * FROM SubwayLine")
    fun getSubwayLines() : MutableList<SubwayLineEntity>

    @Query("DELETE FROM SubwayStation")
    fun deleteAllSubwayStation()

    @Query("DELETE FROM SubwayStation WHERE idx=:idx")
    fun deleteIdxSubwayStation(idx:Int);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubwayStation(station:SubwayStationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubwayLine(line:SubwayLineEntity)
}

class SubwayRoomDatabase(context: Context){
    val databaseInstance = BaseDatabase.createRoomDatabase(context);
}