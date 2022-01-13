package com.tictoccroc.subway_app.model

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable{

    companion object CREATOR : Parcelable.Creator<SubwayLine?>{
        override fun createFromParcel(p0: Parcel?): SubwayLine? {
            return SubwayLine();
        }

        override fun newArray(p0: Int): Array<SubwayLine?> {
            return arrayOfNulls(p0);
        }
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        val parcel = p0!!;
        parcel.writeInt(idx);
        parcel.writeString(name);
        parcel.writeString(sub_name)
        parcel.writeString(color_code)
    }
}