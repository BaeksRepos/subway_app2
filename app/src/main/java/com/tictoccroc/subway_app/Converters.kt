package com.tictoccroc.subway_app

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tictoccroc.subway_app.model.SubwayLine

class Converters {
    @TypeConverter
    fun arrayListToJson(value:ArrayList<Int>) = Gson().toJson(value);

    @TypeConverter
    fun jsonToArrayLit(value:String) = Gson().fromJson(value, Array<Int>::class.java).toList();

    @TypeConverter
    fun lineListToJson(value:ArrayList<SubwayLine>) = Gson().toJson(value);

    @TypeConverter
    fun jsonToLineList(value:String) = Gson().fromJson(value, Array<SubwayLine>::class.java).toList();
}