package com.tictoccroc.subway_app.listener
import android.view.View
import androidx.navigation.Navigation
import com.tictoccroc.subway_app.Converters
import com.tictoccroc.subway_app.R
import com.tictoccroc.subway_app.adapter.ResultAdapter
import com.tictoccroc.subway_app.database.BaseDatabase
import com.tictoccroc.subway_app.entitiy.SubwayDAO
import com.tictoccroc.subway_app.entitiy.SubwayLineEntity
import com.tictoccroc.subway_app.entitiy.SubwayStationEntity
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation


class SearchClickListstener(view: View, subwayDAO: SubwayDAO) : StationListClickListener {
    private val _view = view;
    private val _subwayDAO = subwayDAO;
    override fun onStationListClick(item: SubwayStation,   position: Int,  lines: ArrayList<SubwayLine>) {
        Thread{
            val jsonStr = Converters().arrayListToJson(item.subwayLines);
            val station = SubwayStationEntity(item.idx, item.name, jsonStr, "Y");

            val listStr = Converters().lineListToJson(lines);
            val line = SubwayLineEntity(1, listStr);

            _subwayDAO.addSubwayStation(station)
            _subwayDAO.addSubwayLine(line);
        }.start()

        Navigation.findNavController(_view).navigate(R.id.action_searchViewFragment_to_mainViewFragment);
    }
}