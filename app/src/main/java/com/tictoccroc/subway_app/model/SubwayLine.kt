package com.tictoccroc.subway_app.model

import com.google.gson.annotations.SerializedName

class SubwayLine(
    @SerializedName("idx")
    val idx:Int = 0,

    @SerializedName("name")
    val name:String="",

    @SerializedName("sub_name")
    val sub_name:String = "",

    @SerializedName("color_code")
    val color_code:String = ""
)