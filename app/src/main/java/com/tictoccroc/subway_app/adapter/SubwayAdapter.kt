package com.tictoccroc.subway_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tictoccroc.subway_app.databinding.SubwaySearchItemBinding
import com.tictoccroc.subway_app.model.Subway
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation
import com.tictoccroc.subway_app.viewholder.SubwayViewHolder

class SubwayAdapter(context: Context, subwayList: Subway) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val _subwayList:Subway = subwayList;
    private val stations = _subwayList.subway_stations
    private val lines = _subwayList.subway_lines;
    private val context = context

    override fun getItemCount(): Int {
       return stations.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = SubwaySearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return SubwayViewHolder(binding, lines);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = stations.get(position);

        if(holder is SubwayViewHolder){
            holder.bind(context,item);
        }

    }
}