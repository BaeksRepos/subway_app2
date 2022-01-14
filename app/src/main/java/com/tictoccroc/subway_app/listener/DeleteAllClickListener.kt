package com.tictoccroc.subway_app.listener

import android.view.View
import com.tictoccroc.subway_app.adapter.ResultAdapter
import com.tictoccroc.subway_app.model.SubwayStation
import com.tictoccroc.subway_app.viewModel.SubwayViewModel

// 전체 삭제 클릭 리스너
class DeleteAllClickListener(viewModel: SubwayViewModel, totalStationList:  ArrayList<SubwayStation>, adapter: ResultAdapter) : View.OnClickListener {
    private val _subwayViewModel = viewModel;
    private val _subwayList = totalStationList
    private val adapter = adapter;
    override fun onClick(p0: View?) {
        
        // 리스트에 출력된 전체 역정보 삭제
        _subwayList.clear();
        
        // 전체 역 정보 라이브데이터 삭제
        _subwayViewModel.stationLiveData.value!!.clear();
        
        // roomData 삭제
        _subwayViewModel.deleteAllSubwayStation();
        adapter.notifyDataSetChanged();
    }
}