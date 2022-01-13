package com.tictoccroc.subway_app.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class SubwayStation (
    @SerializedName("idx")
    val idx:Int = 0,
    @SerializedName("name")
    val name:String = "",
    @SerializedName("subway_lines")
    val subwayLines:ArrayList<Int> = ArrayList<Int>()
) : Parcelable{

    companion object CREATOR : Parcelable.Creator<SubwayStation?>{
        override fun createFromParcel(p0: Parcel?): SubwayStation? {
            return SubwayStation();
        }

        override fun newArray(p0: Int): Array<SubwayStation?> {
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
        parcel.writeIntArray(subwayLines as IntArray)
    }
}