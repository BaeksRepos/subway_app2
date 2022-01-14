package com.tictoccroc.subway_app.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tictoccroc.subway_app.Converters
import com.tictoccroc.subway_app.entitiy.SubwayDAO
import com.tictoccroc.subway_app.entitiy.SubwayLineEntity
import com.tictoccroc.subway_app.entitiy.SubwayStationEntity


@androidx.room.Database(entities = [SubwayStationEntity::class, SubwayLineEntity::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BaseDatabase : RoomDatabase() {

    abstract fun subwayDAO() : SubwayDAO;
    companion object{
        private var database_instance:BaseDatabase? = null;

        fun createRoomDatabase(context: Context) : BaseDatabase?{
            if(database_instance == null){
                database_instance = Room.databaseBuilder(context.applicationContext, BaseDatabase::class.java, "Subway-Database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            }

            return database_instance;
        }
    }
}