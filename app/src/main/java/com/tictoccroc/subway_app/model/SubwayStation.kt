package com.tictoccroc.subway_app.model

import com.google.gson.annotations.SerializedName

class SubwayStation(
    @SerializedName("idx")
    val idx:Int = 0,
    @SerializedName("name")
    val name:String = "",
    @SerializedName("subway_lines")
    val subwayLines:ArrayList<Int>
)
