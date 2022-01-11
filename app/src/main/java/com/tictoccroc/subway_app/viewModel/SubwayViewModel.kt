package com.tictoccroc.subway_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tictoccroc.subway_app.model.Subway
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation
import com.tictoccroc.subway_app.repository.SubwayRepository
import retrofit2.Callback
import retrofit2.Response

class SubwayViewModel(subwayRepos:SubwayRepository) : ViewModel() {
    private val _subwayRepos = subwayRepos;

    val subwayLiveData = MutableLiveData<Subway>();
    fun requestSearchSubway(){
        _subwayRepos.searchSubwayList().enqueue(object : Callback<Subway> {
            override fun onFailure(call: retrofit2.Call<Subway>, t: Throwable) {
                TODO("Not yet implemented")
            }
            override fun onResponse(call: retrofit2.Call<Subway>, response: Response<Subway>) {
                if(response.code() == 200){
                    val subwayInfo = response.body() as Subway;
                    subwayLiveData.postValue(subwayInfo);
                }
            }
        })
    }

}